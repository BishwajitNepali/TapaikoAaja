package com.thangLaStudio.today;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {
	
	private static final int TIME = 4 * 1000;// 4 seconds

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
	
	    openNewActivity();

}

	private void openNewActivity() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(SplashScreen.this,
						MainActivity.class);
				startActivity(intent);
				SplashScreen.this.finish();
				overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
			}
		}, TIME);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

			}
		}, TIME);

		
	}
}
