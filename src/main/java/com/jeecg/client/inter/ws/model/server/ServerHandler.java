package com.jeecg.client.inter.ws.model.server;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.client.entity.client.TClientStatusEntity;
import com.jeecg.client.entity.log.TClientServiceLogEntity;
import com.jeecg.client.inter.ws.BaseHandler;
import com.jeecg.client.inter.ws.Request;
import com.jeecg.client.inter.ws.Result;
import com.jeecg.client.inter.ws.base.TransInfo;
import com.jeecg.client.inter.ws.utils.ConstantUtil;
import com.jeecg.client.util.Des;



@Service("ServerHandler")
@Transactional
public class ServerHandler implements BaseHandler {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ServerHandler.class);
	
	@Autowired
	private SystemService systemService;
	
	//@Autowired
	//private org.jeecgframework.web.system.service.SystemService jdbcService;
	
	@Override
	public Object handle(TransInfo transInfo, Request reqObj) {
		Result result = null;
		String requestCode = transInfo.getRequestCode();
		String serverSign = null;
		String status = null;
		ServerRequestData serverDataInfo = (ServerRequestData) reqObj.getData();
		String clientType = serverDataInfo.getClientType();
		String ctype = serverDataInfo.getClientType();
		String clientVersion = serverDataInfo.getCurVersion();
		String cip = serverDataInfo.getIpAddr();
		String cvn = serverDataInfo.getCurVersionNo();
		try {
			// 设定result数据结构
			result = new Result();
			result.setRequestCode(transInfo.getTransCode());
			result.setReturnMsg("");
			systemService = ApplicationContextUtil.getContext().getBean(SystemService.class);
		
			if("".equals(clientType)||clientType==null||"".equals(clientVersion)||clientVersion==null||"".equals(cip)||cip==null||"".equals(cvn)||cvn==null){
				logger.error("参数错误");
				result.setReturnCode(ConstantUtil.PARAM_ERROR);
				result.setReturnMsg("参数错误");
				TClientServiceLogEntity entity = new TClientServiceLogEntity();
				entity.setClientIp(cip);
				entity.setClientType(ctype);
				entity.setCreateTime(new Date());
				entity.setCurrentVersion(clientVersion);
				//entity.setInterXml(in);
				entity.setLogType("0");
				entity.setFuncName("客户端接口访问");
				
				entity.setResult("参数错误");
				systemService.save(entity);
				//String id = UUID.randomUUID().toString().replaceAll("-", "");
				//String sql="insert into campus_station (id,phone) values ('"+id+"','"+phone+"')";
				///String sql = "insert into t_client_service_log(id,client_ip,client_type,current_version,result) values('"+id+"','"+cip+"','"+ctype+"','"+clientVersion+"','参数错误')";
				//systemService.updateBySqlString(sql);
				return result;
			}
			
			/*if(Integer.parseInt(clientType)>2||Integer.parseInt(clientType)<1){
				result.setReturnCode(ConstantUtil.PARAM_ERROR);
				result.setReturnMsg("参数错误");
				TClientServiceLogEntity entity = new TClientServiceLogEntity();
				entity.setClientIp(cip);
				entity.setClientType(ctype);
				entity.setCreateTime(new Date());
				entity.setCurrentVersion(clientVersion);
				entity.setResult("参数错误");
				systemService.save(entity);
				return result;
			}*/
			
			String xsql = "select t.code code from t_client_type t where t.order_num='"+clientType+"'";
			
			List<Map<String, Object>> yxList =  systemService.findForJdbc(xsql);
			if(yxList!=null&&yxList.size()>0){
				clientType = yxList.get(0).get("code").toString();
			}
			
			//String clientsql = "select t.id from t_client t where t.CLIENT_TYPE = '"+clientType+"' and t.VER_CODE ='"+clientVersion+"'";
			String clientsql = "select t.CLIENT_TYPE clientType,t.CLIENT_NAME filePath,t.VER_CODE verCode,t.VER_CODE_NO verCodeNo from t_client t where t.STATUS='1' and t.CLIENT_TYPE = '"+clientType+"' and (t.VER_CODE_NO+0) = (select max(a.VER_CODE_NO+0) maxNo from t_client a where a.CLIENT_TYPE='"+clientType+"' and a.status='1')";
			List<Map<String, Object>> clientlist = systemService.findForJdbc(clientsql);
			if(clientlist.size()<1){
				result.setReturnCode(ConstantUtil.OTHER_ERROR);
				result.setReturnMsg("没有最新版本的客户端");
				
				TClientServiceLogEntity entity = new TClientServiceLogEntity();
				entity.setClientIp(cip);
				entity.setClientType(ctype);
				entity.setCreateTime(new Date());
				entity.setCurrentVersion(clientVersion);
				entity.setResult("没有最新版本的客户端");
				//entity.setInterXml(in);
				entity.setLogType("0");
				entity.setFuncName("客户端接口访问");
				systemService.save(entity);
				
				logger.info("[" + requestCode + "]==>" + "没有最新版本的客户端");
				return result;
			}
			
			List<TClientStatusEntity> statusList = systemService.findByProperty(TClientStatusEntity.class, "clientType", clientType);
			if(statusList!=null&&statusList.size()>0){
				TClientStatusEntity entity = statusList.get(0);
				if("0".equals(entity.getStatus())){
					result.setReturnCode(ConstantUtil.CLIENT_UPGRADE_ING);
					result.setReturnMsg("系统在升级版本");
					logger.info("[" + requestCode + "]==>" + "系统在升级版本");
					
					TClientServiceLogEntity entity11 = new TClientServiceLogEntity();
					entity11.setClientIp(cip);
					entity11.setClientType(ctype);
					entity11.setCreateTime(new Date());
					entity11.setCurrentVersion(clientVersion);
					entity11.setResult("系统在升级版本");
					//entity11.setInterXml(in);
					entity11.setLogType("0");
					entity11.setFuncName("客户端接口访问");
					systemService.save(entity11);
					
					return result;
				}else{
					
					
					
					String sql = "select IFNULL(t.ftp_ip,'') ftpIp,IFNULL(t.ftp_port,'') ftpPort,IFNULL(t.ftp_user_name,'') ftpUserName,IFNULL(t.ftp_password,'') ftpPassword,IFNULL(t.db_port,'') dbPort,IFNULL(t.db_name,'') dbName,IFNULL(t.db_user_name,'') dbUserName,IFNULL(t.db_password,'') dbPassword,IFNULL(t.db_ip,'') dbIp,IFNULL(t.pass_post_fix,'') passPostFix from  t_ftp_db_info t where t.client_type='"+ctype+"'";
					List<Map<String, Object>> list = systemService.findForJdbc(sql);
					if(list!=null&&list.size()>0){
						
						String filePath=clientlist.get(0).get("filePath").toString();
						String verCode=clientlist.get(0).get("verCode").toString();
						String verCodeNo=clientlist.get(0).get("verCodeNo").toString();
						
						String  ftpIp = list.get(0).get("ftpIp").toString();
						String  ftpPort = list.get(0).get("ftpPort").toString();
						String  ftpUserName = list.get(0).get("ftpUserName").toString();
						String  ftpPassword = list.get(0).get("ftpPassword").toString();
						//ftpPassword = Des.jiami(ftpPassword);
						String  dbIp = list.get(0).get("dbIp").toString();
						String  dbPort = list.get(0).get("dbPort").toString();
						String  dbName = list.get(0).get("dbName").toString();
						String  dbUserName = list.get(0).get("dbUserName").toString();
						String  dbPassword = list.get(0).get("dbPassword").toString();
						String  passPostFix = list.get(0).get("passPostFix").toString();
						//String path = "/"+clientType+"/"+clientVersion;
						ServerResult propInfo = new ServerResult();
						
						propInfo.setNewVersion(verCode);
						propInfo.setNewVersionNo(verCodeNo);
						
						propInfo.setIpAddr(ftpIp);
						propInfo.setPortNo(ftpPort);
						propInfo.setUserName(ftpUserName);
						propInfo.setUserPass(ftpPassword);
						propInfo.setFilePath(filePath);
						
						if("2".equals(ctype)){
							propInfo.setDbAddr(dbIp);
							propInfo.setDbPort(dbPort);
							propInfo.setDbName(dbName);
							propInfo.setDbUserName(dbUserName);
							//dbPassword = Des.jiami(dbPassword);
							propInfo.setDbUserPass(dbPassword);
						}else{
							propInfo.setDbAddr("");
							propInfo.setDbPort("");
							propInfo.setDbName("");
							propInfo.setDbUserName("");
							propInfo.setDbUserPass("");
						}
						propInfo.setPassPostFix(passPostFix);
						
						result.setPropInfo(propInfo);
						result.setReturnCode(ConstantUtil.EXE_SUCCESS);
						
						TClientServiceLogEntity entity11 = new TClientServiceLogEntity();
						entity11.setClientIp(cip);
						entity11.setClientType(ctype);
						entity11.setCreateTime(new Date());
						entity11.setCurrentVersion(clientVersion);
						entity11.setResult("执行成功");
						//entity11.setInterXml(in);
						entity11.setLogType("0");
						entity11.setFuncName("客户端接口访问");
						systemService.save(entity11);
						
						
					}else{
						logger.info("[" + requestCode + "]==>" + "没有数据");
						result.setReturnCode(ConstantUtil.PARAM_ERROR);
						result.setReturnMsg("没有数据");
						TClientServiceLogEntity entity11 = new TClientServiceLogEntity();
						entity11.setClientIp(cip);
						entity11.setClientType(ctype);
						entity11.setCreateTime(new Date());
						entity11.setCurrentVersion(clientVersion);
						entity11.setResult("没有数据");
						//entity11.setInterXml(in);
						entity11.setLogType("0");
						entity11.setFuncName("客户端接口访问");
						systemService.save(entity11);
					}
				}
			}else{
				logger.info("[" + requestCode + "]==>" + "服务不可用");
				result.setReturnCode(ConstantUtil.PARAM_ERROR);
				result.setReturnMsg("服务不可用");
				TClientServiceLogEntity entity11 = new TClientServiceLogEntity();
				entity11.setClientIp(cip);
				entity11.setClientType(ctype);
				entity11.setCreateTime(new Date());
				entity11.setCurrentVersion(clientVersion);
				entity11.setResult("服务不可用");
				//entity11.setInterXml(in);
				entity11.setLogType("0");
				entity11.setFuncName("客户端接口访问");
				systemService.save(entity11);
			}
		} catch (Exception e) {
			// 设置结果标志，操作结果
			result.setReturnCode(ConstantUtil.PLAT_ERROR);// 其它错误
			result.setReturnMsg("平台异常");
			logger.error("[" + requestCode + "]==>" + "参数 serverSign [" + serverSign + "]"
					+ "参数 status [" + status + "]",e);
			logger.error(e.getMessage());
			TClientServiceLogEntity entity11 = new TClientServiceLogEntity();
			entity11.setClientIp(cip);
			entity11.setClientType(ctype);
			entity11.setCreateTime(new Date());
			entity11.setCurrentVersion(clientVersion);
			entity11.setResult("平台错误");
			//entity11.setInterXml(in);
			entity11.setLogType("0");
			entity11.setFuncName("客户端接口访问");
			systemService.save(entity11);
			e.printStackTrace();
		}
		return result;
	}
	
}
