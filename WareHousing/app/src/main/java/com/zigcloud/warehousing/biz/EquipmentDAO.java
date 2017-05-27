package com.zigcloud.warehousing.biz;

import com.zigcloud.warehousing.entity.ControlResultJson;
import com.zigcloud.warehousing.entity.EquipmentJson;
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
public class EquipmentDAO extends BaseDAO { 
 
	/**
	 * 获取所有情景模式
	 * */
	public List<EquipmentJson> getAll(){
		
		List<EquipmentJson> equipmentEntityList = null;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/node.cgi?method=get");
			equipmentEntityList = mObjectMapper.readValue(result,
					new TypeReference<List<EquipmentJson>>() {
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
		return equipmentEntityList; 
	}
	/**
	 * 根据Id获取模式
	 * */
	public EquipmentJson getByNodeId(String nodeId){
		
		EquipmentJson equipmentEntity;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/node.cgi?method=getByNodeId&nodeId="+nodeId);
			equipmentEntity = mObjectMapper.readValue(result,
					new TypeReference<EquipmentJson>() {
					}); 
			return equipmentEntity;
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
	 * 发送控制指令
	 * */
	public ControlResultJson sendCmd(String nodeId,String stateFlag,String stateValue){
		
		ControlResultJson controlResultJson;
		try {
			String result =HttpUtil.getRequest(MyURL.GATEWAY_BASE_URL+"/bin/node.cgi?method=sendCmd&nodeId="+nodeId+"&stateFlag="+stateFlag+"&stateValue="+stateValue);
			controlResultJson = mObjectMapper.readValue(result,
					new TypeReference<ControlResultJson>() {
					}); 
			return controlResultJson;
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
