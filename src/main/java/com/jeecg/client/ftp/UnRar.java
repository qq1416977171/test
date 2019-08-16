package com.jeecg.client.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import com.jeecg.client.service.impl.client.TClientServiceImpl;

public class UnRar {
	
	
	public static void main(String[] args) throws Exception{

	      UnRar u=new UnRar();
//"D:\\ftp_sp\\visit_client_file\\v1.3"
	      u.unrar("D:\\11\\Serv-U_22291.rar","D:\\11");
	               
	   }

	  
	
	private static final Logger logger = Logger.getLogger(UnRar.class);
	
	
	
	
	public static void urar(String rarFile,String savePath) {

		//判断是否rar文件

		if(!rarFile.endsWith(".rar")) {

		//System.err.println("打开的文件不是rar文件！");
		logger.error("打开的文件不是rar文件！");
		return;

		}

		try {

		FileOutputStream fos = null;    

		//创建一个rar档案文件

		Archive rarArchive = new  Archive(new File(rarFile));

		//判断是否有加密

		if(rarArchive != null) {

		if(rarArchive.isEncrypted()) {

		rarArchive.close();//关闭资源

		return;

		}

		FileHeader fileHeader = rarArchive.nextFileHeader();

		while(fileHeader != null) {

		if(!fileHeader.isDirectory()){    

		//从压缩文件中解压出来的文件名，有可能带目录的文件名

		               String name = fileHeader.getFileNameString().trim();    

		                       String outPutFileName = savePath +"\\"+name; 

		                       

		                       //分离文件名，为了创建目录

		                   File dir = new File(outPutFileName.substring(0, outPutFileName.lastIndexOf("\\")));  

		                   //创建文件夹    

		                   if(!dir.exists()||!dir.isDirectory()){    

		                      dir.mkdirs();  

		                   }    

		                   

		                   fos = new FileOutputStream(new File(outPutFileName));    

		                   //保存解压的文件

		                   rarArchive.extractFile(fileHeader, fos);   

		                   //关闭资源

		                   fos.close();    

		                   fos = null;    

		               }    

		fileHeader = rarArchive.nextFileHeader();  

		}

		}

		rarArchive.close();//关闭资源

		} catch (RarException e) {
			logger.error("rar解压异常", e);
		

		} catch (IOException e) {

			logger.error("rar解压异常", e);

		}   

		}
	
	
	public void unrar2(File sourceRar, File destDir) throws Exception {  
        Archive archive = null;  
        FileOutputStream fos = null;  
        System.out.println("Starting...");  
        try {  
            archive = new Archive(sourceRar);  
            FileHeader fh = archive.nextFileHeader();  
            int count = 0;  
            File destFileName = null;  
            while (fh != null) {  
                System.out.println((++count) + ") " + fh.getFileNameString());  
                String compressFileName = fh.getFileNameString().trim();  
                destFileName = new File(destDir.getAbsolutePath() + "/" + compressFileName);  
                if (fh.isDirectory()) {  
                    if (!destFileName.exists()) {  
                        destFileName.mkdirs();  
                    }  
                    fh = archive.nextFileHeader();  
                    continue;  
                }   
                if (!destFileName.getParentFile().exists()) {  
                    destFileName.getParentFile().mkdirs();  
                }  
                fos = new FileOutputStream(destFileName);  
                archive.extractFile(fh, fos);  
                fos.close();  
                fos = null;  
                fh = archive.nextFileHeader();  
            }  
  
            archive.close();  
            archive = null;  
            System.out.println("Finished !");  
        } catch (Exception e) {  
            throw e;  
        } finally {  
            if (fos != null) {  
                try {  
                    fos.close();  
                    fos = null;  
                } catch (Exception e) {  
                    //ignore  
                }  
            }  
            if (archive != null) {  
                try {  
                    archive.close();  
                    archive = null;  
                } catch (Exception e) {  
                    //ignore  
                }  
            }  
        }  
    }  
  
  
	
	
	
	
	
	

	   public  boolean unrar(String srcRarPath,String dstDirectoryPath) throws Exception { 

		  boolean flag = true;
	        if (!srcRarPath.toLowerCase().endsWith(".rar")) {

	             //System.out.println("非rar文件！");
	             logger.error("非rar文件！");
	             flag = false;
	             

	          }

	          File dstDiretory = new File(dstDirectoryPath);

	          if (!dstDiretory.exists()) {// 目标目录不存在时，创建该文件夹

	              dstDiretory.mkdirs();

	          }

	          File fol=null,out=null;

	          Archive a = null;

	          try {

	              a = new Archive(new File(srcRarPath));

	              if (a != null){

	                  a.getMainHeader().print(); // 打印文件信息.

	                  FileHeader fh = a.nextFileHeader();

	                  while (fh != null){

	                      if (fh.isDirectory()) { // 文件夹

	                      // 如果是中文路径，调用getFileNameW()方法，否则调用getFileNameString()方法，还可以使用if(fh.isUnicode())

	                      if(existZH(fh.getFileNameW())){

	                         fol = new File(dstDirectoryPath + File.separator

	                                      + fh.getFileNameW());

	                      }else{

	                          fol = new File(dstDirectoryPath + File.separator

	                                      + fh.getFileNameString());

	                      }

	                       

	                          fol.mkdirs();

	                      } else { // 文件

	                      if(existZH(fh.getFileNameW())){

	                           out = new File(dstDirectoryPath + File.separator

	                                      + fh.getFileNameW().trim());

	                      }else{

	                          out = new File(dstDirectoryPath + File.separator

	                                      + fh.getFileNameString().trim());

	                      }

	                       

	                          //System.out.println(out.getAbsolutePath());

	                          try {// 之所以这么写try，是因为万一这里面有了异常，不影响继续解压.

	                              if (!out.exists()) {

	                                  if (!out.getParentFile().exists()){// 相对路径可能多级，可能需要创建父目录.

	                                      out.getParentFile().mkdirs();

	                                  }

	                                  out.createNewFile();

	                              }

	                              FileOutputStream os = new FileOutputStream(out);

	                              a.extractFile(fh, os);

	                              os.close();

	                          } catch (Exception ex) {
	                        	  logger.error("rar解压异常！",ex);
	                            //  ex.printStackTrace();

	                          }

	                      }

	                      fh = a.nextFileHeader();

	                  }

	                 a.close();

	              }

	              flag = true;
	          } catch (Exception e) {
	        	  logger.error("rar解压异常！",e);
	             // e.printStackTrace();
	              flag = false;
	          }finally {
					// 登出服务器并断开连接
	        	  if(a!=null){
	        		  a.close();
	        	  }
	        	  
				}
	          return flag;
	     }

	   

	   /*

	     * 判断是否是中文

	     */

	   public  boolean existZH(String str){ 

	        String regEx = "[\\u4e00-\\u9fa5]"; 

	        Pattern p = Pattern.compile(regEx); 

	        Matcher m = p.matcher(str); 

	        while (m.find()) { 

	            return true; 

	        } 

	        return false; 

	   }
}
