package com.example.middletest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class TextDetail extends AppCompatActivity {
	Boolean NoteisExist;
	TextView returntomain,Text_title,savefile;
	EditText editplace;
	String fileName,Filetext;
	SQL_DB my_db;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent mainTothis = getIntent();
		setContentView(R.layout.text_layout);

		returntomain = findViewById(R.id.returntomain);
		Text_title = findViewById(R.id.Text_title);
		editplace = findViewById(R.id.editplace);
		savefile = findViewById(R.id.save);
		my_db = new SQL_DB(TextDetail.this);

		//获取前面传到后面的文件名
		fileName = mainTothis.getStringExtra("name");

		//展示文件内容
		showFiles(fileName);

		//设置文件名
		Text_title.setText(fileName);

		//当点击returntomain时，将页面跳转回Main界面
		returntomain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intentToMain = new Intent(TextDetail.this, com.example.middletest.MainActivity.class);
				startActivity(intentToMain);
			}
		});

		//设置保存按钮
		savefile.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				//从edit获取所有的文件
				Filetext = editplace.getText().toString();
				SaveFiles(fileName,Filetext);
			}
		});
	}


	public void showFiles(String fileName){
		SQLiteDatabase db = my_db.getReadableDatabase();
		Cursor cursor = db.query("note_data", null,"name=?",new String[]{fileName},null,null,null);
		if (cursor.getCount() == 0){
			NoteisExist = false;
		}else {
			NoteisExist = true;
		}
		if (cursor.moveToFirst()){
			String note = cursor.getString(cursor.getColumnIndex("cont_data"));
			editplace.setText(note);
		}
		cursor.close();
		db.close();
	}


	// 保存事件
	public void SaveFiles(String filename,String Filetext){
		try {
			SQLiteDatabase db = my_db.getReadableDatabase();
			if(NoteisExist){//如果文件存在，则修改，
				ContentValues values = new ContentValues();
				values.put("cont_data",Filetext);
				db.update("note_data",values,"name=?",new String[]{filename+""});
				Toast.makeText(TextDetail.this,"修改成功",Toast.LENGTH_LONG).show();
			} else {//不存在则新建
				ContentValues values = new ContentValues();
				values.put("name",filename);
				values.put("cont_data",Filetext);
				db.insert("note_data",null,values);
				Toast.makeText(TextDetail.this,"保存成功",Toast.LENGTH_LONG).show();
			}
			db.close();
		}catch (Exception e){
			Toast.makeText(TextDetail.this,"保存失败",Toast.LENGTH_LONG).show();
		}
	}
}
