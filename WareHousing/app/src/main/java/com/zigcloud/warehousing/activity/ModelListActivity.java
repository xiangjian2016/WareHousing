package com.zigcloud.warehousing.activity;

import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.base.BaseActivity;
import com.zigcloud.warehousing.adapter.RadioAdapter;
import com.zigcloud.warehousing.biz.ModelDAO;
import com.zigcloud.warehousing.entity.ControlResultJson;
import com.zigcloud.warehousing.entity.ModelJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 情景模式
 * */
public class ModelListActivity extends BaseActivity{ 
		
	private TextView titlebar_left;
	private TextView titlebar_title;
	
	private GridView modelsGridView;  
	private ArrayList<HashMap<String, Object>> modelsArrayList = new ArrayList<HashMap<String, Object>>();
	private HashMap<String, Object> modelsHashMap = new HashMap<String, Object>(); 
	private RadioAdapter modelsAdapter;
	/**
	 * 获取模式列表
	 * */
	private AsyncTask<String, Integer, ModelListHttpRequestTask.Result> modelListHttpRequestTask=new ModelListHttpRequestTask(); 
	/**
	 * 切换当前模式
	 * */
	private AsyncTask<String, Integer, ControlResultJson> changeModelHttpRequestTask=new ChangeModelHttpRequestTask(); 
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_model_list);
        MainApplication.getInstance().addActivity(this);
        
		titlebar_left = (TextView) findViewById(R.id.titlebar_left); 
		titlebar_left.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) { 
				onBackPressed(); 
			}
		});
		titlebar_title = (TextView) findViewById(R.id.titlebar_title); 
		titlebar_title.setText(R.string.title_models);
		
		initialModelsGridView();
    }  
	/**
	 * 初始化情景模式列表
	 * */
	private void initialModelsGridView(){
		modelsGridView=(GridView) findViewById(R.id.gdv_models);  
		modelsAdapter = new RadioAdapter(getApplicationContext(), 
				modelsArrayList, 
	            R.layout.activity_model_list_item,  
	            new String[] {"ItemImage","ItemText","ItemContent","ItemId"},   
	            new int[] {R.id.itemImage,R.id.itemText,R.id.itemContent},
	            R.drawable.metro_home_blocks,
	            R.drawable.metro_home_blocks_hover); 
		modelsGridView.setAdapter(modelsAdapter);  
		modelsGridView.setOnItemClickListener(new OnItemClickListener() { 
			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				HashMap<String, Object> item=( HashMap<String, Object>) arg0.getItemAtPosition(arg2);
				if(item!=null&&item.get("ItemId")!=null&&item.get("ItemId").toString()!=null){
					 //change model 
					if(changeModelHttpRequestTask.getStatus()!=Status.RUNNING){
						changeModelHttpRequestTask=new ChangeModelHttpRequestTask() ;
						changeModelHttpRequestTask.execute(String.valueOf(arg2),item.get("ItemId").toString()); 
						modelsAdapter.setSeclectItemPosition(arg2);
						modelsAdapter.notifyDataSetChanged();
					}
				}
			} 
		});
        modelListHttpRequestTask.execute();
	} 
	
	private ModelDAO modelDAO=new ModelDAO(); 
	/**
	 * 获取模式列表
	 * */
	private class ModelListHttpRequestTask extends AsyncTask<String, Integer, ModelListHttpRequestTask.Result>{

		@Override
		protected Result doInBackground(String... params) {
			// TODO Auto-generated method stub 
			ModelJson currenModelEntity=modelDAO.getCurrentModel();
			String currentModelId=currenModelEntity==null?null:currenModelEntity.id;
			return new Result(currentModelId,modelDAO.getAll());
		}   
		@Override
		protected void onPostExecute(Result result) {
			// TODO Auto-generated method stub  
			if(result!=null&& result.getItems()!=null){    
				ModelJson modelEntity=null;
				for(int i=0;i<result.getItems().size();i++){
					modelEntity= result.getItems().get(i);
					if(modelEntity!=null){
						modelsHashMap=new HashMap<String, Object>();
						modelsHashMap.put("ItemImage", R.drawable.metro_home_blacks_scan_code);
						modelsHashMap.put("ItemText", modelEntity.name); 
						modelsHashMap.put("ItemContent", modelEntity.description); 
						modelsHashMap.put("ItemId", modelEntity.id); 
						modelsArrayList.add(modelsHashMap); 
						if(modelEntity.id.equals(result.selectedId)){
							modelsAdapter.setSeclectItemPosition(i);
						}
					}
				}
				modelsAdapter.notifyDataSetChanged();
			}
			else{ 
				findViewById(R.id.view_loading_error).setVisibility(0);
			}
		} 
		
		/**
		 * 获取模式的返回结果
		 * */
		public class Result{
			/**
			 * 当前选中的模式
			 * */
			public String  selectedId;
			/**
			 * 模式列表
			 * */
			public List<ModelJson> items;
			
			public Result(String selectedId,List<ModelJson> items){
				this.selectedId=selectedId;
				this.items=items;
			} 
			@SuppressWarnings("unused")
			public String getSelectedId() {
				return selectedId;
			}
			@SuppressWarnings("unused")
			public void setSelectedId(String selectedId) {
				this.selectedId = selectedId;
			}
			public List<ModelJson> getItems() {
				return items;
			}
			@SuppressWarnings("unused")
			public void setItems(List<ModelJson> items) {
				this.items = items;
			} 
		} 
	} 
	
	/**
	 * 切换当前模式
	 * */
	private class ChangeModelHttpRequestTask extends  AsyncTask<String, Integer, ControlResultJson>{
		@Override
		protected ControlResultJson doInBackground(
				String... params) {
			// TODO Auto-generated method stub
			if(params!=null&&params.length>1){ 
				return modelDAO.changeModelById(params[1]);
			}
			return null; 
		} 
		
		protected void onPostExecute(ControlResultJson result) {
			modelsAdapter.notifyDataSetInvalidated();
		}
	}
}