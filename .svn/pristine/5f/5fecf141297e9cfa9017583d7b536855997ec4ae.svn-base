package com.jeecg.client.inter.ws.base;

import java.util.HashMap;
import java.util.Map;

import com.jeecg.client.inter.ws.bean.BaseBean;

public class TransInfo{
	
	/**
	 * transCode 不一定使用。
	 */
	private String transCode = null;
	
	private String requestCode = null;
	
	private BaseBean reqBean = null;
	
	private BaseBean resBean = null;
	
	private BasePackage reqPack = null;
	
	private BasePackage resPack = null;
	
	private TransStatus transStatus = TransStatus.UNKNOWN;
	
	private Map<String, String> clssReqMap = new HashMap<String, String>();
	
	private Map<String, String> clssResMap = new HashMap<String, String>();
	
	private Map<String, String> handlerMap = new HashMap<String, String>();
	
	public String getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}

	public TransInfo() {
		//:TODO 添加必要的初始化操作
	}
	
	public String getTransCode() {
		return transCode;
	}
	
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	
	public BasePackage getReqPack() {
		return reqPack;
	}
	
	public void setReqPack(BasePackage reqPack) {
		this.reqPack = reqPack;
	}
	
	public BasePackage getResPack() {
		return resPack;
	}
	
	public void setResPack(BasePackage resPack) {
		this.resPack = resPack;
	}
	
	public TransStatus getTransStatus() {
		return transStatus;
	}
	
	public void setTransStatus(TransStatus transStatus) {
		this.transStatus = transStatus;
	}

	public BaseBean getReqBean() {
		return reqBean;
	}

	public void setReqBean(BaseBean reqBean) {
		this.reqBean = reqBean;
	}

	public BaseBean getResBean() {
		return resBean;
	}

	public void setResBean(BaseBean resBean) {
		this.resBean = resBean;
	}

	public Map<String, String> getClssReqMap() {
		return clssReqMap;
	}

	public void setClssReqMap(Map<String, String> clssReqMap) {
		this.clssReqMap = clssReqMap;
	}

	public Map<String, String> getClssResMap() {
		return clssResMap;
	}

	public void setClssResMap(Map<String, String> clssResMap) {
		this.clssResMap = clssResMap;
	}

	public String getMappedReqClassName( String name ) {
		return clssReqMap.get( name );
	}
	
	public String getMappedResClassName( String name ) {
		return clssResMap.get( name );
	}

	public Map<String, String> getHandlerMap() {
		return handlerMap;
	}

	public void setHandlerMap(Map<String, String> handlerMap) {
		this.handlerMap = handlerMap;
	}
}
