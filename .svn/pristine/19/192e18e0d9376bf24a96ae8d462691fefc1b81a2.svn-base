package com.jeecg.client.inter.ws.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * @date 2017-02-22
 * @author daer
 * @decrible 预加载XML文件
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class XmlLoad {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(XmlLoad.class);
	/**
	 * xml 文件名 
	 */
	public static final String ResBeanStrcL = "ResBeanStrcL";
	public static final String classmap = "classmap";
	public static final String socketmap_out = "socketmap.out";
	
	private static HashMap xmlMap = new HashMap();
	public static final String filePath = FileUtil.ROOT_PATH +File.separator+"com/jeecg/client/inter/ws/conf/";
	private static final String pattern = ".xml";
	static{
		logger.info("xmlPath="+filePath);
		FileInputStream inputFile = null;
		List<File> list = new ArrayList<File>();
		try {
			FileUtil.getFiles(list, filePath, pattern);
			for (Iterator i = list.iterator(); i.hasNext();)  { 
				File file = (File)i.next();
				Element root = readXML(file);
				String fileName = file.getName().replaceFirst(pattern, "");
				xmlMap.put(fileName, root);
				logger.info("init xmlMap key="+fileName);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("=========================XML文件加载失败！！！============================="+e);
		}finally{
			if(inputFile!=null)
				try {
					inputFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public static HashMap getPropMap(){
		return xmlMap;
	}
	public static Element readXML(String filePath) throws DocumentException{
		return readXML(new File(filePath));
	}
	public static Element readXML(File file) throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document.getRootElement();
	}
	public static Element getElement(String fileName){
		logger.info("getElement fileName="+fileName);
		Element root = (Element) xmlMap.get(fileName);
		return root;
		
	}
}
