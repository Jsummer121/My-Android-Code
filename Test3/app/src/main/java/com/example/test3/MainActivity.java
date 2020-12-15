package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
	TextView text4;
	EditText Edit1,Edit2,Edit3;
	int num1,num2,num3;
	Button bt1,bt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Edit1 = this.findViewById(R.id.Edit1);
		Edit2 = this.findViewById(R.id.Edit2);
		Edit3 = this.findViewById(R.id.Edit3);
		bt1 = this.findViewById(R.id.Button1);
		bt2 = this.findViewById(R.id.Button2);
		text4 = this.findViewById(R.id.Test4);
		bt1.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("SetTextI18n")
			@Override
			public void onClick(View view) {
				text4.setText("hello");
				num1 = Integer.parseInt(Edit1.getEditableText().toString());
				num2 = Integer.parseInt(Edit2.getEditableText().toString());
				num3 = Integer.parseInt(Edit3.getEditableText().toString());
				if (num3==num1+num2){
					text4.setText("正确");
				}else{
					text4.setText("错误");
				}
			}
		});

		bt2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Edit1.setText("");
				Edit2.setText("");
				Edit3.setText("");
			}
		});
	}
}
