package com.example.test5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	EditText Username,Password;
	Button Register,Login;
	TextView ForgetPSW;
	String username=null,password=null;
	AlertDialog SuccessfulLogin,NotSuccessfulLogin;
	CheckBox RememberPWD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Username = findViewById(R.id.Username);
		Password = findViewById(R.id.Password);
		Register = findViewById(R.id.register);
		Login = findViewById(R.id.login);
		ForgetPSW = findViewById(R.id.forgetPsw);
		RememberPWD = findViewById(R.id.RememberPWD);

		//则读取记住密码内容，并将其放入密码框和用户名框
		SharedPreferences sp = getSharedPreferences("rememberdata",MODE_PRIVATE);
		username = sp.getString("name","");
		password = sp.getString("password","");
		Username.setText(username);
		Password.setText(password);
		if(sp.getString("ischeck","0").equals("1")){
			RememberPWD.setChecked(true);
		}


		//为记住密码添加事件
		RememberPWD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@SuppressLint("WrongConstant")
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					//如果选中，则将用户名和密码用SharePreferences保存
					SharedPreferences sp = getSharedPreferences("rememberdata",MODE_PRIVATE);
					@SuppressLint("CommitPrefEdits") SharedPreferences.Editor editer = sp.edit();
					editer.putString("name",Username.getText().toString());
					editer.putString("password",Password.getText().toString());
					editer.putString("ischeck","1");
					editer.apply();
				}else {
					//如果是取消选择，则将该文件删除
					SharedPreferences sp = getSharedPreferences("rememberdata",MODE_PRIVATE);
					SharedPreferences.Editor editer = sp.edit();
					editer.clear();
				}
			}
		});

		//为注册按钮添加跳转事件
		Register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,Register.class);
				startActivity(intent);
			}
		});

		//为忘记密码添加监听事件
		ForgetPSW.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,ForgetPassword.class);
				startActivity(intent);
			}
		});

		Login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(username==""||username==null){
					username = Username.getText().toString();
					password = Password.getText().toString();
				}
				//获取用户输入的用户名和密码，然后连接数据库进行判断用if和else
				if(username.equals("summer") & password.equals("summer")){
					AlertDialog.Builder successLogin_builder = new AlertDialog.Builder(MainActivity.this)
							.setTitle("登录成功")
							.setMessage("用户名："+username+"\n密码："+password+"\n登陆成功");
					SuccessfulLogin = successLogin_builder.create();
					SuccessfulLogin.show();
				}else {
					AlertDialog.Builder notsuccessLogin_builder = new AlertDialog.Builder(MainActivity.this)
							.setTitle("登录失败")
							.setMessage("用户名或密码错误，请重试！！！");
					NotSuccessfulLogin = notsuccessLogin_builder.create();
					NotSuccessfulLogin.show();
				}

			}
		});
	}
}
