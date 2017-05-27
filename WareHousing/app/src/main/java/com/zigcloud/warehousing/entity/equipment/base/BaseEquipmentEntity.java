package com.zigcloud.warehousing.entity.equipment.base;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.entity.EquipmentJson;
import com.zigcloud.warehousing.entity.equipment.AirQualityEntity;
import com.zigcloud.warehousing.entity.equipment.AlarmEntity;
import com.zigcloud.warehousing.entity.equipment.BedLampEntity;
import com.zigcloud.warehousing.entity.equipment.CurtainEntity;
import com.zigcloud.warehousing.entity.equipment.FanEntity;
import com.zigcloud.warehousing.entity.equipment.GasEntity;
import com.zigcloud.warehousing.entity.equipment.IlluminationEntity;
import com.zigcloud.warehousing.entity.equipment.InfraredEntity;
import com.zigcloud.warehousing.entity.equipment.LampEntity;
import com.zigcloud.warehousing.entity.equipment.TempHumiEntity;
import com.zigcloud.warehousing.entity.equipment.WallLampEntity;
/**
 * 设备基类
 * */
public class BaseEquipmentEntity {
	
	/**
	 * json数据
	 * */
	protected EquipmentJson equipmentJson; 
	/**
	 * 节点编号
	 * */
	protected String nodeId;
	/**
	 * 节点类型
	 * */
	protected String nodeTypeId;
	/**
	 * 类型名称
	 * */
	protected String typeName;  
	/**
	 * 设备名称
	 * */
	protected String name;
	/**
	 * 图标
	 * */
	protected int iconRes;
	/**
	 * 图片
	 * */
	protected int imageRes;
	/**
	 * 数据(字符串)
	 * */
	protected String dataValueString;
	/**
	 * 更新时间(字符串)
	 * */
	protected String updateTimeString;
	
	
	public static BaseEquipmentEntity parse(EquipmentJson equipmentJson){
		BaseEquipmentEntity equipment=null; 
		if(equipmentJson!=null){
			if(equipmentJson.nodeTypeId.equals("01")){
				equipment=new InfraredEntity(); 
			}
			else if(equipmentJson.nodeTypeId.equals("02")){
				equipment=new IlluminationEntity(); 
			}
			else if(equipmentJson.nodeTypeId.equals("04")){
				equipment=new GasEntity(); 
			}
			else if(equipmentJson.nodeTypeId.equals("06")){
				equipment=new TempHumiEntity(); 
			}
			else if(equipmentJson.nodeTypeId.equals("07")){
				equipment=new AirQualityEntity(); 
			}
			else if(equipmentJson.nodeTypeId.equals("13")){
				equipment=new BaseEquipmentEntity(); 
			}    
			else if(equipmentJson.nodeTypeId.equals("20")){
				equipment=new CurtainEntity(); 
			} 
			else if(equipmentJson.nodeTypeId.equals("24")){
				equipment=new LampEntity(); 
			} 
			else if(equipmentJson.nodeTypeId.equals("25")){
				equipment=new BedLampEntity(); 
			} 
			else if(equipmentJson.nodeTypeId.equals("26")){
				equipment=new WallLampEntity(); 
			} 
			else if(equipmentJson.nodeTypeId.equals("27")){
				equipment=new AlarmEntity(); 
			}  
			else if(equipmentJson.nodeTypeId.equals("29")){
				equipment=new FanEntity(); 
			}
			else{
				equipment=new BaseEquipmentEntity(); 
			}
			equipment.equipmentJson=equipmentJson; 
		}
		return  equipment;
	}

	public EquipmentJson getEquipmentJson() {
		return equipmentJson;
	}

	public String getNodeId() {
		if(equipmentJson!=null)
			nodeId=equipmentJson.nodeId;
		return nodeId;
	}  
	
	public String getNodeTypeId() {
		if(equipmentJson!=null)
			nodeTypeId=equipmentJson.nodeTypeId;
		return nodeTypeId; 
	}

	public String getTypeName() {
		typeName="未知";
		return typeName;
	}

	public String getName() {
		name=getTypeName()+getNodeId();
		return name;
	}

	public int getIconRes() {
		return R.drawable.icon_equip_base_0;
	}

	public int getImageRes() {
		return imageRes;
	}

	public String getDataValueString() {
		if(equipmentJson!=null&&equipmentJson.appData!=null&&equipmentJson.appData.equipDatas!=null){
			dataValueString="";
			for(int i=0;i<equipmentJson.appData.equipDatas.size();i++){
				if(equipmentJson.appData.equipDatas.get(i)!=null)dataValueString+=String.format("%s:%s;", "数值",equipmentJson.appData.equipDatas.get(i).dataValue);
			}
		}
		return dataValueString;
	}

	public String getUpdateTimeString() {
		if(equipmentJson!=null)
			updateTimeString=equipmentJson.currentTime;
		return updateTimeString;  
	}
}
