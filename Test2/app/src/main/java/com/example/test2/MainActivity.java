package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	TextView tv1;
	Button b_ac, b_fu, b_baifen, b_chu, b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, b_cheng, b_and,
			b_dengyu, b_dote;
	int way, dote = 0;
	double first_number, number, float_dote;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//声明变量
		tv1 = this.findViewById(R.id.tv1);//显示器
		b_ac = this.findViewById(R.id.ac);//AC键
		b_fu = this.findViewById(R.id.fuhao);//正负号
		b_baifen = this.findViewById(R.id.baifenhao);//百分号
		b_chu = this.findViewById(R.id.chu);//除号
		b1 = this.findViewById(R.id.one);
		b2 = this.findViewById(R.id.two);
		b3 = this.findViewById(R.id.three);
		b4 = this.findViewById(R.id.four);
		b5 = this.findViewById(R.id.five);
		b6 = this.findViewById(R.id.six);
		b7 = this.findViewById(R.id.seven);
		b8 = this.findViewById(R.id.eight);
		b9 = this.findViewById(R.id.nine);
		b0 = this.findViewById(R.id.zero);
		b_cheng = this.findViewById(R.id.cheng);//乘号
		b_and = this.findViewById(R.id.and);//加号
		b_dengyu = this.findViewById(R.id.dengyu);//等于号
		b_dote = this.findViewById(R.id.dote);//点号

		//利用onclick接口实现按钮事件
		b_ac.setOnClickListener(this);
		b_fu.setOnClickListener(this);
		b_baifen.setOnClickListener(this);
		b_chu.setOnClickListener(this);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);
		b9.setOnClickListener(this);
		b0.setOnClickListener(this);
		b_cheng.setOnClickListener(this);
		b_and.setOnClickListener(this);
		b_dengyu.setOnClickListener(this);
		b_dote.setOnClickListener(this);
	}

	//添加单击事件
	@SuppressLint("SetTextI18n")
	@Override
	public void onClick(View v) {
		int n = v.getId();
		if (n == R.id.ac) {
			number = 0;
			first_number = 0;
			dote=0;
		} else if (n == R.id.fuhao) {
			number = -number;
		} else if (n == R.id.dote) {
			dote = 1;
		} else if (n == R.id.baifenhao) {
			number %= 100;
		} else if (n == R.id.zero) {
			if (dote == 0) {
				number *= 10;
			}
		} else if (n == R.id.one) {
			if (dote == 0) {
				number *= 10;
				number += 1;
			} else {
				float_dote = 1;
				for (int i = 0; i < dote; i++) {
					float_dote /= 10;
				}
				BigDecimal b = new BigDecimal(float_dote);
				float_dote = b.setScale(dote,BigDecimal.ROUND_HALF_UP).doubleValue();
				number += float_dote;
				dote+=1;
			}
		} else if (n == R.id.two) {
			if (dote == 0) {
				number *= 10;
				number += 2;
			} else {
				float_dote = 2;
				for (int i = 0; i < dote; i++) {
					float_dote /= 10;
				}
				BigDecimal b = new BigDecimal(float_dote);
				float_dote = b.setScale(dote,BigDecimal.ROUND_HALF_UP).doubleValue();
				number += float_dote;
				dote+=1;
			}
		} else if (n == R.id.three) {
			if (dote == 0) {
				number *= 10;
				number += 3;
			} else {
				float_dote = 3;
				for (int i = 0; i < dote; i++) {
					float_dote /= 10;
				}
				BigDecimal b = new BigDecimal(float_dote);
				float_dote = b.setScale(dote,BigDecimal.ROUND_HALF_UP).doubleValue();
				number += float_dote;
				dote+=1;
			}
		} else if (n == R.id.four) {
			if (dote == 0) {
				number *= 10;
				number += 4;
			} else {
				float_dote = 4;
				for (int i = 0; i < dote; i++) {
					float_dote /= 10;
				}
				BigDecimal b = new BigDecimal(float_dote);
				float_dote = b.setScale(dote,BigDecimal.ROUND_HALF_UP).doubleValue();
				number += float_dote;
				dote+=1;
			}
		} else if (n == R.id.five) {
			if (dote == 0) {
				number *= 10;
				number += 5;
			} else {
				float_dote = 5;
				for (int i = 0; i < dote; i++) {
					float_dote /= 10;
				}
				BigDecimal b = new BigDecimal(float_dote);
				float_dote = b.setScale(dote,BigDecimal.ROUND_HALF_UP).doubleValue();
				number += float_dote;
				dote+=1;
			}
		} else if (n == R.id.six) {
			if (dote == 0) {
				number *= 10;
				number += 6;
			} else {
				float_dote = 6;
				for (int i = 0; i < dote; i++) {
					float_dote /= 10;
				}
				BigDecimal b = new BigDecimal(float_dote);
				float_dote = b.setScale(dote,BigDecimal.ROUND_HALF_UP).doubleValue();
				number += float_dote;
				dote+=1;
			}
		} else if (n == R.id.seven) {
			if (dote == 0) {
				number *= 10;
				number += 7;
			} else {
				float_dote = 7;
				for (int i = 0; i < dote; i++) {
					float_dote /= 10;
				}
				BigDecimal b = new BigDecimal(float_dote);
				float_dote = b.setScale(dote,BigDecimal.ROUND_HALF_UP).doubleValue();
				number += float_dote;
				dote+=1;
			}
		} else if (n == R.id.eight) {
			if (dote == 0) {
				number *= 10;
				number += 8;
			} else {
				float_dote = 8;
				for (int i = 0; i < dote; i++) {
					float_dote /= 10;
				}
				BigDecimal b = new BigDecimal(float_dote);
				float_dote = b.setScale(dote,BigDecimal.ROUND_HALF_UP).doubleValue();
				number += float_dote;
				dote+=1;
			}
		} else if (n == R.id.nine) {
			if (dote == 0) {
				number *= 10;
				number += 9;
			} else {
				float_dote = 9;
				for (int i = 0; i < dote; i++) {
					float_dote /= 10;
				}
				BigDecimal b = new BigDecimal(float_dote);
				float_dote = b.setScale(dote,BigDecimal.ROUND_HALF_UP).doubleValue();
				number += float_dote;
				dote+=1;
			}
		} else if (n == R.id.chu) {
			way = 0;
			first_number = number;
			number = 0;
			dote=0;
		} else if (n == R.id.cheng) {
			way = 1;
			first_number = number;
			number = 0;
			dote=0;
		} else if (n == R.id.and) {
			way = 2;
			first_number = number;
			number = 0;
			dote=0;
		} else if (n == R.id.jian) {
			way = 3;
			first_number = number;
			number = 0;
			dote=0;
		} else if (n == R.id.dengyu) {
			//实现运算
			if(first_number!=0){
				if (way == 0) {
					number = first_number / number;
				} else if (way == 1) {
					number *= first_number;
				} else if (way == 2) {
					number += first_number;
				} else {
					number = first_number - number;
				}
			}
		}
		tv1.setText(Double.toString(number));
	}
}
