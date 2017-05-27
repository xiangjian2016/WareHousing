package com.zigcloud.warehousing.activity.vitamio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.LoginActivity;
import com.zigcloud.warehousing.activity.MainApplication;
import com.zigcloud.warehousing.activity.base.BaseActivity;

/**
 * 过渡页面
 * */
public class SplashActivity extends BaseActivity{

	private Handler mHandler = new Handler();
	protected final int SPLASH_TIME = 500;  
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 	
        //setFullScreen();  
        View view=View.inflate(this, R.layout.activity_splash, null);
		setContentView(view);
		Animation animation=AnimationUtils.loadAnimation(this, R.anim.alpha);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {}
			@Override
			public void onAnimationRepeat(Animation arg0) {}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						goMain();
					}
				}, SPLASH_TIME);
			}
		});   
	}

	protected void onResume() {
		super.onResume();
	}

	private void goMain() {
		MainApplication.getInstance().addActivity(this);
		startActivity(new Intent(this,LoginActivity.class));
	};
	
	@SuppressWarnings("unused")
	private void setFullScreen(){
	     getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	@SuppressWarnings("unused")
	private void quitFullScreen(){
	      final WindowManager.LayoutParams attrs = getWindow().getAttributes();
	      attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      getWindow().setAttributes(attrs);
	      getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
	}
}