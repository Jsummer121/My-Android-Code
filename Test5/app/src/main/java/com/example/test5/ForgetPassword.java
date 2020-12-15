package com.example.test5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class ForgetPassword extends AppCompatActivity {
	EditText Username,Password,RePassword;
	Button SubmitButton;
	String username,password,repassword;
	AlertDialog GotoMain;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgetpassword);
		Username = findViewById(R.id.Username);
		Password = findViewById(R.id.Password);
		RePassword = findViewById(R.id.RePassword);
		SubmitButton = findViewById(R.id.submitbutton);
		username = Username.getText().toString();
		password = Password.getText().toString();
		repassword = RePassword.getText().toString();


		//为确定按钮添加监听事件
		SubmitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!password.equals(repassword)){
					AlertDialog.Builder submit_bulider = new AlertDialog.Builder(ForgetPassword.this)
							.setTitle("错误")
							.setMessage("两次密码输入不同，请重试！！！");
					GotoMain = submit_bulider.create();
					GotoMain.show();
				}else {
					//实现简单的界面跳转，后面可以加入将数据传入数据库
					Intent intent = new Intent();
					intent.setClass(ForgetPassword.this,MainActivity.class);
					startActivity(intent);
				}
			}
		});


	}
}
