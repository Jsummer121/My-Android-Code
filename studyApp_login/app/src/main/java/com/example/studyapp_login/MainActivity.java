package com.example.studyapp_login;

import androidx.appcompat.app.AppCompatActivity;
/*import android.support.v7.app.AppCompatActivity;*/
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
	private EditText et1, etp1;  //2个文本框
	SharedPreferences sp;
	Button bt1, bt2, bt3;/*bt1为登录，2为注册，c为清除*/
	CheckBox cb1;
	AlertDialog SuccessfulLogin,NotSuccessfulLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et1 = findViewById(R.id.et1);
		etp1 = findViewById(R.id.etp1);
		bt1 = findViewById(R.id.bt_login);
		bt2 = findViewById(R.id.bt_reg);
		bt3 = findViewById(R.id.bt_clean);


		sp = getPreferences(Activity.MODE_PRIVATE); //创建SharedPreferences对象
		final String username = sp.getString("username", "");  //第2参数表示按键名取不到值时，设置为空串
		final String password = sp.getString("password", "");
		et1.setText(username);
		etp1.setText(password);

		bt1.setOnClickListener(new View.OnClickListener() {//为登录按钮注册监听
			@Override
			public void onClick(View view) {    //为登录注册监听
//                String s1 = et1.getText().toString();  /*登录文本字符串*/
//                String s2 = etp1.getText().toString();
//                Toast.makeText(getApplicationContext(), "输入的用户名为" + s1 + "\n输入的密码为" + s2, Toast.LENGTH_LONG).show();
//                if (username.equals("15840032743") & password.equals("ljc")) {
//                    AlertDialog.Builder successLogin_builder = new AlertDialog.Builder(MainActivity.this)
//                            .setTitle("登录成功")
//                            .setMessage("用户名：" + username + "\n密码：" + password + "\n登陆成功");
//                    SuccessfulLogin = successLogin_builder.create();
//                    SuccessfulLogin.show();
//                } else {
//                    AlertDialog.Builder notsuccessLogin_builder = new AlertDialog.Builder(MainActivity.this)
//                            .setTitle("登录失败")
//                            .setMessage("用户名或密码错误，请重试！！！");
//                    NotSuccessfulLogin = notsuccessLogin_builder.create();
//                    NotSuccessfulLogin.show();
//                }

				Intent intent1 = new Intent();
				intent1.setClass(MainActivity.this, abc.class);
				startActivity(intent1);

			}
		});

		bt2.setOnClickListener(new View.OnClickListener() {//为注册 注册监听
			//@Override
			public void onClick(View view) {    //为注册按钮注册监听
				Intent intent = new Intent();
//                intent2.setClass(MainActivity.this,register.class);
				startActivity(new Intent(MainActivity.this, register.class));
//                intent.setClass(MainActivity.this,register.class);
				startActivity(intent);
			}
		});

		bt3.setOnClickListener(new View.OnClickListener() {/*重置按钮注册监听*/
			public void onClick(View v) {       //为重置注册监听
				et1.setText("");
				etp1.setText("");
				Toast.makeText(getApplicationContext(), "已重置输入信息", Toast.LENGTH_SHORT).show();
			}
		});

		cb1 = findViewById(R.id.cb);        //复选框注册监听
		cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  //选中监听
			@SuppressLint("ApplySharedPref")
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1) { //勾选时
					Toast.makeText(MainActivity.this, "已保存登录信息", Toast.LENGTH_LONG).show();
					sp.edit()
							.putString("username", et1.getText().toString())
							.putString("password", etp1.getText().toString())
							.commit();
				} else {  //未勾选时
					//第一参数要求为Context类型，外部类对象
					Toast.makeText(MainActivity.this, "不保存登录信息", Toast.LENGTH_LONG).show();
					sp.edit()
							.putString("username", null)
							.putString("password", null)
							.commit();
				}
			}
		});
	}
}