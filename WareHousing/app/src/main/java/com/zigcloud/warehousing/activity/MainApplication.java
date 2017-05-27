package com.zigcloud.warehousing.activity;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

public class MainApplication extends Application {

	private List<Activity> activityList = new LinkedList<Activity>();
	
	private static MainApplication instance;

	private MainApplication()
	{
		
	} 
	public static MainApplication getInstance()
	{
		if(null == instance)
		{
			instance = new MainApplication();
		}
		return instance;  
	} 
	public void addActivity(Activity activity)
	{
		activityList.add(activity);
	} 
	public void exit()
	{

		for(Activity activity:activityList)
		{
			activity.finish();
		}  
		System.exit(0);  
	}
}

