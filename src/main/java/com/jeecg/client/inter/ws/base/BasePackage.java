package com.jeecg.client.inter.ws.base;

public class BasePackage {
	
	private String packageStr = null;
	private EnumPackageType packageType = EnumPackageType.UNKNOWN;
	
	public String getPackgeStr() {
		return packageStr;
	}
	
	public void setPackgeStr(String packgeStr) {
		this.packageStr = packgeStr;
	}
	
	public EnumPackageType getPackgeType() {
		return packageType;
	}
	
	public void setPackgeType(EnumPackageType packgeType) {
		this.packageType = packgeType;
	}
	
}
