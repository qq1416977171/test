package com.jeecg.client.service.impl.ftpdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.client.entity.ftpdb.TFtpDbInfoEntity;
import com.jeecg.client.service.ftpdb.TFtpDbInfoServiceI;
import com.jeecg.client.util.Des;

import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tFtpDbInfoService")
@Transactional
public class TFtpDbInfoServiceImpl extends CommonServiceImpl implements TFtpDbInfoServiceI {
	
	@Autowired
	private JdbcDao jdbcDao;
	public void saveFtp(TFtpDbInfoEntity tFtpDbInfo){
		String sql = "delete from t_ftp_db_info where client_type = '"+tFtpDbInfo.getClientType()+"'";
		jdbcDao.update(sql);
		//String fpassword = tFtpDbInfo.getFtpPassword();
		//String dpassword = tFtpDbInfo.getDbPassword();
		
		this.save(tFtpDbInfo);
	}
}