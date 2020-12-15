package com.example.test6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
	private static final String TestApp = "TestApp";
	Button New;
	ListView lv;
	List<Map<String, Object>> Items;
	String Filename,EditFileName;
	EditText EditFile;
	String[] fileNames;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		New = findViewById(R.id.New);
		lv = findViewById(R.id.lv);
		Items = new ArrayList<>();
		fileNames = new String[]{};
		checkNeedPermissions();
		

		//通过循环文件来获取所有的文件名
		//获取特定文件夹下的所有文件名
		File file = new File(getExternalFilesDir(null).toString()+"/myData");//获取到存储目录的路径
		if(!file.exists()) {
			file.mkdirs();
		}
		fileNames = file.list();

		if(fileNames!=null){
			for (String fileName : fileNames) {
				Filename = fileName;
				Map<String, Object> listItem = new HashMap<>();
				listItem.put("name", Filename.replace(".txt",""));
				Items.add(listItem);
			}
		}

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
				String filename = fileNames[position];//按位置获取文件名，然后进行传入后台操作
				turntoDetail(filename.replace(".txt",""));
				return false;
			}
		});

	}


	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       String permissions[], int[] grantResults) {
		super.onRequestPermissionsResult(requestCode,permissions,grantResults);
		if(requestCode==1){
			for(int i=0;i<permissions.length;i++){
				if(permissions[i].equals("android.permission.WRITE_EXTERNAL_STORAGE")&&grantResults[i]==PackageManager.PERMISSION_GRANTED){
					Log.d(TestApp, "权限"+permissions[i]+"申请成功");
				}else {
					Log.d(TestApp, "权限"+permissions[i]+"申请失败");
				}
			}
		}
	}

	// 创建编辑对话框
	public void showWaiterAuthorizationDialog() {
		// LayoutInflater是用来找layout文件夹下的xml布局文件，并且实例化
		LayoutInflater factory = LayoutInflater.from(this);
		// 把布局文件中的控件定义在View中
		final View textEntryView = factory.inflate(R.layout.edittext_dialog_layout, null);
		// 将自定义xml文件中的控件显示在对话框中
		new AlertDialog.Builder(this)
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
						EditFileName = EditFile.getText().toString()+".txt";//添加.txt后缀
						if(fileNames!=null){//如果filenames不等于null，则循环判断该文件名是否存在
							for (int i=0;i<fileNames.length;i++){
								if(EditFileName.equals(fileNames[i])){
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
													turntoDetail(EditFileName.replace(".txt",""));//将后缀txt取消
												}})
											// 设置dialog是否为模态，false表示模态，true表示非模态
											.setCancelable(false)
											// 对话框的创建、显示
											.create().show();
								}
							}
						}
						//循环完毕，说明已经存在的日记中，并没有该文件名,则带参数跳转到第二个页面
						turntoDetail(EditFileName.replace(".txt",""));
					}
				})
				// 对话框的“退出”单击事件
				.setNegativeButton("退出", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						//不做操作，关闭对话框
					}
				})
				// 设置dialog是否为模态，false表示模态，true表示非模态
				.setCancelable(false)
				// 对话框的创建、显示
				.create().show();
	}

	public void turntoDetail(String name){
		Intent intent = new Intent(MainActivity.this,TextDetail.class);
		intent.putExtra("name",name);
		startActivity(intent);
	}

	private void checkNeedPermissions(){
		// 在要调用权限的activity中插入该方法。可以写到onCreate()中。
		// 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

			// 判断是否有这个权限，是返回PackageManager.PERMISSION_GRANTED，否则是PERMISSION_DENIED
			// 这里我们要给应用授权所以是!= PackageManager.PERMISSION_GRANTED
			if (ContextCompat.checkSelfPermission(this,
					Manifest.permission.WRITE_EXTERNAL_STORAGE)
					!= PackageManager.PERMISSION_GRANTED) {

				// 如果应用之前请求过此权限但用户拒绝了请求,且没有选择"不再提醒"选项 (后显示对话框解释为啥要这个权限)，此方法将返回 true。
				if (ActivityCompat.shouldShowRequestPermissionRationale(this,
						Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
				} else {
					// requestPermissions以标准对话框形式请求权限。123是识别码（任意设置的整型），用来识别权限。应用无法配置或更改此对话框。
					//当应用请求权限时，系统将向用户显示一个对话框。当用户响应时，系统将调用应用的 onRequestPermissionsResult() 方法，向其传递用户响应。您的应用必须替换该方法，以了解是否已获得相应权限。回调会将您传递的相同请求代码传递给 requestPermissions()。
					ActivityCompat.requestPermissions(this,
							new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
							1);
				}
			}

		}
	}
}
