package com.thangLaStudio.today;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Rashifal extends Activity{
	TextView tv;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String text = extras.getString("apple");
		
		setContentView(R.layout.activity_rashifal);
		tv=(TextView) findViewById(R.id.rashi_text);
	
		Log.e("k xa rashi ma", text+"");
		
		tv.setText(text+"");
		
	}

}
