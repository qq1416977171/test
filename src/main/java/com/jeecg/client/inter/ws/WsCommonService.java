package com.jeecg.client.inter.ws;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import com.jeecg.client.inter.ws.base.TransInfo;
import com.jeecg.client.inter.ws.pack.XmlParser;

public class WsCommonService implements WsCommonServiceI {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WsCommonService.class);

	@Override
	public String upgradeVersion(String in) {
		//请求信息错误时发送的结果
		String xmlStr = "<data><returnCode>1111</returnCode><returnMsg>2</returnMsg><requestCode></requestCode></data>";
		try {
			Document document = null;
			Node serviceCodeNode = null;
			Node requestCodeNode = null;

			logger.info("webservice服务的接收请求XML in =\n[" + in + "]");
			TransInfo transInfo = new TransInfo();
			WebXmlParser parser = new WebXmlParser();
			Request reqObj = null;

			try {
				document = DocumentHelper.parseText(in);
			} catch (DocumentException e) {
				e.printStackTrace();
				logger.error("Document 初始化失败！"+e);
				return xmlStr;
			}

			if (null != document) {
				serviceCodeNode = document.selectSingleNode("/ORDER/serviceCode");
				requestCodeNode = document.selectSingleNode("/ORDER/requestCode");
			} else {
				logger.info("Document 初始化失败！");
				return xmlStr;
			}

			if (null == serviceCodeNode || null == requestCodeNode) {
				logger.info( "Node 初始化失败！");
				return xmlStr;
			}
			
			logger.info("当前操作编码:"+serviceCodeNode.getStringValue().trim());
			logger.info("当前请求编码:"+requestCodeNode.getStringValue().trim());
			transInfo.setTransCode(serviceCodeNode.getStringValue().trim());
			transInfo.setRequestCode(requestCodeNode.getStringValue().trim());

			try {
				//WsConfiguration.getClassMap(transInfo);
				WsConfiguration.getClassMap(transInfo);
			
			} catch (Exception e) {
				logger.error("读取配置文件时报错,文件名:classmap;"+e);
				e.printStackTrace();
				return xmlStr;
			}

			reqObj = parser.xml2bean(transInfo, in);
			logger.info("["+transInfo.getRequestCode()+"]==>"+ "请求XML转换成Bean( Request ) =[" + reqObj.getData() + "]");

			// 调用接口函数
			xmlStr = generateXml(transInfo, reqObj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlStr;
	}

	public String generateXml(TransInfo transInfo, Request reqObj) {
		String xmlStr = null;
		try {
			Set<String> aliasSet = transInfo.getHandlerMap().keySet();
			for (Iterator<String> i = aliasSet.iterator(); i.hasNext();) {
				String name = i.next();
				BaseHandler handler = (BaseHandler) Class.forName(transInfo.getHandlerMap().get(name)).newInstance();
				Object respObj = handler.handle(transInfo,reqObj);
				// bean转换成xml文件
				XmlParser xmlParser = new XmlParser();
				xmlStr = xmlParser.bean2xmlWeb(transInfo, respObj);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("["+transInfo.getRequestCode()+"]==>"+"返回XML文件( Response ) =[" + xmlStr + "]");
		return xmlStr;
	}
}
