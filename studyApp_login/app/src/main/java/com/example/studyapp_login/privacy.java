package com.example.studyapp_login;

import android.os.Bundle;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class privacy extends AppCompatActivity {
    private Toolbar mToolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacypolicy);
        mToolbar =findViewById(R.id.mToolbar);

//        setSupportActionBar(mToolbar);
//        //设置导航Button点击事件
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"点击导航栏",Toast.LENGTH_SHORT).show();
//            }
//        });
    }



}
