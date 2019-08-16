package com.jeecg.client.inter.ws.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * <pre>
 * Title:文件操作工具类
 * Description: 文件操作
 * </pre>
 * 
 * @author 李健
 * @version 1.00.00
 * @since 2011-8-31
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public final class FileUtil {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FileUtil.class);
	
	public static final String ROOT_PATH = new File(new FileUtil().getClass().getResource("/").getPath()).getAbsolutePath();

	  /**
	   * 新建目录
	   * @param folderPath String 如 c:/fqf
	   * @return boolean
	   */
	  public static void newFolder(String folderPath) {
	    try {
	      String filePath = folderPath;
	      filePath = filePath.toString();
	      java.io.File myFilePath = new java.io.File(filePath);
	      if (!myFilePath.exists()) {
	        myFilePath.mkdir();
	      }
	    }
	    catch (Exception e) {
	      logger.error("新建目录操作出错"+e);
	      e.printStackTrace();
	    }
	  }

	  /**
	   * 新建文件 字符流输出流
	   * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
	   * @param fileContent String 文件内容
	   * @return boolean
	   */
	  public static void newFile(String filePathAndName, String fileContent) {

	    try {
	      String filePath = filePathAndName;
	      filePath = filePath.toString();
	      File myFilePath = new File(filePath);
	      if (!myFilePath.exists()) {
	        myFilePath.createNewFile();
	      }
	      FileWriter resultFile = new FileWriter(myFilePath);
	      PrintWriter myFile = new PrintWriter(resultFile);
	      String strContent = fileContent;
	      myFile.println(strContent);
	      resultFile.close();

	    }
	    catch (Exception e) {
	      logger.error("新建文件操作出错"+e);
	      e.printStackTrace();

	    }

	  }

	  /**
	   * 删除文件
	   * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
	   * @param fileContent String
	   * @return boolean
	   */
	  public static void delFile(String filePathAndName) {
	    try {
	      String filePath = filePathAndName;
	      filePath = filePath.toString();
	      java.io.File myDelFile = new java.io.File(filePath);
	      myDelFile.delete();

	    }
	    catch (Exception e) {
	      logger.error("删除文件操作出错"+e);
	      e.printStackTrace();

	    }

	  }

	  /**
	   * 删除文件夹
	   * @param filePathAndName String 文件夹路径及名称 如c:/fqf
	   * @param fileContent String
	   * @return boolean
	   */
	  public static void delFolder(String folderPath) {
	    try {
	      delAllFile(folderPath); //删除完里面所有内容
	      String filePath = folderPath;
	      filePath = filePath.toString();
	      java.io.File myFilePath = new java.io.File(filePath);
	      myFilePath.delete(); //删除空文件夹

	    }
	    catch (Exception e) {
	      logger.error("删除文件夹操作出错"+e);
	      e.printStackTrace();

	    }

	  }

	  /**
	   * 删除文件夹里面的所有文件
	   * @param path String 文件夹路径 如 c:/fqf
	   */
	  public static void delAllFile(String path) {
	    File file = new File(path);
	    if (!file.exists()) {
	      return;
	    }
	    if (!file.isDirectory()) {
	      return;
	    }
	    String[] tempList = file.list();
	    File temp = null;
	    for (int i = 0; i < tempList.length; i++) {
	      if (path.endsWith(File.separator)) {
	        temp = new File(path + tempList[i]);
	      }
	      else {
	        temp = new File(path + File.separator + tempList[i]);
	      }
	      if (temp.isFile()) {
	        temp.delete();
	      }
	      if (temp.isDirectory()) {
	        delAllFile(path+"/"+ tempList[i]);//先删除文件夹里面的文件
	        delFolder(path+"/"+ tempList[i]);//再删除空文件夹
	      }
	    }
	  }

	  /**
	   * 复制单个文件
	   * @param oldPath String 原文件路径 如：c:/fqf.txt
	   * @param newPath String 复制后路径 如：f:/fqf.txt
	   * @return boolean
	   */
	  public static void copyFile(String oldPath, String newPath) {
		InputStream inStream = null;
		FileOutputStream fs = null;
	    try {
	      int bytesum = 0;
	      int byteread = 0;
	      File oldfile = new File(oldPath);
	      File newfile = new File(newPath);
	      //判断目标文件是否存在，不存在的新建
	      if(newfile.exists()){
	      }else{
	    	  newFile(newPath, "");
	      }
	      
	      if (oldfile.exists()) { //文件存在时
	        inStream = new FileInputStream(oldPath); //读入原文件
	        fs = new FileOutputStream(newPath);
	        byte[] buffer = new byte[1444];
	        while ( (byteread = inStream.read(buffer)) != -1) {
	          bytesum += byteread; //字节数 文件大小
	          fs.write(buffer, 0, byteread);
	        }
	        fs.close();
	        inStream.close();
	      }else{
	      }
	    }catch (Exception e) {
	      logger.error("复制整个文件夹内容操作出错"+e);
	      e.printStackTrace();
	    }finally{
	    	try{
	    		if(null != inStream){
	    			inStream.close();
	    		}
	    		if(null != fs){
	    			fs.close();
	    		}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }

	  }

	  /**
	   * 复制整个文件夹内容
	   * @param oldPath String 原文件路径 如：c:/fqf
	   * @param newPath String 复制后路径 如：f:/fqf/ff
	   * @return boolean
	   */
	  public static void copyFolder(String oldPath, String newPath) {
		String[] file=null;
		FileInputStream input = null;
		FileOutputStream output = null;
	    try {
	      (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
	      File a=new File(oldPath);
	      file=a.list();
	      File temp=null;
	      for (int i = 0; i < file.length; i++) {
	        if(oldPath.endsWith(File.separator)){
	          temp=new File(oldPath+file[i]);
	        }
	        else{
	          temp=new File(oldPath+File.separator+file[i]);
	        }

	        if(temp.isFile()){
	          input = new FileInputStream(temp);
	          output = new FileOutputStream(newPath + "/" +
	              (temp.getName()).toString());
	          byte[] b = new byte[1024 * 5];
	          int len;
	          while ( (len = input.read(b)) != -1) {
	            output.write(b, 0, len);
	          }
	          output.flush();
	          output.close();
	          input.close();
	        }
	        if(temp.isDirectory()){//如果是子文件夹
	          copyFolder(oldPath+"/"+file[i],newPath+"/"+file[i]);
	        }
	      }
	    }catch (Exception e) {
	      logger.error("复制整个文件夹内容操作出错"+e);
	      e.printStackTrace();
	    }finally{
	    	file = null;
	    	try{
		    	if(null != input){
		    		input.close();
		    	}
		    	if(null != output){
		    		output.close();
		    	}
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }

	  }

	  /**
	   * 移动文件到指定目录（剪切）
	   * @param oldPath String 如：c:/fqf.txt
	   * @param newPath String 如：d:/fqf.txt
	   */
	  public static void moveFile(String oldPath, String newPath) {
	    copyFile(oldPath, newPath);
	    delFile(oldPath);

	  }
	  
	  /**
	   * 移动文件夹到指定目录（剪切）
	   * @param oldPath String 如：c:/fqf.txt
	   * @param newPath String 如：d:/fqf.txt
	   */
	  public static void moveFolder(String oldPath, String newPath) {
	    copyFolder(oldPath, newPath);
	    delFolder(oldPath);

	  }

	  /**
	   * 查询文件名称
	   * 如果返回为空则没有需要再处理的文件
	   * @return
	   */
	  public static String getFileName(String Path){
		  String file_name = "";
		  try{
			  File a=new File(Path);
		      String[] file=a.list();
		      
		      if(file.length>=1){
		    	  file_name  = file[0];
		      }
		  }catch(Exception e) {
		      logger.error("复制整个文件夹内容操作出错"+e);
		      e.printStackTrace();
		  }
		  
		  return file_name;
	  }
	  /**
	   * 新建文件 字节流 指定字符集
	   * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
	   * @param fileContent String 文件内容
	   * @param charset 指定的字符集
	   * @return boolean
	   */
	  public static void newFile(String filePathAndName, String fileContent,String charset) {
		String filePath = filePathAndName;
		filePath = filePath.toString();
		File myFilePath = new File(filePath);
		
		try {
		 
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
		}
		catch (Exception e) {
			logger.error("新建文件操作出错"+e);
			e.printStackTrace();
			return ;
		}
		FileOutputStream fileOutputStream=null; 
		OutputStreamWriter outputStreamWriter=null;   
		BufferedWriter bufferedWriter = null;  
		try {
			fileOutputStream = new FileOutputStream(myFilePath); 
			outputStreamWriter = new OutputStreamWriter(fileOutputStream,charset);   
			bufferedWriter = new BufferedWriter(outputStreamWriter);  
			bufferedWriter.write(fileContent);
		
		} catch (Exception e) {
			logger.error("文件写入操作出错"+e);
			e.printStackTrace();
		}finally{
			try {
				if(bufferedWriter!=null) 
					bufferedWriter.close();
				if(outputStreamWriter!=null) 
					outputStreamWriter.close();
				if(fileOutputStream!=null) 
					fileOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("关闭流资源出错"+e);
				e.printStackTrace();
			}
		}
	  }
	  /**
	   * 递归获取指定目录下的所有文件，包括子文件夹
	   * 
	   * @author RyanLi
	   * @date 2013-11-7
	   *  
	   */
	  public static void getFiles(List<File> list,String path,String pattern){
		  File rootDir = new File(path);
		    if(!rootDir.isDirectory()){
		    	logger.info("san files："+rootDir.getAbsolutePath());
		    	if(pattern==null||pattern.length()==0||rootDir.getName().endsWith(pattern))//.properties
		    		list.add(rootDir);
		    }else{
			     String[] fileList =  rootDir.list();
			     for (int i = 0; i < fileList.length; i++) {
				     path = rootDir.getAbsolutePath()+File.separator+fileList[i];
				     getFiles(list,path,pattern);      
			     } 
		    }    
		  
	  }
}
