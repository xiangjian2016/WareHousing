package com.zigcloud.warehousing.activity;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.base.BaseActivity;
import com.zigcloud.warehousing.biz.EquipmentDAO;
import com.zigcloud.warehousing.entity.ControlResultJson;
import com.zigcloud.warehousing.entity.EquipmentJson;
import com.zigcloud.warehousing.entity.equipment.base.BaseEquipmentEntity;
import com.zigcloud.warehousing.equipment.base.BaseEquipmentWidget;
import com.zigcloud.warehousing.widget.equiment.BedLampWidget;
import com.zigcloud.warehousing.widget.equiment.CurtainWidget;
import com.zigcloud.warehousing.widget.equiment.LampWidget;
import com.zigcloud.warehousing.widget.equiment.WallLampWidget;

/**
 * 设备
 * */
public class EquipmentActivity extends BaseActivity{   
	
	private TextView titlebar_left;
	private TextView titlebar_title; 
	private String nodeId;
	private String nodeName;
	private String nodeTypeId;
	private String nodeTypeName;  
	private	String dataValueString;
	private String updateTimeString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        MainApplication.getInstance().addActivity(this);

        nodeId=getIntent().getStringExtra("nodeId");
        nodeName=getIntent().getStringExtra("nodeName");
        nodeTypeId=getIntent().getStringExtra("nodeTypeId");
        nodeTypeName=getIntent().getStringExtra("nodeTypeName");
        dataValueString=getIntent().getStringExtra("dataValueString");
        updateTimeString=getIntent().getStringExtra("updateTimeString");
        
 		titlebar_left = (TextView) findViewById(R.id.titlebar_left); 
 		titlebar_left.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) { 
 				onBackPressed(); 
 			}
 		});
 		titlebar_title = (TextView) findViewById(R.id.titlebar_title); 
 		titlebar_title.setText(nodeName);
 		
		initalEquipment();
	}
	
	/**
	 * 初始化设备信息
	 * */
	private void initalEquipment(){
		LinearLayout ll_equipment=(LinearLayout) findViewById(R.id.ll_equipment);
		if(ll_equipment!=null){
			if(nodeTypeId!=null){
				BaseEquipmentWidget equipmentWidget=null;
				if(nodeTypeId.equals("20")){ 
					equipmentWidget=new  CurtainWidget(EquipmentActivity.this);
				}
				else if(nodeTypeId.equals("24")){
					equipmentWidget=new  LampWidget(EquipmentActivity.this);
				}
				else if(nodeTypeId.equals("25")){
					equipmentWidget=new BedLampWidget(EquipmentActivity.this);
				}
				else if(nodeTypeId.equals("26")){
					equipmentWidget=new WallLampWidget(EquipmentActivity.this);
				}
				else{
					equipmentWidget=new BaseEquipmentWidget(EquipmentActivity.this);
				}  
				equipmentWidget.setNodeId(nodeId);
				equipmentWidget.setNodeTypeName(nodeTypeName);
				equipmentWidget.setNodeDataString(dataValueString);
				equipmentWidget.setUpdateTimeString(updateTimeString);  
				equipmentWidget.addSendCmdListener(new BaseEquipmentWidget.Listener(){ 
					@Override
					public void sendCmd(String nodeId, String stateFlag,
							String stateValue) {
						// TODO Auto-generated method stub
						if(mSendCmdHttpRequestTask.getStatus()!=Status.RUNNING){
							mSendCmdHttpRequestTask=new SendCmdHttpRequestTask();
							mSendCmdHttpRequestTask.execute(nodeId, stateFlag, stateValue) ;
						}
					}
					
				});
				ll_equipment.addView(equipmentWidget,new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
			}
		}
	}  

	/**
	 * 设备控制业务类
	 * */
	protected EquipmentDAO mEquipmentDAO=new EquipmentDAO();
	
	/**
	 * 异步发送命令任务类
	 * */
	protected SendCmdHttpRequestTask mSendCmdHttpRequestTask=new SendCmdHttpRequestTask();  
	
	/**
	 * 切换当前模式
	 * */
	public class SendCmdHttpRequestTask extends  AsyncTask<String, Integer, ControlResultJson>{
		@Override
		protected ControlResultJson doInBackground(
				String... params) {
			// TODO Auto-generated method stub
			if(params!=null&&params.length>2){  
				mEquipmentDAO.sendCmd(params[0], params[1], params[2]);
			}
			return null; 
		}  
	} 

	protected EquipmentHttpRequestTask mEquipmentHttpRequestTask=new EquipmentHttpRequestTask();
	/**
	 * 获取equipment
	 * */
	protected class EquipmentHttpRequestTask extends AsyncTask<String, Integer, EquipmentJson>{ 

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		} 
		
		@Override
		protected EquipmentJson doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String nodeId=arg0!=null&&arg0.length>0?arg0[0]:null;
			return mEquipmentDAO.getByNodeId(nodeId);
		}

		@Override
		protected void onPostExecute(EquipmentJson result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			BaseEquipmentEntity baseEquipment=BaseEquipmentEntity.parse(result);
			if(baseEquipment!=null){ 
				
			}
		}
	} 
}
