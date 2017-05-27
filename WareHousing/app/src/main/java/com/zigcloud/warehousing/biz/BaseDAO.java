package com.zigcloud.warehousing.biz;

import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;

/**
 * 业务处理基类
 * */
public class BaseDAO {
	
	ObjectMapper mObjectMapper = new ObjectMapper();

	protected Activity mActivity;
	
	public BaseDAO(){};
	
	public BaseDAO(Activity activity)
	{
		mActivity=activity;
	} 
}
