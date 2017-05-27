package com.zigcloud.warehousing.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.base.BaseActivity;
import com.zigcloud.warehousing.biz.EquipmentDAO;
import com.zigcloud.warehousing.entity.EquipmentJson;
import com.zigcloud.warehousing.entity.equipment.base.BaseEquipmentEntity;
import com.zigcloud.warehousing.widget.Gallery3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 家用电器
 * */
@SuppressLint({ "CutPasteId", "HandlerLeak" })
public class FunctionListActivity extends BaseActivity{ 
	
	private TextView titlebar_left;
	private TextView titlebar_title;
	
	private GridView equipmentsGridView;  
	private ArrayList<HashMap<String, Object>> equipmentsArrayList = new ArrayList<HashMap<String, Object>>();
	private HashMap<String, Object> equipmentsHashMap = new HashMap<String, Object>(); 
	private SimpleAdapter equipmentsAdapter;
	/**
	 * 获取设备列表
	 * */
	private EquipmentListHttpRequestTask equipmentListHttpRequestTask=new EquipmentListHttpRequestTask();
	 
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_environmentmonitor);
        MainApplication.getInstance().addActivity(this);
        
		titlebar_left = (TextView) findViewById(R.id.titlebar_left); 
		titlebar_left.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				onBackPressed(); 
			}
		});
		titlebar_title = (TextView) findViewById(R.id.titlebar_title); 
		titlebar_title.setText(R.string.title_myfunction);
         
		initialRoomsGallery();
		initialEquipmentsGridView();
    }  
	/**
	 * 初始化设备信息列表
	 * */
	private void initialEquipmentsGridView(){
		
		equipmentsGridView=(GridView) findViewById(R.id.gdv_equipments);  
		equipmentsAdapter = new SimpleAdapter(getApplicationContext(), 
				equipmentsArrayList, 
	            R.layout.activity_equipment_list_item,  
	            new String[] {"ItemImage","ItemNodeName","ItemDataValue","ItemArea"},   
                new int[] {R.id.img_image,R.id.tv_name,R.id.tv_datavalue,R.id.tv_area}); 
		equipmentsGridView.setAdapter(equipmentsAdapter);  
		equipmentsGridView.setOnItemClickListener(new OnItemClickListener() { 
			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap<String, Object> item=( HashMap<String, Object>) arg0.getItemAtPosition(arg2);
				String nodeId=item.get("ItemNodeId")==null ?null:item.get("ItemNodeId").toString();
				String nodeName=item.get("ItemNodeName")==null ?null:item.get("ItemNodeName").toString();
				String nodeTypeId=item.get("ItemNodeTypeId")==null ?null:item.get("ItemNodeTypeId").toString();
				String nodeTypeName=item.get("ItemNodeTypeName")==null ?null:item.get("ItemNodeTypeName").toString();
				String dataValueString=item.get("ItemDataValueString")==null ?null:item.get("ItemDataValueString").toString();
				String updateTimeString=item.get("ItemUpdateTimeString")==null ?null:item.get("ItemUpdateTimeString").toString();
				Intent intent =new Intent(getApplicationContext(),EquipmentActivity.class);
				intent.putExtra("nodeId", nodeId);
				intent.putExtra("nodeName", nodeName);
				intent.putExtra("nodeTypeId", nodeTypeId);
				intent.putExtra("nodeTypeName", nodeTypeName);
				intent.putExtra("dataValueString", dataValueString);
				intent.putExtra("updateTimeString",updateTimeString); 
				startActivity(intent);
			} 
		});
		equipmentListHttpRequestTask.execute(); 
	}
	
	private EquipmentDAO mEquipmentDAO=new EquipmentDAO(); 
	
	private Handler mHandler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg); 
			switch(msg.what){
				case 10001: 
					//正在加载
					findViewById(R.id.gdv_equipments).setVisibility(8);
					findViewById(R.id.view_loading).setVisibility(0);
					findViewById(R.id.view_loading_error).setVisibility(8);
					break; 
				case 10002:
					//加载成功
					findViewById(R.id.gdv_equipments).setVisibility(0);
					findViewById(R.id.view_loading).setVisibility(8);
					findViewById(R.id.view_loading_error).setVisibility(8);
					break;
				case 10003:
					//加载失败
					findViewById(R.id.gdv_equipments).setVisibility(8);
					findViewById(R.id.view_loading).setVisibility(8);
					findViewById(R.id.view_loading_error).setVisibility(0);
					break;
			}
		} 
	};
	
	/**
	 * 获取equipment列表
	 * */
	private class EquipmentListHttpRequestTask extends AsyncTask<String, Integer, List<EquipmentJson>>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			if(!(equipmentsAdapter!=null&&equipmentsAdapter.getCount()>0)){ 
				mHandler.sendEmptyMessage(10001);
			}
		}
		@Override
		protected List<EquipmentJson> doInBackground(String... params) {
			// TODO Auto-generated method stub   
			return mEquipmentDAO.getAll();
		}   
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values); 
		}
		@Override
		protected void onPostExecute(List<EquipmentJson> result) { 
			// TODO Auto-generated method stub  
			if(result!=null){    
				EquipmentJson equipmentEntity=null;
				BaseEquipmentEntity equipment=null;
				for(int i=0;i<result.size();i++){
					equipmentEntity= result.get(i);
					equipment= BaseEquipmentEntity.parse(equipmentEntity);
					if(equipment!=null){
						equipmentsHashMap=new HashMap<String, Object>();
						equipmentsHashMap.put("ItemImage", equipment.getIconRes());
						equipmentsHashMap.put("ItemNodeName", equipment.getName()); 
						equipmentsHashMap.put("ItemDataValue", equipment.getDataValueString()); 
						equipmentsHashMap.put("ItemArea", "未知区域"); 
						equipmentsHashMap.put("ItemNodeId",equipment.getNodeId()); 
						equipmentsHashMap.put("ItemNodeTypeId",equipment.getNodeTypeId()); 
						equipmentsHashMap.put("ItemNodeTypeName",equipment.getTypeName()); 
						equipmentsHashMap.put("ItemDataValueString",equipment.getDataValueString());
						equipmentsHashMap.put("ItemUpdateTimeString",equipment.getUpdateTimeString()); 
						equipmentsArrayList.add(equipmentsHashMap);  
					}
				}
				equipmentsAdapter.notifyDataSetChanged();    
				mHandler.sendEmptyMessage(10002);
			}
			else{    
				mHandler.sendEmptyMessage(10003);
			}
		} 
	};
	
	private void initialRoomsGallery(){  
        Gallery3D   gallery = (Gallery3D) findViewById(R.id.gal_rooms);
		ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>(); 
    	map.put("ItemImage", R.drawable.scene_gallery_0);
		map.put("ItemText", "环境"); 
		map.put("ItemContent", "打开家里的灯光，关闭安防设备"); 
    	lstImageItem.add(map); 
    	map = new HashMap<String, Object>(); 
    	map.put("ItemImage", R.drawable.scene_gallery_1);
		map.put("ItemText", "灯"); 
		map.put("ItemContent", "打开家里的灯光，关闭安防设备"); 
    	lstImageItem.add(map);
    	map = new HashMap<String, Object>(); 
    	map.put("ItemImage", R.drawable.scene_gallery_2);
		map.put("ItemText", "电视机"); 
		map.put("ItemContent", "打开家里的灯光，关闭安防设备"); 
    	lstImageItem.add(map);
		map = new HashMap<String, Object>(); 
    	map.put("ItemImage", R.drawable.scene_gallery_3);
		map.put("ItemText", "窗帘"); 
		map.put("ItemContent", "打开家里的灯光，关闭安防设备"); 
    	lstImageItem.add(map); 
        SimpleAdapter saImageItems = new SimpleAdapter(this, 
        		                                    lstImageItem, 
        		                                    R.layout.view_grallery3d_item,  
        		                                    new String[] {"ItemImage","ItemText","ItemContent"},   
        		                                    new int[] {R.id.itemImage,R.id.itemText,R.id.itemContent}); 
        
		gallery.setFadingEdgeLength(0);
        gallery.setAdapter(saImageItems);  
        gallery.setOnItemSelectedListener(new OnItemSelectedListener() {	 
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(RoomListActivity.this, "img " + (position+1) + " selected", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		gallery.setOnItemClickListener(new OnItemClickListener() {	 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(RoomListActivity.this, "img " + (position+1) + " selected", Toast.LENGTH_SHORT).show();
			}
		});
	}
}