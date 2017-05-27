package com.zigcloud.warehousing.biz;

import com.zigcloud.warehousing.entity.ControlResultJson;
import com.zigcloud.warehousing.entity.ModelJson;
import com.zigcloud.warehousing.util.HttpUtil;
import com.zigcloud.warehousing.util.MyURL;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.List;

/**
 * 情景模式业务处理类
 * */
public class ModelDAO extends BaseDAO { 
 
	/**
	 * 获取所有情景模式
	 * */
	public List<ModelJson> getAll(){
		
		List<ModelJson> modelEntityList = null;
		try{
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/model.cgi?method=getModels");
			modelEntityList = mObjectMapper.readValue(result,
					new TypeReference<List<ModelJson>>() {
					});  
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelEntityList; 
	}
	/**
	 * 根据Id获取模式
	 * */
	public ModelJson get(String id){
		
		ModelJson modelEntity;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/model.cgi?method=getModelById&id="+id);
			modelEntity = mObjectMapper.readValue(result,
					new TypeReference<ModelJson>() {
					}); 
			return modelEntity;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	/**
	 * 获取当前选择的模式
	 * */
	public ModelJson getCurrentModel(){
		
		ModelJson modelEntity;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/model.cgi?method=getCurrentModel");
			modelEntity = mObjectMapper.readValue(result,
					new TypeReference<ModelJson>() {
					}); 
			return modelEntity;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	} 
	/**
	 * 改变当前模式（根据Id）
	 * */
	public ControlResultJson changeModelById(String id){
		
		ControlResultJson controlResultEntity;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/model.cgi?method=changeModelById&id="+id);
			controlResultEntity = mObjectMapper.readValue(result,
					new TypeReference<ControlResultJson>() {
					}); 
			return controlResultEntity;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	} 
}
