package com.zigcloud.warehousing.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.base.BaseActivity;
import com.zigcloud.warehousing.biz.GoodsDAO;
import com.zigcloud.warehousing.entity.ControlResultJson; 

/**
 * 物品入库
 * */
public class GoodsImportActivity extends BaseActivity{

	private TextView titlebar_left;
	private TextView titlebar_title; 

	/**
	 * 物品操作业务类
	 * */
	private GoodsDAO mGoodsDAO=new GoodsDAO();
	/**
	 * 物品入库任务类
	 * */
	private GoodsImportTask mGoodsImportTask=new GoodsImportTask();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_import);
        MainApplication.getInstance().addActivity(this); 
        
 		titlebar_left = (TextView) findViewById(R.id.titlebar_left); 
 		titlebar_left.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) { 
 				onBackPressed(); 
 			}
 		});
 		titlebar_title = (TextView) findViewById(R.id.titlebar_title); 
 		titlebar_title.setText(R.string.title_goods_import); 
 		/**
 		 * 商品入库按钮事件
 		 * */
 		findViewById(R.id.btn_ok).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText edt_goods_cardId=(EditText)findViewById(R.id.edt_goods_cardId);
				EditText edt_goods_name=(EditText)findViewById(R.id.edt_goods_name);
				EditText edt_goods_address=(EditText)findViewById(R.id.edt_goods_address); 
				mGoodsImportTask=new GoodsImportTask();
				mGoodsImportTask.execute(edt_goods_cardId.getText().toString(),edt_goods_name.getText().toString(),edt_goods_address.getText().toString(),"admin");
			}
		});
	}
	
	/**
	 * 物品入库任务类
	 * */
	private class GoodsImportTask extends AsyncTask<String, Integer, ControlResultJson>{

		@Override
		protected ControlResultJson doInBackground(String... params) {
			// TODO Auto-generated method  
			if(params!=null&&params.length>3){
				String cardNum=params[0];
				String dataName=params[1];
				String originPlace=params[2];
				String staffName=params[3];
				if(cardNum!=null&&dataName!=null&&originPlace!=null&&staffName!=null)
					return mGoodsDAO.goodsImport(cardNum, dataName, originPlace, staffName); 
			}
			return null;
		}

		@Override
		protected void onPostExecute(ControlResultJson result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			String resStr=null;
			if(result!=null){
				resStr=result.flag==0?"添加成功！":"添加失败";
			}
			else{
				resStr="添加失败";
			}
			Toast.makeText(GoodsImportActivity.this, resStr, Toast.LENGTH_SHORT).show();
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
	}
}
