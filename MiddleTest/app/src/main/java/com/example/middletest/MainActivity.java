package com.example.middletest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
	Button New;
	ListView lv;
	List<Map<String, Object>> Items;
	String EditFileName;
	EditText EditFile;
	List<String> fileNames;
	SQL_DB my_db;
	AlertDialog editdialog,deletedialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		New = findViewById(R.id.New);
		lv = findViewById(R.id.lv);
		Items = new ArrayList<>();
		fileNames = new ArrayList<>();
		my_db = new SQL_DB(MainActivity.this);


		// 通过SQLite获取当前数据库中所有的文件名
		SQLiteDatabase db = my_db.getReadableDatabase();//获取可读sqlite对象
		Cursor cursor = db.query("note_data",null,null,null,null,null,null);
		if (cursor.getCount() != 0){
			while (cursor.moveToNext()){
				Map<String, Object> listItem = new HashMap<>();
				String name = String.valueOf(cursor.getString(cursor.getColumnIndex("name")));
				fileNames.add(name);
				listItem.put("name", name);
				Items.add(listItem);
			}
		}
		cursor.close();
		db.close();

		//设置lv显示
		SimpleAdapter simpleAdapter = new SimpleAdapter(
				this,
				Items,
				R.layout.text_listview_layout,
				new String[]{"name"},
				new int[]{R.id.text_name});
		lv.setAdapter(simpleAdapter);


		//设置新建文件事件
		New.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showWaiterAuthorizationDialog();
			}
		});

		//设置Items长按事件
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			                               long id) {
				//写删除事件
				deleteNote((String) fileNames.get(position),position);
				return false;
			}
		});

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				turntoDetail((String) fileNames.get(position));
			}
		});

	}


	// 创建编辑对话框
	public void showWaiterAuthorizationDialog() {
		// LayoutInflater是用来找layout文件夹下的xml布局文件，并且实例化
		LayoutInflater factory = LayoutInflater.from(this);
		// 把布局文件中的控件定义在View中
		final View textEntryView = factory.inflate(R.layout.edittext_dialog_layout, null);
		// 将自定义xml文件中的控件显示在对话框中
		AlertDialog.Builder editBuilder = new AlertDialog.Builder(MainActivity.this)
				// 对话框的标题
				.setTitle("创建新日记")
				// 设定显示的View
				.setView(textEntryView)
				// 对话框中的“完成”按钮的点击事件
				.setPositiveButton("完成", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						//获取用户输入的文件名
						//textEntryView.findViewById很重要，因为上面factory.inflate(R.layout.activity_login, null)将页面布局赋值给了textEntryView了
						EditFile = textEntryView.findViewById(R.id.Eidt_Filename);
						EditFileName = EditFile.getText().toString();//获取编辑框内输入的文本名
						if(fileNames!=null){//如果filenames不等于null，则循环判断该文件名是否存在
							for (int i=0;i<fileNames.size();i++){
								if(EditFileName.equals(fileNames.get(i))){
									//判断如果该文件夹存在，则新建一个dialog判断是否需要跳转到该日记
									//如果需要，则直接带文件夹名跳转
									//如果不需要，则清空edit，让用户重新输入
									new AlertDialog.Builder(MainActivity.this)
											.setMessage("该日记名已存在，是否需要跳转？")
											.setPositiveButton("确定", new DialogInterface.OnClickListener() {
												@Override
												public void onClick(DialogInterface dialog,
												                    int which) {
													//带参数跳转
													turntoDetail(EditFileName);//将后缀txt取消
												}})
											// 设置dialog是否为模态，false表示模态，true表示非模态
											.setCancelable(false)
											// 对话框的创建、显示
											.create().show();
								}
							}
						}
						//循环完毕，说明已经存在的日记中，并没有该文件名,则带参数跳转到第二个页面
						turntoDetail(EditFileName);
					}
				})
				// 对话框的“退出”单击事件
				.setNegativeButton("退出", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						editdialog.dismiss();
					}
				})
				// 设置dialog是否为模态，false表示模态，true表示非模态
				.setCancelable(false);
		editdialog = editBuilder.create();
		editdialog.show();
	}


	//跳转详细页面事件
	public void turntoDetail(String name){
		Intent intent = new Intent(MainActivity.this,TextDetail.class);
		intent.putExtra("name",name);
		startActivity(intent);
	}

	//删除事件
	public void deleteNote(final String name, final int POSTion){
		final int postion = POSTion;
		//创建询问是否删除对话框
		AlertDialog.Builder deleBuilder = new AlertDialog.Builder(MainActivity.this)
				// 对话框的标题
				.setTitle("是否删除？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SQLiteDatabase db = my_db.getReadableDatabase();
						db.delete("note_data","name=?",new String[]{name+""});
						Items.remove(postion);
						lv.deferNotifyDataSetChanged();
						Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_LONG).show();
						db.close();
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//不做操作，关闭对话框
						deletedialog.dismiss();
					}
				})
				// 设置dialog是否为模态，false表示模态，true表示非模态
				.setCancelable(false);
		deletedialog = deleBuilder.create();
		deletedialog.show();
	}
}
