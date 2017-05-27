package com.zigcloud.warehousing.entity.equipment;

import com.zigcloud.warehousing.entity.EquipmentJson;
import com.zigcloud.warehousing.entity.equipment.base.BaseEquipmentEntity;

/**
 * 燃气探测器
 * */
public class GasEntity extends BaseEquipmentEntity{

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
		typeName="燃气探测器";
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
		// TODO Auto-generated method stub 	
		if(equipmentJson!=null&&equipmentJson.appData!=null&&equipmentJson.appData.equipDatas!=null){
			dataValueString="";
			if(equipmentJson.appData.equipDatas.get(0)!=null)dataValueString+=String.format("%s:%s;", "燃气",equipmentJson.appData.equipDatas.get(0).dataValue); 
		}
		return dataValueString;
	}

	@Override
	public String getUpdateTimeString() {
		// TODO Auto-generated method stub
		return super.getUpdateTimeString();
	}

}
