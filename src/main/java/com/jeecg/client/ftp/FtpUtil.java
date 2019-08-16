package com.jeecg.client.ftp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jeecg.client.entity.client.TClientFileEntity;
import com.jeecg.client.util.Des;
 
/**
 * FTP服务器工具类
 * 
 * @author 码农先生
 * @version 1.0
 * @date 2013-11-18
 * @see 参数 地址1：http://blog.csdn.net/hbcui1984/article/details/2720204
 * @see 参数 地址2:http://blog.csdn.net/yibing548/article/details/38586073
 * @see 参考地址3：http://blog.csdn.net/for_china2012/article/details/16820607
 * @see 参考地址3：http://blog.csdn.net/kardelpeng/article/details/6588284
 * @see 参考地址3：http://commons.apache.org/proper/commons-net/apidocs/org/apache/commons/net/ftp/FTPClient.html
 * 
 */
@SuppressWarnings("all")
public class FtpUtil {
	private static Logger logger = Logger.getLogger(FtpUtil.class);
	private static FtpUtil ftpUtils;
	private FTPClient ftpClient;
 
	private String serverUrl; //服务器地址
	private String port; // 服务器端口
	private String username; // 用户登录名
	private String password; // 用户登录密码
	//private InputStream is; // 文件下载输入流
	private int fileLevel;
	
	private List<TClientFileEntity> dlist = new ArrayList<TClientFileEntity>();
	
	public List<TClientFileEntity> getList(){
		
		return dlist;
	}
	
	public void cleanList(){
		dlist.clear();
	}
 
	/**
	 * 私有构造方法
	 */
	private FtpUtil() {
		initConfig();
		if (null == ftpClient) {
			ftpClient = new FTPClient();
		}
	}
 
	/**
	 * 获取FTPUtils对象实例
	 * 
	 * @return FTPUtils对象实例
	 */
	public synchronized static FtpUtil getInstance() {
		if (null == ftpUtils) {
			ftpUtils = new FtpUtil();
		}
		return ftpUtils;
	}
 
	/**
	 * 初始化FTP服务器连接属性
	 */
	public void initConfig() {
	
		try {
			
			 
			username = ResourceUtil.getConfigByName("ftp.name"); //"ftp_admin";// 
			password = ResourceUtil.getConfigByName("ftp.password");  //"123456";//
	        serverUrl =  ResourceUtil.getConfigByName("ftp.ip");//直接ip地址  "218.60.2.242";//
	        port =ResourceUtil.getConfigByName("ftp.port");//"8021";// 
	        password = Des.jiemi(password);
       
	     /*   username = "ftp_admin";
			password = "123123";
	        serverUrl =  "192.168.253.5";
	        port ="2121";*/
			
			/*serverUrl = (String) properties.get("ftpClientCmbcIp"); // 设置服务器地址
			port = (String) properties.get("ftpPort"); // 设置端口
			username = (String) properties.get("ftpusername"); // 设置用户名
			password = (String) properties.get("ftppassword"); // 设置密码
*/		} catch(Exception e){
			logger.error("加载配置文件异常！",e);
		} finally {
			
		}
	}
 
	/**
	 * 连接（配置通用连接属性）至服务器
	 * 
	 * @param serverName
	 *            服务器名称
	 * @param remotePath
	 *            当前访问目录
	 * @return <b>true</b>：连接成功 <br/>
	 *         <b>false</b>：连接失败
	 */
	public boolean connectToTheServer(String remotePath) {
		// 定义返回值
		boolean result = false;
		try {
			// 连接至服务器，端口默认为21时，可直接通过URL连接
			 ftpClient.connect(serverUrl, Integer.parseInt(port));
			// 登录服务器
			ftpClient.login(username, password);
			// 判断返回码是否合法
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				// 不合法时断开连接
				ftpClient.disconnect();
				// 结束程序
				return result;
			}
			//设置文件传输模式
			//被动模式
			 ftpClient.enterLocalPassiveMode();
			//创建目录
			 ftpClient.changeWorkingDirectory("/");
			 
			 remotePath = remotePath.substring(1);
			 
			 String[] pah = remotePath.split("/");         
	            // 分层创建目录
	            for (String pa : pah) {
	            	boolean ff = ftpClient.changeWorkingDirectory(pa);
	            	if(ff){
	            		ftpClient.changeWorkingDirectory(pa);
	            	}else{
	            		 ftpClient.makeDirectory(pa);
	            		ftpClient.changeWorkingDirectory(pa);
	            	}
	            	
	               
	                // 切到到对应目录
	                
	            }  
			 
			// ftpClient.makeDirectory(remotePath);
			 
			 
			 
			// 设置文件操作目录
			//result = ftpClient.changeWorkingDirectory(remotePath);
			// 设置文件类型，二进制
			result = ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// 设置缓冲区大小
			ftpClient.setBufferSize(3072);
			// 设置字符编码
			ftpClient.setControlEncoding("UTF-8");
			
		} catch (IOException e) {
			logger.error("连接FTP服务器异常",e);
			throw new RuntimeException("连接FTP服务器异常" , e);
		}
		return result;
	}
 
	
 
	/**
	 * 下载FTP服务器文件至本地<br/>
	 * 操作完成后需调用logout方法与服务器断开连接
	 * 
	 * @param serverName
	 *            服务器名称
	 * @param remotePath
	 *            下载文件存储路径
	 * @param fileName
	 *            下载文件存储名称
	 * @return <b>InputStream</b>：文件输入流
	 */
	public InputStream retrieveFile11(String remotePath,String fileName) {
		InputStream is = null;
		try {
			boolean result = false;
			// 连接至服务器
			result = connectToTheServer(remotePath);
			// 判断服务器是否连接成功
			if (result) {
				// 获取文件输入流
				is = ftpClient.retrieveFileStream(fileName);
			}
		} catch (IOException e) {
			logger.error("从FTP下载文件到本地异常"+e.getMessage());
			throw new RuntimeException("从FTP下载文件到本地异常" , e);
		}
		return is;
	}
 
	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @Version1.0 Jul 27, 2008 5:32:36 PM by 崔红保（cuihongbao@d-heaven.com）创建
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public boolean retrieveFile11(String remotePath, String fileName,String localPath) {
		// 初始表示下载失败
		boolean success = false;
		//表示是否连接成功
		boolean result = false;
		File file = new File(localPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			// 连接至服务器
			result = connectToTheServer(remotePath);
			if(result){
				// 列出该目录下所有文件
				FTPFile[] fs = ftpClient.listFiles();
				// 遍历所有文件，找到指定的文件
				for (FTPFile ff : fs) {
					if (ff.getName().equals(fileName)) {
						// 根据绝对路径初始化文件
						File localFile = new File(localPath + "/" + ff.getName());
						//输出流
						OutputStream is = new FileOutputStream(localFile);
						//下载文件
						success=ftpClient.retrieveFile(ff.getName(), is);
						is.close();
					}
				}
			}
 
		} catch (IOException e) {
			logger.error("从FTP服务器下载文件异常",e);
		} finally {
			// 登出服务器并断开连接
			ftpUtils.logout();
		}
		return success;
	}
	
	
	
	/**
	 * 检测FTP服务器文件是否存在
	 * 
	 * @param serverName
	 *            服务器名称
	 * @param remotePath
	 *            检测文件存储路径
	 * @param fileName
	 *            检测文件存储名称
	 * @return <b>true</b>：文件存在 <br/>
	 *         <b>false</b>：文件不存在
	 */
	public boolean checkFile( String remotePath,String fileName) {
		boolean result = false;
		try {
			// 连接至服务器
			result = connectToTheServer(remotePath);
			// 判断服务器是否连接成功
			if (result) {
				// 默认文件不存在
				result = false;
				// 获取文件操作目录下所有文件名称
				String[] remoteNames = ftpClient.listNames();
				// 循环比对文件名称，判断是否含有当前要下载的文件名
				for (String remoteName : remoteNames) {
					if (fileName.equals(remoteName)) {
						result = true;
					}
				}
			}
		} catch (IOException e) {
			logger.error("检查FTP文件是否存在异常"+e.getMessage());
			throw new RuntimeException("检查FTP文件是否存在异常" , e);
		} finally {
			// 登出服务器并断开连接
			ftpUtils.logout();
		}
		return result;
	}
	
	
	
	
	
	public boolean downLoadFile11(String remotePath,String localPath) {
		// 初始表示下载失败
		boolean success = false;
		//表示是否连接成功
		boolean result = false;
		File file = new File(localPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			// 连接至服务器
			result = connectToTheServer(remotePath);
			if(result){
				// 列出该目录下所有文件
				FTPFile[] fs = ftpClient.listFiles();
				// 遍历所有文件，找到指定的文件
				for (FTPFile ff : fs) {
				
						// 根据绝对路径初始化文件
						File localFile = new File(localPath + "/" + ff.getName());
						//输出流
						OutputStream isa = new FileOutputStream(localFile);
						//下载文件
						success=ftpClient.retrieveFile(ff.getName(), isa);
						isa.close();
					
				}
			}
 
		} catch (IOException e) {
			logger.error("从FTP服务器下载文件异常",e);
		} finally {
			// 登出服务器并断开连接
			ftpUtils.logout();
		}
		return success;
	}
	
	
	/*  public byte[] downFileByte(String remotePath,String fileName){
	    
	    	boolean result = false;
	    	
	    	
	    	byte[] return_arraybyte=null;
	    	try{
	    		
	    		result = connectToTheServer(remotePath);
	    		if(result){
	    			
	    			FTPFile[] files = ftpClient.listFiles();
	    			  for(FTPFile file:files){
	    				  
	    				  if (file.isFile()) {
	    					  String name = file.getName().substring(0, file.getName().lastIndexOf("."));
		    	      		  if(name.equals(fileName)){
		    				  InputStream ins=ftpClient.retrieveFileStream(file.getName());
		    				  ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		    	    	        byte[] buf = new byte[204800];
		    	    	        int bufsize = 0;
		    	    	        while ((bufsize = ins.read(buf, 0, buf.length)) != -1) {
		    	    	            byteOut.write(buf, 0, bufsize);
		    	    	        }
		    	    	         return_arraybyte = byteOut.toByteArray();
		    	    	        byteOut.close();
		      	    			ins.close();
		      	    			break;
		    	    	   }
	    				  }
	    				  
	    				
	    			  }
	    		}
    	
	    	}catch (IOException e) {
				logger.error("从FTP服务器下载文件异常",e);
			} finally {
				// 登出服务器并断开连接
				ftpUtils.logout();
			}
	    	
      return return_arraybyte;
  }*/

	  public Map<String,Object> downFileByte(String remotePath,String fileName,String[] ext){
		    
	    	boolean result = false;
	    	
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	byte[] return_arraybyte=null;
	    	try{
	    		
	    		result = connectToTheServer(remotePath);
	    		if(result){
	    			
	    			FTPFile[] files = ftpClient.listFiles();
	    			  for(FTPFile file:files){
	    				  
	    				  if (file.isFile()) {
	    					  String name = file.getName();//.substring(0, file.getName().lastIndexOf("."));
	    					 // String exd = file.getName().substring(file.getName().lastIndexOf(".") + 1);
		    	      		  if(name.equals(fileName)){
		    				  InputStream ins=ftpClient.retrieveFileStream(file.getName());
		    				  ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		    	    	        byte[] buf = new byte[204800];
		    	    	        int bufsize = 0;
		    	    	        while ((bufsize = ins.read(buf, 0, buf.length)) != -1) {
		    	    	            byteOut.write(buf, 0, bufsize);
		    	    	        }
		    	    	       
		    	    	         return_arraybyte = byteOut.toByteArray();
		    	    	         map.put("array", return_arraybyte);
		    	    	         map.put("fileName", file.getName());
		    	    	        byteOut.close();
		      	    			ins.close();
		      	    			break;
		    	    	   }
	    				  }
	    				  
	    				
	    			  }
	    		}
  	
	    	}catch (IOException e) {
				logger.error("从FTP服务器下载文件异常",e);
			} finally {
				// 登出服务器并断开连接
				ftpUtils.logout();
			}
	    	
    return map;
}
	  
	  
	  
	  public boolean checkFileExsit(String remotePath, String fileName,String[] ext) {
			// 初始表示下载失败
			boolean flag = false;
			//表示是否连接成功
			boolean result = false;
			
			try {
				// 连接至服务器
				result = connectToTheServer(remotePath);
				if(result){
					// 列出该目录下所有文件
					FTPFile[] files = ftpClient.listFiles();
					String[] remoteNames = ftpClient.listNames();
					// 遍历所有文件，找到指定的文件
				//	if(remoteNames!=null&&remoteNames.length>0){
						for (FTPFile file:files) {
							if (file.isFile()) {
								  String name = file.getName();//.substring(0, file.getName().lastIndexOf("."));
								 // String exd = file.getName().substring(file.getName().lastIndexOf(".") + 1);
								if (name.equals(fileName)) {
									flag = true;
									break;
								}
							}
						}
					//}
					
				}
	 
			} catch (IOException e) {
				logger.error("检查FTP文件是否存在异常"+e.getMessage());
				throw new RuntimeException("检查FTP文件是否存在异常" , e);
			} finally {
				// 登出服务器并断开连接
				ftpUtils.logout();
			}
			return flag;
		}
 
	/**
	 * 删除FTP服务器文件
	 * 
	 * @param serverName
	 *            服务器名称
	 * @param remotePath
	 *            当前访问目录
	 * @param fileName
	 *            文件存储名称
	 * @return <b>true</b>：删除成功 <br/>
	 *         <b>false</b>：删除失败
	 */
	public boolean deleteFile(String remotePath,String fileName) {
		boolean result = false;
		// 连接至服务器
		result = connectToTheServer(remotePath);
		// 判断服务器是否连接成功
		if (result) {
			try {
				// 删除文件
				
				result = ftpClient.deleteFile(fileName);
			} catch (IOException e) {
				logger.error("删除FTP服务器上的 文件异常"+e.getMessage());
				throw new RuntimeException("删除FTP服务器上的 文件异常" , e);
			} finally {
				// 登出服务器并断开连接
				ftpUtils.logout();
			}
		}
		return result;
	}
	
	
	public boolean deleteAllFile(String remotePath) {
		boolean result = false;
		// 连接至服务器
		result = connectToTheServer(remotePath);
		// 判断服务器是否连接成功
		if (result) {
			try {
				FTPFile[] files = ftpClient.listFiles(remotePath);
				for (FTPFile f : files) {
					
					if (f.isFile()) {
						ftpClient.deleteFile(f.getName());
					}
				}
			
				String patha = remotePath.substring(0, remotePath.lastIndexOf("/"));
				ftpClient.changeWorkingDirectory(patha);
				ftpClient.removeDirectory(remotePath);
				ftpClient.changeWorkingDirectory("/");
				String dic = remotePath.substring(1);
				ftpClient.removeDirectory(dic);
			} catch (IOException e) {
				logger.error("删除FTP服务器上的 文件异常"+e.getMessage());
				throw new RuntimeException("删除FTP服务器上的 文件异常" , e);
			} finally {
				// 登出服务器并断开连接
				ftpUtils.logout();
			}
		}
		return result;
	}
	
 
	
 
	/**
	 * 登出服务器并断开连接
	 * 
	 * @param ftp
	 *            FTPClient对象实例
	 * @return <b>true</b>：操作成功 <br/>
	 *         <b>false</b>：操作失败
	 */
	public boolean logout() {
		boolean result = false;
		/*if (null != is) {
			try {
				// 关闭输入流
				is.close();
			} catch (IOException e) {
				logger.error("登录FTP服务器异常"+e.getMessage());
				throw new RuntimeException("登录FTP服务器异常" , e);
			}
		}*/
		if (null != ftpClient) {
			try {
				// 登出服务器
				result = ftpClient.logout();
			} catch (IOException e) {
				logger.error("登录FTP服务器异常"+e.getMessage());
				throw new RuntimeException("登录FTP服务器异常" , e);
			} finally {
				// 判断连接是否存在
				if (ftpClient.isConnected()) {
					try {
						// 断开连接
						ftpClient.disconnect();
					} catch (IOException e) {
						logger.error("关闭FTP服务器异常"+e.getMessage());
						throw new RuntimeException("关闭FTP服务器异常" , e);
					}
				}
			}
		}
		return result;
	}
 
 
 
 
 
	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public static boolean downFile11(String url, int port, String username,
			String password, String remotePath, String fileName,
			String localPath) {
		// 初始表示下载失败
		boolean success = false;
		// 创建FTPClient对象
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.connect(url, port);
			reply = ftp.getReplyCode();
			/*
			 * 判断是否连接成功
			 */
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			} else {
				// 登录ftp
				if (ftp.login(username, password)) {
					// 转到指定下载目录
					ftp.changeWorkingDirectory(remotePath);
					// 列出该目录下所有文件
					FTPFile[] fs = ftp.listFiles();
					// 遍历所有文件，找到指定的文件
					for (FTPFile ff : fs) {
						if (ff.getName().equals(fileName)) {
							// 根据绝对路径初始化文件
							File localFile = new File(localPath + "/"
									+ ff.getName());
							// 输出流
							OutputStream is = new FileOutputStream(localFile);
							// 下载文件
							ftp.retrieveFile(ff.getName(), is);
							is.close();
						}
					}
					// 退出ftp
					ftp.logout();
					// 下载成功
					success = true;
				}
			}
		} catch (IOException e) {
			logger.error("从FTP服务器下载文件异常"+e.getMessage());
			throw new RuntimeException("从FTP服务器下载文件异常" , e);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					logger.error("关闭FTP连接异常"+ioe.getMessage());
					throw new RuntimeException("关闭FTP连接异常" , ioe);
				}
			}
		}
		return success;
	}
 
	/**
	 * 读取本地TXT
	 * 
	 * @param filepath
	 *            txt文件目录即文件名
	 */
 
	public ArrayList<String> readtxt(String filepath) {
		ArrayList<String> readList = new ArrayList<String>();
		ArrayList retList = new ArrayList();
		try {
			String temp = null;
			File f = new File(filepath);
			String adn = "";
			// 指定读取编码用于读取中文
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					f), "UTF-8");
			BufferedReader reader = new BufferedReader(read);
			// bufReader = new BufferedReader(new FileReader(filepath));
			do {
				temp = reader.readLine();
				readList.add(temp);
			} while (temp != null);
			read.close();
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("读取本地txt异常"+e.getMessage());
			throw new RuntimeException("读取本地txt异常" , e);
		}
		return readList;
	}
 
 
	/**
	 * @param properties
	 * @param batchNo
	 * @param status
	 * @param financeChannel
	 * @param fileDownUtil
	 * @return
	 * @throws Exception
	 */
//	public  String downCmbcCallBackFile(Properties properties,FTPClient ftpClient, String batchNo, String status, FinanceChannel financeChannel, FileDownUtil fileDownUtil) throws Exception {
//		FTPClient ftp = new FTPClient();
//		FileOutputStream is = null;
//		File localEncoFile = null;
//		File dirLocalEncoPath = null;
//		String fileName = "";
//		String filePath = "";
//		String romoteEncoPeerPath = properties.getProperty("romoteEncoPeerPath") + batchNo.split("_")[0];
//		String romoteEncoCorssPath = properties.getProperty("romoteEncoCorssPath") + batchNo.split("_")[0];
//		String localEncoPeerPath = this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + batchNo.split("_")[0];
//		String localEncoCorssPath = this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + batchNo.split("_")[0];
//		try {
//			int reply;
//			ftp.connect(properties.getProperty("url"));
//			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
//			ftp.login(properties.getProperty("ftpusername"), properties.getProperty("ftppassword"));// 登录
//			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//			reply = ftp.getReplyCode();
//			if (!FTPReply.isPositiveCompletion(reply)) {
//				ftp.disconnect();
//				return null;
//			}
//			if ("Peer".equals(financeChannel.getSameOrCorssBank().name())) {
//				dirLocalEncoPath = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + batchNo.split("_")[0] + "/");// 加密目录
//				ftp.changeWorkingDirectory(properties.getProperty("romotePeerPath") + batchNo.split("_")[0] + "/");// 转移到FTP服务器目录
//				localEncoPeerPath =this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + DateUtil.formatDate(new Date(), "yyyyMMdd");
//				romoteEncoPeerPath = properties.getProperty("romoteEncoPeerPath") + DateUtil.formatDate(new Date(), "yyyyMMdd");
//				dirLocalEncoPath = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + DateUtil.formatDate(new Date(), "yyyyMMdd") + "/");// 加密目录
//				if (!dirLocalEncoPath.exists()) {
//					dirLocalEncoPath.mkdirs();
//					logger.info("创建本地目录成功:" + dirLocalEncoPath);
//				}
//			} else {
//				dirLocalEncoPath = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + batchNo.split("_")[0] + "/");// 加密目录
//				ftp.changeWorkingDirectory(properties.getProperty("romoteCorssPath") + batchNo.split("_")[0] + "/");// 转移到FTP服务器目录
//				localEncoCorssPath = this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + DateUtil.formatDate(new Date(), "yyyyMMdd");
//				romoteEncoCorssPath = properties.getProperty("romoteEncoCorssPath") + DateUtil.formatDate(new Date(), "yyyyMMdd");
//				dirLocalEncoPath = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + DateUtil.formatDate(new Date(), "yyyyMMdd") + "/");// 加密目录
//
//				if (!dirLocalEncoPath.exists()) {
//					dirLocalEncoPath.mkdirs();
//					logger.info("创建本地目录成功:" + dirLocalEncoPath);
//				}
//			}
//			FTPFile[] fs = ftp.listFiles();
//			for (FTPFile ff : fs) {
//				if ("Peer".equals(financeChannel.getSameOrCorssBank().name())) {
//					if (ff.getName().equals("res_" + batchNo + ".txt")) {
//						String newBatchNo = CmbcUtil.modifyBatchNo(batchNo, DateUtil.formatDate(new Date(), "yyyyMMdd"));
//						localEncoFile = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoPeerPath") + DateUtil.formatDate(new Date(), "yyyyMMdd") + "/" + "res_" + newBatchNo + ".txt");
//						is = new FileOutputStream(localEncoFile);
//						ftp.retrieveFile(ff.getName(), is);
//						fileName = "res_" + newBatchNo + ".txt";
//						// 先用生产key解密文件
//						CmbcUtil.decodeAESFile(properties.getProperty("deProPassword").getBytes(), localEncoPeerPath + "/" + "dePeer.txt", localEncoPeerPath + "/" + fileName);
//						// 再用测试key加密文件
//						CmbcUtil.encodeAESFile(properties.getProperty("dePassword").getBytes(), localEncoPeerPath + "/" + "dePeer.txt", localEncoPeerPath + "/" + fileName);
//						filePath = fileDownUtil.ftpUploadFile(ftpClient,properties,localEncoPeerPath, romoteEncoPeerPath, fileName);
//						break;
//					}
//				} else {
//					if (ff.getName().equals("res_outer_" + batchNo + ".txt")) {
//						String newBatchNo = CmbcUtil.modifyBatchNo(batchNo, DateUtil.formatDate(new Date(), "yyyyMMdd"));
//						localEncoFile = new File(this.getClass().getResource("/").getPath()+properties.getProperty("localEncoCorssPath") + DateUtil.formatDate(new Date(), "yyyyMMdd") + "/" + "res_outer_" + newBatchNo + ".txt");
//						is = new FileOutputStream(localEncoFile);
//						ftp.retrieveFile(ff.getName(), is);
//						fileName = "res_outer_" + newBatchNo + ".txt";
//						// 先用生产key解密文件
//						CmbcUtil.decodeAESFile(properties.getProperty("deProPassword").getBytes(), localEncoCorssPath + "/" + "deCorss.txt", localEncoCorssPath + "/" + fileName);
//						// 再用测试key加密文件
//						CmbcUtil.encodeAESFile(properties.getProperty("dePassword").getBytes(), localEncoCorssPath + "/" + "deCorss.txt", localEncoCorssPath + "/" + fileName);
//						filePath = fileDownUtil.ftpUploadFile(ftpClient,properties,localEncoCorssPath, romoteEncoCorssPath, fileName);
//						break;
//					}
//				}
//			}
//
//		} catch (IOException e) {
//			logger.error("关闭FTP服务器连接异常", e);
//			throw new RuntimeException("从FTP服务器下载文件异常", e);
//
//		} finally {
//			if (null != ftp) {
//				try {
//					ftp.logout();
//				} catch (IOException e) {
//					logger.error("关闭FTP服务器连接异常", e);
//				}
//			}
//			if (ftp.isConnected()) {
//				try {
//					ftp.disconnect();
//				} catch (IOException e) {
//					logger.error("关闭FTP服务器连接异常", e);
//				}
//			}
//			if (null != is) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					logger.error("关闭FTP服务器连接异常", e);
//				}
//			}
//		}
//		return filePath;
//	}
 
	
	
	
	/** 
	 * 把配置文件先写到本地的一个文件中取 
	 *  
	 * @param ftpPath 
	 * @param str 
	 * @return 
	 */  
	public boolean write(String fileName, String fileContext,  
			String writeTempFielPath) {  
		try {  
			logger.info("开始写配置文件");  
			File f = new File(writeTempFielPath + "/" + fileName);  
			if(!f.exists()){  
				if(!f.createNewFile()){  
					logger.info("文件不存在，创建失败!");  
				}  
			}  
			BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));  
			bw.write(fileContext.replaceAll("\n", "\r\n"));  
			bw.flush();  
			bw.close();  
			return true;  
		} catch (Exception e) {  
			logger.error("写文件没有成功");  
			e.printStackTrace();  
			return false;  
		}  
	}  
 
	
	/**
	 * 上传文件至FTP服务器
	 * 
	 * @param serverName
	 *            服务器名称
	 * @param storePath
	 *            上传文件存储路径
	 * @param fileName
	 *            上传文件存储名称
	 * @param is
	 *            上传文件输入流
	 * @return <b>true</b>：上传成功 <br/>
	 *         <b>false</b>：上传失败
	 */
	public boolean storeFile( String storePath,	List<MultipartFile> list) {
		boolean result = false;
		//InputStream iiis=null;
		InputStream is = null;
		try {
			// 连接至服务器
			result = connectToTheServer(storePath);
			
			
			// 判断服务器是否连接成功
			if (result) {
				// 上传文件
				
					MultipartFile mf = list.get(0);
					is = mf.getInputStream();//new ByteArrayInputStream(mf.getBytes());
				
					result = ftpClient.storeFile(mf.getOriginalFilename(), is);
					System.out.println(is.read());
					is.close();
				
				/*	if(mf.getInputStream()!=null){
						
						mf.getInputStream().close();
						//System.out.println(mf.getInputStream().read());
						mf.getInputStream().close();
						//System.out.println(mf.getInputStream().read());
					}*/
				
				
			}
			
			// 关闭输入流
			
			
			if(!result){
				for(MultipartFile mf:list){
					ftpClient.deleteFile(mf.getOriginalFilename());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			result = false;
		} finally {
			// 判断输入流是否存在
			if (null != is) {
				try {
					// 关闭输入流
					is.close();
				} catch (IOException e) {
					logger.error("上传文件至FTP异常"+e.getMessage());
					throw new RuntimeException("上传文件至FTP异常" , e);
				}
			}
			// 登出服务器并断开连接
			System.gc();
			ftpUtils.logout();
			
		}
		return result;
	}
	
	
	
	
	
	
	
	
	public boolean makeDic(String storePath) {
		boolean result = false;
		
		try {
			// 连接至服务器
			result = connectToTheServer(storePath);
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			
			ftpUtils.logout();
		}
		return result;
	}
	
	
	
	public boolean makeDic111(String storePath) {
		boolean result = false;
		
		try {
			// 连接至服务器
			result = connectToTheServer(storePath);
			 FTPFile[] files = ftpClient.listFiles(storePath);
			 
			 for (FTPFile f : files) {
				 // byte[] bytes = f.getName().getBytes("iso-8859-1");
				   // ftpFile.setName(new String(bytes, "GBK"));
				  //new String(f.getName().getBytes("GBK"),"iso-8859-1");
				 System.out.println(f.getName());
				 //System.out.println(new String(f.getName().getBytes("GBK"),"iso-8859-1"));
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			
			ftpUtils.logout();
		}
		return result;
	}
	
	
	
	public boolean reall(String remotePath){
		boolean result = false;
		
		try{
			result = connectToTheServer(remotePath);
			
			removeAll(remotePath);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			// 登出服务器并断开连接
			ftpUtils.logout();
		}
		
		return result;
	}
	
	
	public void removeAll(String remotePath) {
		
		// 连接至服务器
		
		// 判断服务器是否连接成功
		
			try {
				FTPFile[] files = ftpClient.listFiles(remotePath);
				for (FTPFile f : files) {
					
					if (f.isDirectory()) {
						this.removeAll(remotePath + "/" + f.getName());
						
						ftpClient.changeWorkingDirectory(remotePath.substring(0, remotePath.lastIndexOf("/")));

                      //  ftp.removeDirectory(pathName);
						
						ftpClient.removeDirectory(remotePath);
						}
					if (f.isFile()) {
							ftpClient.deleteFile(remotePath + "/" + f.getName());
						}
				}
				ftpClient.changeWorkingDirectory(remotePath.substring(0, remotePath.lastIndexOf("/")));

				ftpClient.removeDirectory(remotePath);
			
			} catch (IOException e) {
				logger.error("删除FTP服务器上的 文件异常"+e.getMessage());
				throw new RuntimeException("删除FTP服务器上的 文件异常" , e);
			}
		
		
	}
	
	
	
	
	
	
	/*  public String removeDirectoryALLFile(String pathName)

	    {

	        try

	        {

	            FTPFile[] files = ftp.listFiles(pathName);

	            if (null != files && files.length > 0)

	            {

	                for (FTPFile file : files)

	                {

	                    if (file.isDirectory())

	                    {

	                        removeDirectoryALLFile(pathName + "/" + file.getName());

	                        

	                        // 切换到父目录，不然删不掉文件夹

	                        ftp.changeWorkingDirectory(pathName.substring(0, pathName.lastIndexOf("/")));

	                        ftp.removeDirectory(pathName);

	                    }

	                    else

	                    {

	                        if (!ftp.deleteFile(pathName + "/" + file.getName()))

	                        {

	                            return "删除指定文件" + pathName + "/" + file.getName() + "失败!";

	                        }

	                    }

	                }

	            }

	            // 切换到父目录，不然删不掉文件夹

	            ftp.changeWorkingDirectory(pathName.substring(0, pathName.lastIndexOf("/")));

	            ftp.removeDirectory(pathName);

	        }

	        catch (IOException e)

	        {

	            logger.error("删除指定文件夹" + pathName + "失败：" + e);

	            e.printStackTrace();

	            return "删除指定文件夹" + pathName + "失败：" + e;

	        }

	        

	        return CommonProperties.SUCCESS;

	    }*/
	

	//aaaaa
	public boolean getAllFileAnDic(String remotePath,String clientId){
		boolean result = false;
		
		try{
			result = connectToTheServer(remotePath);
			
			//getAll(remotePath);
			//printDir(remotePath);
            readFile(remotePath,clientId,null);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			// 登出服务器并断开连接
			ftpUtils.logout();
		}
		
		return result;
	}
	
	
public void getAll(String remotePath) {
		
		// 连接至服务器
		
		// 判断服务器是否连接成功
		
			try {
				FTPFile[] files = ftpClient.listFiles(remotePath);
				for (FTPFile f : files) {
					
					if (f.isDirectory()) {
						System.out.println(remotePath);
						this.getAll(remotePath + "/" + f.getName());
						
						ftpClient.changeWorkingDirectory(remotePath.substring(0, remotePath.lastIndexOf("/")));
						
						
						//ftpClient.removeDirectory(remotePath);
						}
					if (f.isFile()) {
							//ftpClient.deleteFile(remotePath + "/" + f.getName());
						System.out.println(f.getName());
						}
				}
				//ftpClient.changeWorkingDirectory(remotePath.substring(0, remotePath.lastIndexOf("/")));

				//ftpClient.removeDirectory(remotePath);
			
			} catch (IOException e) {
				logger.error("删除FTP服务器上的 文件异常"+e.getMessage());
				throw new RuntimeException("删除FTP服务器上的 文件异常" , e);
			}
		
		
	}
	


	public String createPrintStr(String name,String path) {
	    // 输出的前缀
	    String printStr = "";
	    // 按层次进行缩进
	    /*for (int i = 0; i < level; i ++) {
	            printStr  = printStr + "  ";
	    }*/
	    printStr = printStr + "- " + name+"_"+path;
	    return printStr;
	}

	/*  public void printDir(String dirPath){
          // 将给定的目录进行分割
          String[] dirNameList = dirPath.split("/");
          // 设定文件level的base
          fileLevel = dirNameList.length;
          // 按格式输出
          for (int i = 0; i < dirNameList.length; i ++) {
                  System.out.println(createPrintStr(dirNameList[i], i));
          }
  }*/
	  
	  
	  public void readFile(String dirPath,String clientId,String parentid) throws IOException {
          // 建立当前目录中文件的File对象
         // File file = new File(dirPath);
          // 取得代表目录中所有文件的File对象数组
          
        // File[] list = file.listFiles();
         FTPFile[] list = ftpClient.listFiles(dirPath);
          // 遍历file数组
          for (int i = 0; i < list.length; i++) {
         	 
                  if (list[i].isDirectory()) {
                	
                		  TClientFileEntity tfile = new TClientFileEntity();
                		  String id = UUID.randomUUID().toString().replaceAll("-", "");
                		  tfile.setId(id);
                		  tfile.setClientId(clientId);
                		  tfile.setFileName(list[i].getName());
                		  //byte[] bytes = list[i].getName().getBytes("iso-8859-1");
                		   // ftpFile.setName(new String(bytes, "GBK"));

                		  
                		 // tfile.setFileName(new String(bytes, "GBK"));
                		  
                		  TClientFileEntity tfile2 = new TClientFileEntity();
                		  tfile2.setId(parentid);
                		  tfile.setTClientFileEntity(tfile2);
                		  dlist.add(tfile);
                		  readFile(dirPath + "/" + list[i].getName(),clientId,id);
                	 
                  } else {
                	 
                	  
                	  TClientFileEntity tfile = new TClientFileEntity();
            		  String id = UUID.randomUUID().toString().replaceAll("-", "");
            		  tfile.setId(id);
            		  tfile.setClientId(clientId);
            		  tfile.setFileName(list[i].getName());
            		 // byte[] bytes = list[i].getName().getBytes("iso-8859-1");
           		   // ftpFile.setName(new String(bytes, "GBK"));

           		  
           		 // tfile.setFileName(new String(bytes, "GBK"));
            		  
            		  TClientFileEntity tfile2 = new TClientFileEntity();
            		  tfile2.setId(parentid);
            		  tfile.setTClientFileEntity(tfile2);
            		  dlist.add(tfile);
            		 
                  }
         }
	  }
	
	/**
	 * 
	 * @Description： 测试连接ftP服务器
	 * @author: GuXiYang
	 * @date: 2015-6-18 下午4:28:15
	 * @param args
	 */
	public static void main(String[] args) {
		FtpUtil ftp = FtpUtil.getInstance();
		String path = "/ftp_client_dic/v.1.1.1/jeecg";
		
		
		
		
		 ftp.makeDic(path);
		//System.out.println(aa);
		//ftp.get(path);
		//List<String> list = ftp.getList();
		/*System.out.println(list.size());
		for(String str:list){
			System.out.println(str);
		}
		ftp.cleanList();;
		System.out.println(list.size());
		for(String str:list){
			System.out.println(str);
		}*/
		//ftp.deleteFile(path,"abc.txt");
		//String name = "1.2.3.text";
		//System.out.println(name.substring(name.lastIndexOf(".") + 1));
		
		//ftp.downLoadFile(path, "D:/1111");
		//ftp.downLoadFile(path, "D:/1111");
/*		String localPath="D:/aaa.csv";
		
		String remotePath = "/speed";
		String fileName = "aaa.csv";
		//文件上传
				try {  
					
					File file = new File(localPath);
					File file2 = new File("D:/settings.xml");
					List<File> fileList = new ArrayList();
					fileList.add(file);
					fileList.add(file2);
					
					
					//FileInputStream in=new FileInputStream(new File(localPath));  
					//boolean flag = ftp.storeFile(remotePath,fileList);
					logger.info("文件上传结果：+flag");  
				} catch (Exception e) {  
					e.printStackTrace();  
				} */ 
 
		//文件下载
		//		InputStream in=ftp.retrieveFile(remotePath, fileName);
		//		StringBuffer resultBuffer = new StringBuffer();  
		//		BufferedReader br = new BufferedReader(new InputStreamReader(in));  
		//		String data = null;  
		//		try {  
		//			while ((data = br.readLine()) != null) {  
		//				resultBuffer.append(data + "\n");  
		//			}  
		//		} catch (IOException e) {  
		//			logger.error("文件读取错误。");  
		//			e.printStackTrace();  
		//		}
		//     System.out.println(resultBuffer.toString());
 
 
		//在FTP服务器上生成一个文件，并将一个字符串写入到该文件中
//		try {  
//			InputStream input = new ByteArrayInputStream("一切只能靠自己！".getBytes("utf-8"));  
//			boolean flag = ftp.storeFile(remotePath,"test.txt",input);  
//			System.out.println(flag);  
//		} catch (UnsupportedEncodingException e) {  
//			e.printStackTrace();  
//		}  
 
 
	}
}