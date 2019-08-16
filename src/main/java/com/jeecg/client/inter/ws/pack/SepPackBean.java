package com.jeecg.client.inter.ws.pack;

import java.util.List;

public class SepPackBean {
	
	private String name = null;
	
	private String type = null;
	
	private String sep = null;
	
	private List<PackItem> attrList = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSep() {
		return sep;
	}

	public void setSep(String sep) {
		this.sep = sep;
	}

	public List<PackItem> getAttrList() {
		return attrList;
	}

	public void setAttrList(List<PackItem> attrList) {
		this.attrList = attrList;
	}
}
