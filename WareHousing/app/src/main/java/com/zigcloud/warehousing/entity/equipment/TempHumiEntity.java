package com.zigcloud.warehousing.entity.equipment;

import com.zigcloud.warehousing.entity.EquipmentJson;
import com.zigcloud.warehousing.entity.equipment.base.BaseEquipmentEntity;

/**
 * 温湿度探测器
 * */
public class TempHumiEntity extends BaseEquipmentEntity{

	@Override
	public EquipmentJson getEquipmentJson() {
		// TODO Auto-generated method stub
		return super.getEquipmentJson();
	}

	@Override
	public String getNodeId() {
		// TODO Auto-generated method stub
		return super.getNodeId();
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		typeName="温湿度探测器";
		return typeName;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public int getIconRes() {
		// TODO Auto-generated method stub
		return super.getIconRes();
	}

	@Override
	public int getImageRes() {
		// TODO Auto-generated method stub
		return super.getImageRes();
	}

	@Override
	public String getDataValueString() {
		if(equipmentJson!=null&&equipmentJson.appData!=null&&equipmentJson.appData.equipDatas!=null){
			dataValueString="";
			if(equipmentJson.appData.equipDatas.get(0)!=null)dataValueString+=String.format("%s:%s;", "温度",equipmentJson.appData.equipDatas.get(0).dataValue);
			if(equipmentJson.appData.equipDatas.get(1)!=null)dataValueString+=String.format("%s:%s;", "湿度",equipmentJson.appData.equipDatas.get(1).dataValue);
		}
		return dataValueString;
	}

	@Override
	public String getUpdateTimeString() {
		// TODO Auto-generated method stub
		return super.getUpdateTimeString();
	} 

}
