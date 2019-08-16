package com.jeecg.client.ftp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.exception.RarException.RarExceptionType;
import com.github.junrar.rarfile.FileHeader;  
  

public class UnRARTools {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		File file1 = new File("D:\\11\\Serv-U_22291.rar");
//		File file2 = new File("D:\\11");
		UnRARTools aa= new UnRARTools();

			try {
				aa.unrar("D:\\11\\Serv-U_22291.rar","D:\\11");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	
	public  void unrar(String srcRarPath,String dstDirectoryPath)throws Exception { 

        if (!srcRarPath.toLowerCase().endsWith(".rar")) {

             System.out.println("非rar文件！");

              return;

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

                              ex.printStackTrace();

                          }

                      }

                      fh = a.nextFileHeader();

                  }

                 a.close();

              }

          } catch (Exception e) {

              e.printStackTrace();

          }

     }
	
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
