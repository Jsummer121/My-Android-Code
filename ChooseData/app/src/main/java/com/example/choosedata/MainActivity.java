package com.example.choosedata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
	TextView t1,t2;
	DatePicker dp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);//
		t1= this.findViewById(R.id.tv1);//用id获取t1，是界面最上面的一个部分，是用来进行显式点击过后的日期的
		dp= this.findViewById(R.id.dp);//是日历组件
		t2= this.findViewById(R.id.tv2);//按钮组件


		//如果需要在选择日期后立刻做出响应，可以对日期选择器进行初始化
		//初始化时，需要先获得系统的日期。
		//根据实际需要来做按钮单击事件或日期选择器的初始化
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int  day = calendar.get(Calendar.DAY_OF_MONTH);

		dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
			@SuppressLint("SetTextI18n")
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				//该函数是当日历改变之后，现获取选择的月与日，然后将他们传入Check函数进行判断，并将返回值输出到t1与t2模块。
				int Month = dp.getMonth();//获取月
				int day = dp.getDayOfMonth();//获取日
				String[] name = Check(Month,day);
				t1.setText("该日期的星座为："+name[0]);
				t2.setText(name[1]);
			}
		});
	}

	@SuppressLint("SetTextI18n")
	public String[] Check(int month, int day){
		if ((month==1 && day>=20) || (month==2 && day<=18)){
			return new String[]{"水瓶座", "Aquarius\n1月22日~2月18日"};
		}else if((month==2 && day>=19) || (month==3 && day<=20)){
			return new String[]{"双鱼座", "Pisces\n2月19日~3月20日"};
		}else if((month==3 && day>=21) || (month==4 && day <=19)){
			return new String[]{"白羊座", "Aries\n3月21日~4月19日"};
		}else if((month==4 && day>=20) || (month==5 && day>=20)){
			return new String[]{"金牛座", "Taurus\n4月20日~5月20日"};
		}else if((month==5 && day>=21) || (month==6 && day<=21)){
			return new String[]{"双子座", "Gemini\n5月21日~6月21日"};
		}else if((month==6 && day>=22) || (month==7 && day<=22)){
			return new String[]{"巨蟹座", "Cancer\n6月22日~7月22日"};
		}else if((month==7 && day>=23) || (month==8 && day<=22)){
			return new String[]{"狮子座", "Leo\n7月23日~8月22日"};
		}else if((month==8 && day>=23) || (month==9 && day<=22)){
			return new String[]{"处女座", "Virgo\n8月23日~9月22日"};
		}else if((month==9 && day>=23) || (month==10 && day<=23)){
			return new String[]{"天秤座", "Libra\n9月23日~10月23日"};
		}else if((month==10 && day>=24) || (month==11 && day<=22)){
			return new String[]{"天蝎座", "Scorpio\n10月24日~11月22日"};
		}else if((month==11 && day>=23)|| (month==12 && day<=21)){
			return new String[]{"射手座", "Sagittarius\n11月23日~12月21日"};
		}else{
			return new String[]{"摩羯座", "Capricorn\n12月22日~1月19日"};
		}
	}
}
