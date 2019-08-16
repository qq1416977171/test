package com.jeecg.client.inter.ws.pack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Element;

import com.jeecg.client.inter.ws.utils.XmlLoad;


public class BeanParser {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BeanParser.class);
	
	private Element root = null;	
	private Map<String,SepPackBean> beanMap = null;
	private String beanName = null;
	
	public BeanParser() {
		root = XmlLoad.getElement(XmlLoad.socketmap_out);
		beanMap = new HashMap<String,SepPackBean>();
	}
	
	public BeanParser( String conf_file ) {
		root = XmlLoad.getElement(conf_file);
		beanMap = new HashMap<String,SepPackBean>();
	}
	
	private SepPackBean getSepPackBeanFromXml( Element element ) {
		
		String name = element.attributeValue("NAME");
		String type = element.attributeValue("TYPE");
		String sep = element.attributeValue("SEP");
		
		logger.debug("BEAN NAME[" + name + "] TYPE[" + type + "] SEP[" + sep + "]");
		
		List<PackItem> attrList = new ArrayList<PackItem>();
		
		for( @SuppressWarnings("rawtypes") Iterator itemit = element.elementIterator(); itemit.hasNext(); ) {
			
			Element it = (Element)itemit.next();
			PackItem item = new PackItem();
			
			item.setName( it.attributeValue("NAME") );
			item.setType( it.attributeValue("TYPE") );
			
			if( "java.util.List".equals(item.getType()) ) {
				item.setSep( it.attributeValue("SEP") );
			}
			
			logger.debug("ITEM NAME[" + it.attributeValue("NAME") 
					+ "] TYPE[" + it.attributeValue("TYPE") + "] SEP[" 
					+ it.attributeValue("SEP") + "]");
			
			attrList.add( item );
		}
		
		SepPackBean packBean = new SepPackBean();
		packBean.setName(name);
		packBean.setType(type);
		packBean.setSep(sep);
		packBean.setAttrList(attrList);
		
		return packBean;
	}
	
	public void initPackBean( String trans_code ) {
		
		for( @SuppressWarnings("rawtypes")Iterator mapit = root.elementIterator(); mapit.hasNext(); ) {
			Element transElement = (Element) mapit.next();
			
			if( trans_code.equals( transElement.attributeValue("VALUE")) ) {
				logger.info("Find TRANSCODE[" + trans_code + "]");
				setBeanName( transElement.attributeValue("BEAN") );
				for( @SuppressWarnings("rawtypes")Iterator packit = transElement.elementIterator(); packit.hasNext(); ) {
					Element packElement = (Element) packit.next();
					beanMap.put(packElement.attributeValue("NAME"), getSepPackBeanFromXml( packElement ));
				}
				break;
			}
		}
	}
	
	public void destroyPackBean() {
		beanMap.clear();
		beanMap = null;
		
	}
	
	@SuppressWarnings("unchecked")
	public String parseBean2package( Map<String,SepPackBean> map, String name, Object obj ) {
		
		SepPackBean beanXml = map.get( name );
		String sep = beanXml.getSep();
		
		Class<?> beanClss = null;
		
		try {
			beanClss = Class.forName( beanXml.getType() );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<PackItem> attrlist = beanXml.getAttrList();
		String packStr = "";
		
		for (Iterator<PackItem> it = attrlist.iterator(); it.hasNext(); ) {
			PackItem item = it.next();
			
			String _name = item.getName();
			String _type = item.getType();
			
			
			String str = "";
			Method method = null;
			try {
				logger.info("method is [" + "get" + _name.substring(0,1).toUpperCase() + _name.substring(1) + "]");
				if(null == beanClss){
					return null;
				}
				method = beanClss.getMethod( "get" + _name.substring(0,1).toUpperCase() + _name.substring(1) );
				if(null == method){
					return null;
				}
					
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if( "java.lang.String".equals( _type ) ) {
				
				
				try {
					str = (String) method.invoke(obj);
					logger.info("java.lang.String name[" + _name + "]="+str);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if ( str == null) {
					str = "";
				}
				
			} else if( "java.util.List".equals( _type ) ) {
				
				String _sep = item.getSep();
				List<Object> list = null;
				try {
					list = (List<Object>) method.invoke(obj);
					logger.info("java.lang.List name[" + _name + "]="+list);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if( list != null ) {
					for( Iterator<Object> ii = list.iterator(); ii.hasNext();  ) {
						logger.info("List add name[" + _name + "]");
						Object ob = ii.next();
						str += parseBean2package( map, _name, ob ) + _sep;
					}
				}
			} else {
				logger.info("Bean name[" + _name + "]");
				Object ob = null;
				try {
					ob = (Object) method.invoke(obj);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				if ( ob != null) {
					str = parseBean2package( map, _name, ob);
				}
			}
			
			packStr += str + sep;
		}
		
		return packStr;
	}

	public Map<String, SepPackBean> getBeanMap() {
		return beanMap;
	}

	public void setBeanMap(Map<String, SepPackBean> beanMap) {
		this.beanMap = beanMap;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
}
