package com.zigcloud.warehousing.entity.equipment;
 
import com.zigcloud.warehousing.entity.EquipmentJson;
import com.zigcloud.warehousing.entity.equipment.base.BaseEquipmentEntity;

/**
 * 床灯
 * */
public class BedLampEntity extends BaseEquipmentEntity {
	 
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
		typeName="床灯";
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
			if(equipmentJson.appData.equipDatas.get(0)!=null){
				String dataValue=equipmentJson.appData.equipDatas.get(0).dataValue!=null&&!equipmentJson.appData.equipDatas.get(0).dataValue.equals("00")?"关":"开";
				dataValueString+=String.format("%s:%s;", "状态",dataValue);
			}
		}
		return dataValueString;
	}

	@Override
	public String getUpdateTimeString() {
		// TODO Auto-generated method stub
		return super.getUpdateTimeString();
	}
}
