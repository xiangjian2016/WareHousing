package com.zigcloud.warehousing.biz;

import com.zigcloud.warehousing.entity.ControlResultJson;
import com.zigcloud.warehousing.entity.GoodsJson;
import com.zigcloud.warehousing.util.HttpUtil;
import com.zigcloud.warehousing.util.MyURL;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.List;

/**
 * 物品业务处理类
 * */
public class GoodsDAO extends BaseDAO { 
 
	/**
	 * 获取所有物品
	 * */
	public List<GoodsJson> getAll(){
		
		List<GoodsJson> goodsEntityList = null;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/storage_data.cgi?method=selAll");
			goodsEntityList = mObjectMapper.readValue(result,
					new TypeReference<List<GoodsJson>>() {
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
		return goodsEntityList; 
	}
	/**
	 * 根据Id获取物品
	 * */
	public GoodsJson get(String id){
		
		GoodsJson goodsEntity;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/model.cgi?method=getModelById&id="+id);
			goodsEntity = mObjectMapper.readValue(result,
					new TypeReference<GoodsJson>() {
					}); 
			return goodsEntity;
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
	 * 根据CardId获取物品
	 * */
	public GoodsJson getByCardId(String cardNum){
		
		GoodsJson goodsEntity;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/storage_data.cgi?method=selByCardNum&cardNum="+cardNum);
			goodsEntity = mObjectMapper.readValue(result,
					new TypeReference<GoodsJson>() {
					}); 
			return goodsEntity;
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
	 * 物品入库
	 * */
	public ControlResultJson goodsImport(String cardNum,String dataName,String originPlace,String staffName){
		
		ControlResultJson controlResultEntity;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/storage_data.cgi?method=put&cardNum="+cardNum+"&dataName="+dataName+"&originPlace="+originPlace+"&staffName="+staffName+"&putTime=2014:1:8:12:00:00");
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
	/**
	 * 物品出库
	 * */
	public ControlResultJson goodsOutport(String cardId){
		
		ControlResultJson controlResultEntity;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/storage_data.cgi?method=del&cardNum="+cardId);
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
