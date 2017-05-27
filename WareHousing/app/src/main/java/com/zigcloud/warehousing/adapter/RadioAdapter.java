package com.zigcloud.warehousing.adapter; 
 
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;
 
/**
 * 支持单选的适配器
 * */
public class RadioAdapter extends SimpleAdapter{

	/**
	 * 未选中的背景图片
	 * */
	private int backGroundUnCheckedRes;
	/**
	 * 未选中的背景图片
	 * */
	private int backGroundCheckedRes;
	/**
	 * 当前选中的位置
	 * */
	private int seclectItemPosition = -1;  

	public int getBackGroundUnCheckedRes() {
		return backGroundUnCheckedRes;
	}
    
	public void setBackGroundUnCheckedRes(int backGroundUnCheckedRes) {
		this.backGroundUnCheckedRes = backGroundUnCheckedRes;
	}
	
	public int getBackGroundCheckedRes() {
		return backGroundCheckedRes;
	}
	
	public void setBackGroundCheckedRes(int backGroundCheckedRes) {
		this.backGroundCheckedRes = backGroundCheckedRes;
	}
	 
	/**
     * 获取当前选中项
     * */
	public int getSeclectItemPosition() {
		return seclectItemPosition;
	} 
	/**
	 * 设置当前选中项
	 * */
	public void setSeclectItemPosition(int position) {
		seclectItemPosition = position;
	} 
	
	public RadioAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
	}
	
	public RadioAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to,int backGroundUnCheckedRes,int backGroundCheckedRes) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
		this.backGroundUnCheckedRes=backGroundUnCheckedRes;
		this.backGroundCheckedRes=backGroundCheckedRes;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=super.getView(position, convertView, parent);
		if(view!=null){
			if(seclectItemPosition==position){
				view.setBackgroundResource(backGroundCheckedRes);
			}
			else{
				view.setBackgroundResource(backGroundUnCheckedRes);
			}
		}
		return view;
	}
}