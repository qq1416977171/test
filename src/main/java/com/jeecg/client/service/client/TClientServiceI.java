package com.jeecg.client.service.client;

import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.service.CommonService;

import com.jeecg.client.entity.client.TClientEntity;



public interface TClientServiceI extends CommonService{
	public String uploadClient(UploadFile uploadFile,TClientEntity tClient);
	
	public void saveClient(TClientEntity tClient);
	
	public void activeClient(TClientEntity tClient);
	
	public void delClient(TClientEntity tClient);
}
