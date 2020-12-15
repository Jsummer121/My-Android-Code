package com.example.test5;
import android.annotation.SuppressLint;
import androidx.annotation.Nullable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressLint("Registered")
public class UserActivity extends Activity {
	ArrayList<String> names,passwords,phones,sexs;
	AlertDialog delete_dialog;
	List<Map<String,Object>> listItems;
	Intent intent;
	TextView returnToMain;
	ListView lv;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_listview);
		returnToMain = findViewById(R.id.returnToMain);
		intent = getIntent();
		listItems = new ArrayList<>();
		names = new ArrayList<>();
		passwords = new ArrayList<>();
		phones = new ArrayList<>();
		sexs = new ArrayList<>();

		//为返回按钮添加监听事件
		returnToMain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(UserActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});


		//这是从第一个界面传数组的代码
		names = intent.getStringArrayListExtra("name");
		passwords = intent.getStringArrayListExtra("password");
		phones = intent.getStringArrayListExtra("phone");
		sexs = intent.getStringArrayListExtra("sex");
		ShowAdapter(names,passwords,phones,sexs);
		//创建长按事件
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position,
			                               long id) {

				//创建dialog询问是否删除
				AlertDialog.Builder delete_bulider = new AlertDialog.Builder(UserActivity.this)
						.setMessage("您真的要确删除么？？？")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								//按照位置删除元素
								names.remove(position);
								passwords.remove(position);
								phones.remove(position);
								sexs.remove(position);
								delete_dialog.dismiss();
								ShowAdapter(names,passwords,phones,sexs);
							}
						}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								delete_dialog.dismiss();
							}
						});
				delete_dialog = delete_bulider.create();
				delete_dialog.show();
				return false;
			}
		});
	}
	//创建视图显示函数
	public void ShowAdapter(ArrayList names,ArrayList passwords,ArrayList phones,ArrayList sexs){
		//将数据添加到ListView中
		for (int i = 0; i<names.size(); i++){
			Map<String, Object> listItem = new HashMap<>();
			listItem.put("name",names.get(i));
			listItem.put("password",passwords.get(i));
			listItem.put("phone",phones.get(i));
			listItem.put("sex",sexs.get(i));
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter =new SimpleAdapter(
				UserActivity.this,
				listItems,
				R.layout.user_layout,
				new String[] {"name","password","phone","sex"},
				new int[] {R.id.l_name,R.id.l_password,R.id.l_phone,R.id.l_sex});
		lv = findViewById(R.id.lv);
		lv.setAdapter(simpleAdapter);
	}
}
