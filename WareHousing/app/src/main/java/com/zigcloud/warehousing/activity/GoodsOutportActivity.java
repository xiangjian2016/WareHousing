package com.zigcloud.warehousing.activity;

import java.util.ArrayList;
import java.util.HashMap; 
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle; 
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;  
import android.widget.Toast;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.base.BaseActivity; 
import com.zigcloud.warehousing.biz.GoodsDAO;
import com.zigcloud.warehousing.entity.ControlResultJson;
import com.zigcloud.warehousing.entity.GoodsJson; 

/**
 * 物品出库
 * */
public class GoodsOutportActivity extends BaseActivity{
	
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
	/**
	 * 获取物品出库
	 * */
	private GoodsOutportTask mGoodsOutportTask=new GoodsOutportTask();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_outport);
        MainApplication.getInstance().addActivity(this); 
        
 		titlebar_left = (TextView) findViewById(R.id.titlebar_left); 
 		titlebar_left.setOnClickListener(new View.OnClickListener() {
 			
 			@Override
 			public void onClick(View v) { 
 				onBackPressed(); 
 			}
 		});
 		titlebar_title = (TextView) findViewById(R.id.titlebar_title); 
 		titlebar_title.setText(R.string.title_goods_outport); 
 		initialGoodsGridView();
	}
	
	/**
	 * 初始化情景模式列表
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
			@SuppressWarnings({ "unchecked" })
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap<String, Object> item=( HashMap<String, Object>) arg0.getItemAtPosition(arg2); 
				final String goodsCardId= item.get("goodsCardId").toString();
				// TODO Auto-generated method stub
				AlertDialog.Builder builder=new AlertDialog.Builder(GoodsOutportActivity.this) 
					.setIcon(R.drawable.ic_launcher) 
					.setItems(new String[]{"出库"},new DialogInterface.OnClickListener() { 
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							switch(which){
								case 0:  
									mGoodsOutportTask=new GoodsOutportTask();
									mGoodsOutportTask.execute(goodsCardId); 
									break; 
							}
						}
					}); 
				builder.create().show();
			} 
		});
		mGoodsRequestTask.execute();
	} 
	
	/**
	 * 获取物品信息任务类
	 * */
	private class GoodsRequestTask extends AsyncTask<String, Integer, List<GoodsJson>>{

		@Override
		protected List<GoodsJson> doInBackground(String... params) {
			// TODO Auto-generated method stub
			return mGoodsDAO.getAll();
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
		protected void onPostExecute(List<GoodsJson> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null){
				goodsArrayList.clear();
				GoodsJson goodsEntity=null;
				for(int i=0;i<result.size();i++){
					goodsEntity= result.get(i);
					if(goodsEntity!=null){
						goodsHashMap=new HashMap<String, Object>();
						goodsHashMap.put("Image", R.drawable.metro_home_blacks_scan_code);
						goodsHashMap.put("goodsCardId", goodsEntity.cardNum); 
						goodsHashMap.put("goodsName", goodsEntity.dataName); 
						goodsHashMap.put("goodsAddress",goodsEntity.originPlace); 
						goodsHashMap.put("goodsUser", goodsEntity.staffName);
						goodsArrayList.add(goodsHashMap);  
					}
				}
				goodsAdapter.notifyDataSetChanged();
			}
			else{ 
				findViewById(R.id.view_loading_error).setVisibility(0);
			}
		}  
	}
	
	/**
	 * 物品出库
	 * */ 
	private class GoodsOutportTask extends AsyncTask<String, Integer, ControlResultJson>{

		@Override
		protected ControlResultJson doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(params!=null&&params.length>0)
				return mGoodsDAO.goodsOutport(params[0]);
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
		protected void onPostExecute(ControlResultJson result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result); 
			String resStr=null;
			if(result!=null){
				resStr=result.flag==0?"出库成功！":"出库失败";
			}
			else{
				resStr="出库失败";
			}
			mGoodsRequestTask=new GoodsRequestTask();
			mGoodsRequestTask.execute();
			Toast.makeText(GoodsOutportActivity.this, resStr, Toast.LENGTH_SHORT).show();
		}  
	}
}
