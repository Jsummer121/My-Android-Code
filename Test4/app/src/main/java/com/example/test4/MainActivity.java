package com.example.test4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
	private String name,password,phone,sex, hoppys,days;
	ArrayList<String> names,passwords,phones,sexs;
	TextView t_name,t_password,t_phone,t_birthday;
	RadioGroup r_sex;
	Button b_submit,b_reset;
	AlertDialog register_dialog;
	CheckBox c_swinging,c_football,c_running,c_singing,c_another;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		t_name = findViewById(R.id.Name);
		t_password = findViewById(R.id.Password);
		t_phone = findViewById(R.id.Phone);
		r_sex = findViewById(R.id.Sex);
		t_birthday = findViewById(R.id.Birthday);
		b_submit = findViewById(R.id.Submit);
		b_reset = findViewById(R.id.Reset);
		c_swinging = findViewById(R.id.Swimming);
		c_football = findViewById(R.id.Football);
		c_singing = findViewById(R.id.Singing);
		c_running = findViewById(R.id.Running);
		c_another = findViewById(R.id.Another);

		//初始化列表
		names = new ArrayList<>();
		passwords = new ArrayList<>();
		phones = new ArrayList<>();
		sexs = new ArrayList<>();


		//为爱好设置监听事件
		hoppys = "";//初始化hoppys，防止报错
		c_swinging.setOnCheckedChangeListener(this);
		c_football.setOnCheckedChangeListener(this);
		c_singing.setOnCheckedChangeListener(this);
		c_running.setOnCheckedChangeListener(this);
		c_another.setOnCheckedChangeListener(this);

		//设置日历对话框的点击事件
		final DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				if (monthOfYear + 1 < 10) {
					if (dayOfMonth < 10) {
						days = year + "年" + "0" +
								(monthOfYear + 1) + "月" + "0" + dayOfMonth + "日";
					} else {
						days = year + "年" + "0" +
								(monthOfYear + 1) + "月" + dayOfMonth + "日";
					}
				} else {
					if (dayOfMonth < 10) {
						days = year + "年" +
								(monthOfYear + 1) + "月" + "0" + dayOfMonth + "日";
					} else {
						days = year + "年" +
								(monthOfYear + 1) + "月" + dayOfMonth + "日";
					}
				}
				t_birthday.setText(days);
			}
		};

		//为生日框添加监听事件
		t_birthday.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Calendar ca = Calendar.getInstance();
				int mYear = ca.get(Calendar.YEAR);
				int mMonth = ca.get(Calendar.MONTH);
				int mDay = ca.get(Calendar.DAY_OF_MONTH);
				new DatePickerDialog(MainActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
			}
		});

		// 为性别按钮添加监听事件
		r_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(checkedId == R.id.Man){
					sex = "男";
				}else{
					sex = "女";
				}
			}
		});

		//设置注册按钮的点击事件
		b_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				name = t_name.getText().toString();
				password = t_password.getText().toString();
				phone = t_phone.getText().toString();

				//创建Dialog对话框
				AlertDialog.Builder register_bulider = new AlertDialog.Builder(MainActivity.this)
						.setTitle("注册成功")
						.setMessage("姓名："+name+"\n密码："+password+"\n电话："+phone+"\n性别："+sex+"\n爱好："+ hoppys)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Intent intent = new Intent();
								intent.setClass(MainActivity.this,UserActivity.class);
//								intent.putExtra("name",name);
//								intent.putExtra("password",password);
//								intent.putExtra("phone",phone);
//								intent.putExtra("sex",sex);

								//往后传Arrylist参数
								names.add(name);
								passwords.add(password);
								phones.add(phone);
								sexs.add(sex);
								//往第二个页面传参数
								intent.putStringArrayListExtra("name", names);
								intent.putStringArrayListExtra("password", passwords);
								intent.putStringArrayListExtra("phone", phones);
								intent.putStringArrayListExtra("sex", sexs);

								startActivity(intent);
								register_dialog.dismiss();
							}
						});
				register_dialog = register_bulider.create();
				register_dialog.show();
			}
		});


		//为重置按钮添加监听事件
		b_reset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				sex="";
				hoppys="";
				t_name.setText("");
				t_password.setText("");
				t_phone.setText("");
				r_sex.clearCheck();
				c_swinging.clearFocus();
				c_football.clearFocus();
				c_running.clearFocus();
				c_singing.clearFocus();
				c_another.clearFocus();
			}
		});
	}

	//多选框按钮
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		String motion = buttonView.getText().toString();
		if(isChecked){
			if(!hoppys.contains(motion)){
				hoppys = hoppys + motion;
			}
		}else{
			if(hoppys.contains(motion)){
				hoppys = hoppys.replace(motion,"");
			}
		}
	}
}
