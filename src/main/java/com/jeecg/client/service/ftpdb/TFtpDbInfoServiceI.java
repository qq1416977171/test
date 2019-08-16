package com.jeecg.client.service.ftpdb;

import org.jeecgframework.core.common.service.CommonService;

import com.jeecg.client.entity.ftpdb.TFtpDbInfoEntity;

public interface TFtpDbInfoServiceI extends CommonService{

	public void saveFtp(TFtpDbInfoEntity tFtpDbInfo);
}
