package com.example.test6;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextDetail extends AppCompatActivity {
	TextView returntomain,Text_title,savefile;
	EditText editplace;
	String fileName,Filetext;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent mainTothis = getIntent();
		setContentView(R.layout.text_layout);

		checkNeedPermissions();

		returntomain = findViewById(R.id.returntomain);
		Text_title = findViewById(R.id.Text_title);
		editplace = findViewById(R.id.editplace);
		savefile = findViewById(R.id.save);

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
				Intent intentToMain = new Intent(TextDetail.this,MainActivity.class);
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
			File file = new File(getExternalFilesDir(null).toString()+"/myData/" + fileName+".txt");
			if(file.exists()){//如果文件存在，则输出
				Scanner scan = null;
				try {
					scan = new Scanner(new FileInputStream(file));
					while(scan.hasNext()){    // 循环读取
						TextDetail.this.editplace.append(scan.next() + "\n") ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (scan != null) scan.close(); // 关闭打印流
				}
			}
	}




	public void SaveFiles(String filename,String Filetext){
		//如果原来中存在文件，则将其清空，然后将现在的内容保存进入，如果没有则直接保存
		File file = new File(getExternalFilesDir(null).toString()+"/myData/" + fileName+".txt");
		if (file.exists()) {     // 如果该文件存在
			file.delete(); // 删除该文件
		}
		PrintStream out = null ;
		FileOutputStream f1;
		try {
			f1 = new FileOutputStream(file);
			out = new PrintStream(f1);
			out.write(Filetext.getBytes());
			Toast.makeText(TextDetail.this,"保存成功",Toast.LENGTH_LONG).show();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Toast.makeText(TextDetail.this,"保存失败",Toast.LENGTH_LONG).show();
		} finally {
			if (out != null) {
				out.close() ;     // 关闭打印流
			}
		}
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
