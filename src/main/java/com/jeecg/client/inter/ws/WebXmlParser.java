package com.jeecg.client.inter.ws;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import com.jeecg.client.inter.ws.base.TransInfo;
import com.thoughtworks.xstream.XStream;

public class WebXmlParser {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WebXmlParser.class);
	
	public Request xml2bean( TransInfo trans_info, String xml_str ) {
		
		XStream xst = new XStream();
		xst.alias("ORDER", Request.class);
		String requestCode = trans_info.getRequestCode();
		
		Set<String> aliasSet = trans_info.getClssReqMap().keySet();
		for(Iterator<String> i = aliasSet.iterator(); i.hasNext(); ) {
			String name = i.next();
			if( "data".equals( name ) ) {
				try {
					xst.addDefaultImplementation(Class.forName( trans_info.getMappedReqClassName( name ) ), RequestData.class  );
					
				} catch (ClassNotFoundException e) {
					logger.error("["+requestCode+"]==>"+"data节点重命名错误！"+e);
					e.printStackTrace();
				}
			} else {
				try {
					xst.alias(name, Class.forName( trans_info.getMappedReqClassName( name ) ));
				} catch (ClassNotFoundException e) {
					logger.error("["+requestCode+"]==>"+"其它节点重命名错误！"+e);
					e.printStackTrace();
				}
			}
		}
		return (Request)xst.fromXML(xml_str);
	}
}
