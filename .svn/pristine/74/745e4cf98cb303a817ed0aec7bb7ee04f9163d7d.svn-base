package com.jeecg.client.inter.ws.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConvertUtil {

	public List<Object> listToBean(List<Map<String, Object>> mapList,Class clazz){
		Object po = null;
		List<Object> rsList = new ArrayList<Object>();
		for (Map<String, Object> m : mapList) {
			try {
				po = clazz.newInstance();
//				MyBeanUtils.copyMap2Bean_Nobig(po, m);
				rsList.add(po);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rsList;
	}
}
