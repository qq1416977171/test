package com.jeecg.client.inter.ws.pack;

import java.util.Iterator;
import java.util.Set;

import com.jeecg.client.inter.ws.Request;
import com.jeecg.client.inter.ws.RequestData;
import com.jeecg.client.inter.ws.Result;
import com.jeecg.client.inter.ws.base.TransInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlParser {

	public String bean2xml( TransInfo trans_info, Object obj ) {
		String xml_str = "";
		
		Request request = new Request();
		request.setServiceCode( trans_info.getTransCode() );
		request.setRequestCode(trans_info.getTransCode());
		request.setData( (RequestData)obj );
		
		XStream xst = new XStream();
		xst.alias("ORDER", Request.class);
		if(obj!=null){
			xst.addDefaultImplementation( RequestData.class, obj.getClass());
		}
		
		Set<String> aliasSet = trans_info.getClssReqMap().keySet();
		for(Iterator<String> i = aliasSet.iterator(); i.hasNext(); ) {
			String name = i.next();
			if( !"data".equals( name ) ) {
				try {
					xst.alias(name, Class.forName( trans_info.getMappedReqClassName( name ) ));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		xml_str = xst.toXML( request );
		
		return xml_str;
	}
	
	public String bean2xmlWeb( TransInfo trans_info, Object obj ) {
		String xml_str = null;
		String requestCode = trans_info.getRequestCode();
		Result resData = (Result)obj;
		XStream xst = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));

		xst.addDefaultImplementation( RequestData.class, obj.getClass());
		xst.alias("data", obj.getClass());
		
		Set<String> aliasSet = trans_info.getClssResMap().keySet();
		for(Iterator<String> i = aliasSet.iterator(); i.hasNext(); ) {
			String name = i.next();
			if( "data".equals( name ) ) {
				try {
					xst.aliasSystemAttribute(name, "class");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					xst.alias(name, Class.forName( trans_info.getMappedResClassName( name ) ));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		xml_str = xst.toXML(resData);
		return xml_str;
	}
	
	public Result xml2bean(TransInfo trans_info, String xml_str ) throws Exception {
		
		XStream xst = new XStream();
		
		Set<String> aliasSet = trans_info.getClssResMap().keySet();
		for(Iterator<String> i = aliasSet.iterator(); i.hasNext(); ) {
			String name = i.next();
			try {
				xst.alias(name, Class.forName( trans_info.getMappedResClassName( name ) ));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Result result = (Result) xst.fromXML( xml_str );
		
		return result;
	}
	
}
