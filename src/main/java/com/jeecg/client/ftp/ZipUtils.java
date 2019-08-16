package com.jeecg.client.ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class ZipUtils {

	 private static final String DEFAULT_CHARSET = "UTF-8";

	 private static final int buffer = 2048;  

	    /**
	     * 解压
	     *
	     * @param fromZipFile
	     *            zip文件路径
	     * @param unzipPath
	     *            解压路径
	     */
	    @SuppressWarnings("unchecked")
	    public static final void unzip(String fromZipFile, String unzipPath) throws Exception {

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
	            e1.printStackTrace();
	            throw new Exception(e1);
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
	                    e.printStackTrace();
	                    throw new Exception(e);
	                } finally {
	                    IOUtils.closeQuietly(bis);
	                    IOUtils.closeQuietly(bos);
	                    IOUtils.closeQuietly(is);
	                    IOUtils.closeQuietly(fos);
	                }
	            }
	        }
	    }
	    
	    
	    
	    public  static boolean unzip11(String fromZipFile, String unzipPath)  {

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
	    
	    public static  void mkdirs( String... path) {
	        for (String foo : path) {
	            final String realPath = FilenameUtils.normalizeNoEndSeparator(foo);
	            final File folder = new File(realPath);
	            if (!folder.exists() || folder.isFile()) {
	                folder.mkdirs();
	            }
	        }
	    }
	    
	    
	    public static boolean unZipzip(String path) { 
	        boolean flag = true;
	       int count = -1;  
	       String savepath = "";  

	       File file = null;  
	       InputStream is = null;  
	       FileOutputStream fos = null;  
	       BufferedOutputStream bos = null;  

	       savepath = path.substring(0, path.lastIndexOf("/")) + File.separator; //保存解压文件目录  
	       new File(savepath).mkdir(); //创建保存目录  
	       ZipFile zipFile = null;  
	       try  
	       {  
	           zipFile = new ZipFile(path,"gbk"); //解决中文乱码问题  
	           Enumeration<?> entries = zipFile.getEntries();  

	           while(entries.hasMoreElements())  
	           {  
	               byte buf[] = new byte[buffer];  

	               ZipEntry entry = (ZipEntry)entries.nextElement();  

	               String filename = entry.getName();  
	               boolean ismkdir = false;  
	               if(filename.lastIndexOf("/") != -1){ //检查此文件是否带有文件夹  
	                  ismkdir = true;  
	               }  
	               filename = savepath + filename;  

	               if(entry.isDirectory()){ //如果是文件夹先创建  
	                  file = new File(filename);  
	                  file.mkdirs();  
	                   continue;  
	               }  
	               file = new File(filename);  
	               if(!file.exists()){ //如果是目录先创建  
	                  if(ismkdir){  
	                  new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //目录先创建  
	                  }  
	               }  
	               file.createNewFile(); //创建文件  

	               is = zipFile.getInputStream(entry);  
	               fos = new FileOutputStream(file);  
	               bos = new BufferedOutputStream(fos, buffer);  

	               while((count = is.read(buf)) > -1)  
	               {  
	                   bos.write(buf, 0, count);  
	               }  
	               bos.flush();  
	               bos.close();  
	               fos.close();  

	               is.close();  
	           }  

	           zipFile.close();  

	       }catch(IOException ioe){  
	           flag = false;
	       }finally{  
	              try{  
	              if(bos != null){  
	                  bos.close();  
	              }  
	              if(fos != null) {  
	                  fos.close();  
	              }  
	              if(is != null){  
	                  is.close();  
	              }  
	              if(zipFile != null){  
	                  zipFile.close();  
	              }  
	              }catch(Exception e) {  
	                  flag = false;
	              }  
	          }  
	       return flag;
	      } 
	    
	    
	    
	    
	    
	    
	    public static void main(String[] args)   
	    {  
	        //String aa = "D:\ftp_sp\ftp_client_dic\v1.33\jeecg-jeecg-jeecg_3.7.1.zip";
	     //  String  aa ="D:/ftp_sp"+"/"+"ftp_client_dic"+"/"+"v1.33"+"/"+"jeecg-jeecg-jeecg_3.7.1.zip";
	     //  String  bb ="D:/ftp_sp"+"/"+"ftp_client_dic"+"/"+"v1.33";
	        String zipFilePath = "D:/1111/ftp1.6.zip";  
	       
	        try   
	        {  
	        	unZipzip(zipFilePath);  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        } 
	    /*	Properties props=System.getProperties(); //系统属性
	    	String os = props.getProperty("os.name");
	    	System.out.println(os);
	    	System.out.println(os.indexOf("Windows"));;
	    	if(os.indexOf("Windows")!=-1){
	    		System.out.println("操作系统的名称："+props.getProperty("os.name"));
	    	}else{
	    		System.out.println("操作系统的名称：linux");
	    	}*/
	    	
	    	
	    	
	    	
	    } 
}
