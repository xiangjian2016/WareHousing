package com.zigcloud.warehousing.widget.equiment;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.equipment.base.BaseEquipmentWidget;

public class WallLampWidget extends BaseEquipmentWidget{
	
	public WallLampWidget(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public WallLampWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void createView(Context context) {
		// TODO Auto-generated method stub
		mView=LayoutInflater.from(context).inflate(R.layout.widget_equipment_bedlamp, this, true);
		mView.findViewById(R.id.btn_on).setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(getNodeId()!=null)doEvent(getNodeId(),"01","00");
			}
		});
		mView.findViewById(R.id.btn_off).setOnClickListener(new OnClickListener() { 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub  
				if(getNodeId()!=null)doEvent(getNodeId(),"00","00");
			}
		});
	} 
}
