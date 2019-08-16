package com.jeecg.client.service.impl.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.client.entity.client.TClientEntity;
import com.jeecg.client.entity.client.TClientFileEntity;
import com.jeecg.client.ftp.FtpUtil;
import com.jeecg.client.service.client.TClientFileServiceI;

import java.util.List;

import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.service.SystemService;

@Service("tClientFileService")
@Transactional
public class TClientFileServiceImpl extends CommonServiceImpl implements TClientFileServiceI {
	@Autowired
	private JdbcDao jdbcDao;
	
	@Autowired
	private SystemService systemService;
	
	public void syncFile(String clientId){
		//String dsql = "delete from t_client_file where client_id='"+clientId+"'";
		//jdbcDao.update(dsql);
        	TClientEntity tClient = systemService.getEntity(TClientEntity.class, clientId);
    		FtpUtil ftp = FtpUtil.getInstance();
    		ftp.cleanList();
    		ftp.getAllFileAnDic(tClient.getClientName(),clientId);
    		List<TClientFileEntity> list = ftp.getList();
    		
    		for(TClientFileEntity str:list){
    			
    				//System.out.println(str.getId()+","+str.getClientId()+","+str.getFileName()+","+str.getTClientFileEntity().getId());	
    				String isroot = "0";
    				if(str.getTClientFileEntity().getId()==null){
    					isroot = "1";
    				}
    				String insql="insert into t_client_file (id,client_id,file_name,parentid,is_root) values ('"+str.getId()+"','"+str.getClientId()+"','"+str.getFileName()+"','"+str.getTClientFileEntity().getId()+"','"+isroot+"')";
    				jdbcDao.update(insql);
    			
    		}
    		
    		ftp.cleanList();
	}
}