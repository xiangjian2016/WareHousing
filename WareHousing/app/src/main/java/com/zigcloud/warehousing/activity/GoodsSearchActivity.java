package com.zigcloud.warehousing.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.base.BaseActivity;
import com.zigcloud.warehousing.biz.GoodsDAO;
import com.zigcloud.warehousing.entity.GoodsJson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 物品查询
 * */
public class GoodsSearchActivity extends BaseActivity{
	
	private TextView titlebar_left;
	private TextView titlebar_title; 
	
	private GridView goodsGridView;  
	private ArrayList<HashMap<String, Object>> goodsArrayList = new ArrayList<HashMap<String, Object>>();
	private HashMap<String, Object> goodsHashMap = new HashMap<String, Object>(); 
	private SimpleAdapter goodsAdapter;
	/**
	 * 物品业务处理类
	 * */
	private GoodsDAO mGoodsDAO=new GoodsDAO();
	/**
	 * 获取物品信息任务类
	 * */
	private GoodsRequestTask mGoodsRequestTask=new GoodsRequestTask(); 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_search);
        MainApplication.getInstance().addActivity(this); 
        
 		titlebar_left = (TextView) findViewById(R.id.titlebar_left); 
 		titlebar_left.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) { 
 				onBackPressed(); 
 			}
 		});
 		titlebar_title = (TextView) findViewById(R.id.titlebar_title); 
 		titlebar_title.setText(R.string.title_goods_search); 
 		/**
 		 * 查询监听器
 		 * */
 		findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() { 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText edt_cardId=(EditText)findViewById(R.id.edt_cardid);
				mGoodsRequestTask=new GoodsRequestTask();
				mGoodsRequestTask.execute(edt_cardId.getText().toString());
			}
		});
 		initialGoodsGridView();
	}
	
	/**
	 * 初始化物品列表
	 * */
	private void initialGoodsGridView(){
		goodsGridView=(GridView) findViewById(R.id.gridView1);  
		goodsAdapter = new SimpleAdapter(getApplicationContext(), 
				goodsArrayList, 
	            R.layout.activity_goods_list_item,  
	            new String[] {"Image","goodsCardId","goodsName"},   
	            new int[] {R.id.itemImage,R.id.tv_goods_cardid,R.id.tv_goods_name}); 
		goodsGridView.setAdapter(goodsAdapter);  
		goodsGridView.setOnItemClickListener(new OnItemClickListener() {   
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) { 
				
			} 
		}); 
	} 
	
	/**
	 * 获取物品信息任务类
	 * */
	private class GoodsRequestTask extends AsyncTask<String, Integer, GoodsJson>{

		@Override
		protected GoodsJson doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(params!=null&&params.length>0)
				return mGoodsDAO.getByCardId(params[0]);
			return null;
		}

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
		protected void onPostExecute(GoodsJson result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			goodsArrayList.clear();
			if(result!=null){    
				goodsHashMap=new HashMap<String, Object>();
				goodsHashMap.put("Image", R.drawable.metro_home_blacks_scan_code);
				goodsHashMap.put("goodsCardId", result.cardNum); 
				goodsHashMap.put("goodsName", result.dataName); 
				goodsHashMap.put("goodsAddress",result.originPlace); 
				goodsHashMap.put("goodsUser", result.staffName);
				goodsArrayList.add(goodsHashMap);  
			} 
			goodsAdapter.notifyDataSetChanged();
		}  
	}
}
