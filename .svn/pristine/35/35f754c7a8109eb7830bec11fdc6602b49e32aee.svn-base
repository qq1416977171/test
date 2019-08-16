package com.jeecg.client.ftp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
public class Un7z {

	private static final Logger logger = Logger.getLogger(Un7z.class);
	
	  public  boolean apache7ZDecomp(String orgPath, String tarpath) {

	        try {
	            SevenZFile sevenZFile = new SevenZFile(new File(orgPath));
	            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
	            while (entry != null) {

	                // System.out.println(entry.getName());
	                if (entry.isDirectory()) {
	                	
	                	
	                	
	                    new File(tarpath + entry.getName()).mkdirs();
	                    entry = sevenZFile.getNextEntry();
	                    continue;
	                }
	                FileOutputStream out = new FileOutputStream(tarpath
	                        + File.separator + entry.getName());
	                byte[] content = new byte[(int) entry.getSize()];
	                sevenZFile.read(content, 0, content.length);
	                out.write(content);
	                out.close();
	                entry = sevenZFile.getNextEntry();
	            }
	            sevenZFile.close();
	            return true;
	        } catch (FileNotFoundException e) {
	            logger.error("7z解压异常", e);
	            return false;
	        } catch (IOException e) {
	        	logger.error("7z解压异常", e);
	            return false;
	        }
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Un7z u=new Un7z();

	      
			u.apache7ZDecomp("C:\\1111\\视频回放客户端beta1.7z","C:\\222");
		
	}

}
