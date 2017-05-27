package com.zigcloud.warehousing.equipment.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.entity.equipment.base.BaseEquipmentEntity;

import java.util.Vector;

/**
 * 设备控件基类
 * */
public class BaseEquipmentWidget extends LinearLayout{ 
	
	private Vector<Listener> listeners=new Vector<Listener>();
	
	protected View mView;
	
	private String nodeId;
	
	private String nodeTypeName;
	
	private String nodeDataString;
	
	private String updateTimeString;
	
	private int nodeImageRes; 
	
	/**
	 * 当前设备实例
	 * */
	protected BaseEquipmentEntity baseEquipment;   
	
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		TextView txt_nodeID=(TextView)mView.findViewById(R.id.txt_nodeID);
		if(txt_nodeID!=null)txt_nodeID.setText(nodeId);
		this.nodeId = nodeId;
		
	}

	public String getNodeTypeName() {
		return nodeTypeName;
	}

	public void setNodeTypeName(String nodeTypeName) {
		TextView txt_nodeTypeName=(TextView)mView.findViewById(R.id.txt_nodeType);
		if(txt_nodeTypeName!=null)txt_nodeTypeName.setText(nodeTypeName);
		this.nodeTypeName = nodeTypeName;
	}

	public String getNodeDataString() {
		return nodeDataString;
	}

	public void setNodeDataString(String nodeDataString) {
		TextView txt_nodeData=(TextView)mView.findViewById(R.id.txt_nodeData);
		if(txt_nodeData!=null)txt_nodeData.setText(nodeDataString);
		this.nodeDataString = nodeDataString;
	}

	public String getUpdateTimeString() {
		return updateTimeString;
	}

	public void setUpdateTimeString(String updateTimeString) {
		TextView txt_updateTime=(TextView)mView.findViewById(R.id.txt_updateTime);
		if(txt_updateTime!=null)txt_updateTime.setText(updateTimeString);
		this.updateTimeString = updateTimeString;
	}

	public int getNodeImageRes() {
		return nodeImageRes;
	}

	public void setNodeImageRes(int nodeImageRes) {
		ImageView img_nodeImage=(ImageView)mView.findViewById(R.id.img_nodeImage); 
		if(img_nodeImage!=null)img_nodeImage.setImageResource(nodeImageRes);  
		this.nodeImageRes = nodeImageRes;
	}

	public BaseEquipmentEntity getBaseEquipment() {
		return baseEquipment;
	} 
	
	public void setBaseEquipment(BaseEquipmentEntity baseEquipment) {
		if(baseEquipment!=null){
			setNodeId(baseEquipment.getNodeId());
			setNodeTypeName(baseEquipment.getTypeName());
			setNodeDataString(baseEquipment.getDataValueString());
			setNodeImageRes(baseEquipment.getImageRes());
		}
		this.baseEquipment=baseEquipment;
	}
	
	public BaseEquipmentWidget(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		createView(context);
	}
	
	public BaseEquipmentWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		createView(context); 
	}
	
	/**
	 * 创建控件
	 * */
	protected void createView(Context context){ 
		mView=LayoutInflater.from(context).inflate(R.layout.widget_equipment_base, this, true);  
	} 
	
	/*********************************************************************
	Event management methods
	*********************************************************************/
    /**
     * Add the listener class (listener Zigbee data)
     * */
    public synchronized void addSendCmdListener(Listener listener)
    {
    	listeners.addElement(listener);
    }
    /**
     * The remove listener class (listener Zigbee data)
     * */
    public synchronized void removeSendListener(Listener listener)
    {
    	listeners.removeElement(listener);
    }
    /**
     * Perform incident response function method list (listener Zigbee data)
     * */
    @SuppressWarnings("unchecked")
	protected synchronized void doEvent(String nodeId, String stateFlag, String stateValue)
    {
        Vector<Listener> tempVector=null;   
        tempVector=(Vector<Listener>)listeners.clone();
        for(int i=0;i<tempVector.size();i++)
        {
        	Listener listener=(Listener)tempVector.elementAt(i);
        	listener.sendCmd(nodeId, stateFlag, stateValue); 
        } 
    }
	/**
	 * 事件监听器
	 * */
	public static class Listener implements java.util.EventListener{ 
		/**
		 * 发送指令
		 * */
		public void sendCmd(String nodeId, String stateFlag, String stateValue){
			System.out.println("send cmd...");
		}
	}
}
