package com.jeecg.client.service.impl.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jeecg.client.controller.client.TClientController;
import com.jeecg.client.entity.client.TClientEntity;
import com.jeecg.client.entity.client.TClientFileEntity;
import com.jeecg.client.entity.client.TClientStatusEntity;
import com.jeecg.client.entity.log.TClientServiceLogEntity;
import com.jeecg.client.ftp.FtpUtil;
import com.jeecg.client.ftp.Un7z;
import com.jeecg.client.ftp.UnRar;
import com.jeecg.client.ftp.ZipUtils;
import com.jeecg.client.service.client.TClientFileServiceI;
import com.jeecg.client.service.client.TClientServiceI;
import com.jeecg.client.service.client.TClientStatusServiceI;

@Service("tClientService")
@Transactional
public class TClientServiceImpl extends CommonServiceImpl implements TClientServiceI {
	
	@Autowired
	private TClientFileServiceI tClientFileServiceI;
	
	@Autowired
	private TClientStatusServiceI tClientStatusServiceI;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private JdbcDao jdbcDao;
	
	private static final Logger logger = Logger.getLogger(TClientServiceImpl.class);
	
public String uploadClient(UploadFile uploadFile,TClientEntity tClient){
		
	
	String message = "";
	boolean unflag = false;
		//this.save(campusPageImage);
		try {
			
			uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
			MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
		
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			
			
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			//List<TClientFileEntity> clientFile = new ArrayList<TClientFileEntity>();
			for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
				
				MultipartFile mf = entity.getValue();
				fileList.add(mf);
			/*	TClientFileEntity obj = new TClientFileEntity();
				obj.setFileName(mf.getOriginalFilename());
				obj.setFilePath("/"+tClient.getRootPath()+"/"+tClient.getVerCode());
				obj.setClientId(tClient.getId());
				clientFile.add(obj);*/

			}
			
			if(fileList.size()>0){
				
				//文件名
				String filName = fileList.get(0).getOriginalFilename();
				//如果是文件夹 则去掉后缀名
				String llname = filName.substring(0, filName.lastIndexOf("."));
				//文件后缀
				String exd = filName.substring(filName.lastIndexOf(".") + 1);
				
				//根据文件夹目录 生成存放文件的目录
				//String fileType = tClient.getClientType()+"_dic";//.replaceAll("_dic", "_file");
				//String path22 = "/"+fileType+"/"+tClient.getVerCode();
				
				FtpUtil ftp = FtpUtil.getInstance();
				boolean flag = false;
				/*if("1".equals(tClient.getFileType())){
					 flag = ftp.storeFile("/"+fileType+"/"+tClient.getVerCode(),fileList);
				}else{
					 flag = ftp.storeFile("/"+tClient.getClientType()+"/"+tClient.getVerCode(),fileList);
				}*/
				 flag = ftp.storeFile("/"+tClient.getClientType()+"/"+tClient.getVerCode(),fileList);
				
				//String ufile = "/"+tClient.getClientType()+"/"+tClient.getVerCode();
				//boolean flag = ftp.storeFile("/"+tClient.getClientType()+"/"+tClient.getVerCode(),fileList);
				
				if(flag){
					/*tClientFileServiceI.batchSave(clientFile);
					tClient.setStatus("1");
					this.saveOrUpdate(tClient);*/
					
					
					TClientServiceLogEntity entity = new TClientServiceLogEntity();
					entity.setFuncName("上传客户端");
					entity.setCreateTime(new Date());
					
					entity.setLogType("1");
					
					entity.setResult("上传成功");
					systemService.save(entity);
					
					
					if("1".equals(tClient.getFileType())){
						
						
						
						/*TClientEntity tClientFile = new TClientEntity();
						
						tClientFile.setClientType(fileType);
						
						tClientFile.setClientName(path22);
						tClientFile.setVerName(tClient.getVerName());
						tClientFile.setVerCode(tClient.getVerCode());
						tClientFile.setVerCodeNo(tClient.getVerCodeNo());
						tClientFile.setOperTime(new Date());
						tClientFile.setStatus("0");
						tClientFile.setRemark(tClient.getRemark());
						tClientFile.setFileType("0");
						
						tClientFile.setFileName(filName);*/
					
						 boolean zipflag = false;
						 String 	ftppath = ResourceUtil.getConfigByName("ftp.path");
						 
						 if(!isWindowsSystem()){
							 ftppath = ResourceUtil.getConfigByName("ftp.linux.path");
						 }
						 
						if("ZIP".equals(exd.toUpperCase())){
							
							
							 // String zippath = ftppath+"/"+fileType+"/"+tClient.getVerCode()+"/"+filName;
							 String zippath = ftppath+"/"+tClient.getClientType()+"/"+tClient.getVerCode()+"/"+filName;
							  //String zippath2 = ftppath+"/"+tClient.getClientType()+"/"+tClient.getVerCode();
							   zipflag = ZipUtils.unZipzip(zippath);//unzip(zippath,zippath2);
							
						}
						
						if("RAR".equals(exd.toUpperCase())){
							
							 // String zippath = ftppath+"/"+fileType+"/"+tClient.getVerCode()+"/"+filName;
							 String zippath = ftppath+"/"+tClient.getClientType()+"/"+tClient.getVerCode()+"/"+filName;
							  String zippath2 = ftppath+"/"+tClient.getClientType()+"/"+tClient.getVerCode();
							  
							  UnRar u=new UnRar();

							  zipflag = u.unrar(zippath,zippath2);
							  
							
							
						}
						
						if("7Z".equals(exd.toUpperCase())){
							
							 // String zippath = ftppath+"/"+fileType+"/"+tClient.getVerCode()+"/"+filName;
							 String zippath = ftppath+"/"+tClient.getClientType()+"/"+tClient.getVerCode()+"/"+filName;
							  String zippath2 = ftppath+"/"+tClient.getClientType()+"/"+tClient.getVerCode();
							  
							  Un7z u=new Un7z();

						      
							  zipflag = u.apache7ZDecomp(zippath,zippath2);
							  
							
						}
						
						 if(zipflag){
							  // tClient.setStatus("1");
								tClient.setFileName(llname);
								this.saveOrUpdate(tClient);
								
							   // this.save(tClientFile);
								ftp.deleteFile("/"+tClient.getClientType()+"/"+tClient.getVerCode(),filName);
							   // tClientFileServiceI.syncFile(tClient.getId());
							    unflag = true;
							    
							    
							    TClientServiceLogEntity entity11 = new TClientServiceLogEntity();
								entity11.setFuncName("解压客户端");
								entity11.setCreateTime(new Date());
								
								entity11.setLogType("1");
								
								entity11.setResult("解压成功");
								systemService.save(entity11);
							    
							  // tClientFileServiceI.syncFile(tClient.getId());
						   }else{
							   
							   TClientServiceLogEntity entity22 = new TClientServiceLogEntity();
								entity22.setFuncName("解压客户端");
								entity22.setCreateTime(new Date());
								
								entity22.setLogType("1");
								
								entity22.setResult("解压失败");
								systemService.save(entity22);
							   message = "解压失败";
							   ftp.deleteFile("/"+tClient.getClientType()+"/"+tClient.getVerCode(),filName);
						   }
						
					}else{
						//tClient.setStatus("0");
						tClient.setFileName(filName);
						this.saveOrUpdate(tClient);
					}
					
					if("1".equals(tClient.getFileType())){
						if(unflag){
							tClientFileServiceI.syncFile(tClient.getId());
							// activeClient(tClient);
							 message = "上传成功";
						}
					}else{
						tClientFileServiceI.syncFile(tClient.getId());
						// activeClient(tClient);
						 message = "上传成功";
					}
					 
					 
				}else{
					//ftp.deleteAllFile("/"+tClient.getClientName()+"/"+tClient.getVerCode());
					//tClient.setStatus("0");
					//this.saveOrUpdate(tClient);
					
					TClientServiceLogEntity entity = new TClientServiceLogEntity();
					entity.setFuncName("上传客户端");
					entity.setCreateTime(new Date());
					
					entity.setLogType("1");
					
					entity.setResult("上传失败");
					systemService.save(entity);
					
					message = "上传失败";
					
				}
			}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("解压异常",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();    
		} 
		
		return message;
	}


private  boolean isWindowsSystem() {
    String p = System.getProperty("os.name");
    return p.toLowerCase().indexOf("windows") >= 0 ? true : false;
}

	public void saveClient(TClientEntity tClient){
		this.save(tClient);
		String sql = "select t.id from t_client_status t where t.CLIENT_TYPE='"+tClient.getClientType()+"'";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		if(list!=null&&list.size()>0){
			String usql = "update t_client_status set status='0' where client_type='"+tClient.getClientType()+"'";
			jdbcDao.update(usql);
		}else{
			TClientStatusEntity status = new TClientStatusEntity();
			status.setClientName(tClient.getClientName());
			status.setClientType(tClient.getClientType());
			status.setStatus("0");
			tClientStatusServiceI.save(status);
		}
		FtpUtil ftp = FtpUtil.getInstance();
		String path = "/"+tClient.getClientType()+"/"+tClient.getVerCode();
		ftp.makeDic(path);
		/*if("1".equals(tClient.getFileType())){
			String fileP = tClient.getClientType()+"_dic";//.replaceAll("_dic", "_file");
			
			String path22 = "/"+fileP+"/"+tClient.getVerCode();
			ftp.makeDic(path22);
		}*/
		
		
	}
	
	private void createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
           // logger.info("创建目录" + destDirName + "失败，目标目录已经存在");  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
        //	logger.info("创建目录" + destDirName + "成功！");  
        } else {  
        	//logger.info("创建目录" + destDirName + "失败！");  
        }  
    } 
	
	public void activeClient(TClientEntity tClient){
		tClient.setStatus("1");
		this.saveOrUpdate(tClient);
		String usql = "update t_client_status set status='1' where client_type='"+tClient.getClientType()+"'";
		jdbcDao.update(usql);
		
	}
	
	public void delClient(TClientEntity tClient){
		FtpUtil ftp = FtpUtil.getInstance();
		ftp.reall("/"+tClient.getClientType()+"/"+tClient.getVerCode());
		String usql = "update t_client_status set status='1' where CLIENT_TYPE='"+tClient.getClientType()+"'";
		jdbcDao.update(usql);
		String dsql = "delete from t_client_file where client_id='"+tClient.getId()+"'";
		jdbcDao.update(dsql);
		this.delete(tClient);
	}
	
	
	
	
	
	
	
	
	 @SuppressWarnings("unchecked")
	    public   boolean unzip(String fromZipFile, String unzipPath)  {

		 boolean flag = true;
	        FileOutputStream fos = null;
	        InputStream is = null;
	        String path1 = StringUtils.EMPTY;
	        String tempPath = StringUtils.EMPTY;

	        if (!new File(unzipPath).exists()) {
	            new File(unzipPath).mkdir();
	        }
	        ZipFile zipFile = null;
	        try {
	            zipFile = new ZipFile(fromZipFile, "GBK");
	        } catch (IOException e1) {
	        	logger.error("zip解压异常",e1);
	            flag = false;
	            return flag;
	        }
	        File temp = new File(unzipPath);
	        String strPath = temp.getAbsolutePath();
	        Enumeration<ZipEntry> enu = zipFile.getEntries();
	        ZipEntry zipEntry = null;
	        while (enu.hasMoreElements()) {
	            zipEntry = (ZipEntry) enu.nextElement();
	            path1 = zipEntry.getName();
	            if (zipEntry.isDirectory()) {
	                tempPath = FilenameUtils.normalizeNoEndSeparator(strPath + File.separator + path1);
	                File dir = new File(tempPath);
	                dir.mkdirs();
	                continue;
	            } else {

	                BufferedInputStream bis = null;
	                BufferedOutputStream bos = null;
	                try {
	                    is = zipFile.getInputStream(zipEntry);
	                    bis = new BufferedInputStream(is);
	                    path1 = zipEntry.getName();
	                    tempPath = FilenameUtils.normalizeNoEndSeparator(strPath + File.separator + path1);
	                    mkdirs(new File(tempPath).getParent());
	                    fos = new FileOutputStream(tempPath);
	                    bos = new BufferedOutputStream(fos);

	                    IOUtils.copy(bis, bos);
	                } catch (IOException e) {
	                	logger.error("zip解压异常",e);
	                    flag = false;
	                    return flag;
	                   // throw new Exception(e);
	                } finally {
	                	IOUtils.closeQuietly(bos);
	                    IOUtils.closeQuietly(bis);
	                    IOUtils.closeQuietly(is);
	                    IOUtils.closeQuietly(fos);
	                }
	            }
	        }
	        IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(fos);
	        return flag;
	    }
	    
	    public   void mkdirs( String... path) {
	        for (String foo : path) {
	            final String realPath = FilenameUtils.normalizeNoEndSeparator(foo);
	            final File folder = new File(realPath);
	            if (!folder.exists() || folder.isFile()) {
	                folder.mkdirs();
	            }
	        }
	    }
	
}