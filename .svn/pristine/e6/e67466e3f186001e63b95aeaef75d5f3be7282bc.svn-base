package com.jeecg.client.inter.ws;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.jeecg.client.inter.ws.base.TransInfo;
import com.jeecg.client.inter.ws.utils.XmlLoad;

public class WsConfiguration {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WsConfiguration.class);
	
	public static void getClassMap( TransInfo trans_info ) throws Exception {
		Element classmapRoot = XmlLoad.getElement(XmlLoad.classmap);
		trans_info.getClssReqMap().clear();
		trans_info.getClssResMap().clear();
		String trans_code = trans_info.getTransCode();
		String requestCode = trans_info.getRequestCode();
		
		for ( Iterator<?> i = classmapRoot.elementIterator(); i.hasNext(); ) {
		       Element element = (Element) i.next();
//		       logger.info("element path [" + element.getPath() + "]");
		       String attValue = element.attributeValue("TRANSCODE");
		       
		       if( attValue.equals( trans_code ) ) {
		    	   for( Iterator<?> ii = element.elementIterator(); ii.hasNext(); ) {
		    		   Element elmt = (Element) ii.next();
		    		   if( elmt.getName().equals("REQUEST") ) {
		    			   logger.debug("["+requestCode+"]==>"+"Request class is [" + elmt.getTextTrim() + "]");
		    			   trans_info.getClssReqMap().put( elmt.attributeValue("NAME"), elmt.getTextTrim() );
		    		   } else if( elmt.getName().equals("HANDLE") ) {
		    			   logger.debug("["+requestCode+"]==>"+"Handle class is [" + elmt.getTextTrim() + "]");
		    			   trans_info.getHandlerMap().put( elmt.attributeValue("NAME"), elmt.getTextTrim() );
		    		   } else if( elmt.getName().equals("RESPONSE") ) {
		    			   logger.debug("["+requestCode+"]==>"+"Response class is [" + elmt.getTextTrim() + "]");
		    			   trans_info.getClssResMap().put( elmt.attributeValue("NAME"), elmt.getTextTrim() );
		    		   } else {
		    			   throw new Exception();
		    		   }
		    	   }
		       }
		}
		
	}
}
