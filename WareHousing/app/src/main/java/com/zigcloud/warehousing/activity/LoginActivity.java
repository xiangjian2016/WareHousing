package com.zigcloud.warehousing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 用户登录
 * */
public class LoginActivity extends BaseActivity{ 
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_login);
        MainApplication.getInstance().addActivity(this);
        
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,MainActivity.class));
			}
		});
    } 
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	} 
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
        {  
            exitBy2Click();	
        }
		return false;
	} 
	private static Boolean isExit = false;
	/**
	 * 双击后退按钮退出
	 * */
	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; 
			Toast.makeText(this, getResources().getString(R.string.exit_dialog_message), Toast.LENGTH_SHORT).show();
			tExit = new Timer(); 
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; 
				}
			}, 2000); 

		} else { 
			MainApplication.getInstance().exit();
		}
	}
}