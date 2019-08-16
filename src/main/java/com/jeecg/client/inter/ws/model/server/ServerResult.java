package com.jeecg.client.inter.ws.model.server;

import com.jeecg.client.inter.ws.ResultData;

public class ServerResult extends ResultData{
	
	/*<ipAddr> FTP IP地址</ipAddr>
	  <portNo> FTP 端口</portNo>
	  <userName> FTP用户名</userName>
	  <userPass> FTP密码</userPass>
	  <filePath>文件路径</filePath>
	  <dbAddr>数据库地址</dbAddr>
	  <dbPort>数据库端口</dbPort>
	  <dbUserName>数据库用户名</dbUserName>
	  <dbUserPass>数据库密码</dbUserPass>*/
	  private String newVersion;
	  private String newVersionNo;
	  private String ipAddr;
	  private String portNo;
	  private String userName;
	  private String userPass;
	  private String filePath;
	  private String dbAddr;
	  private String dbPort;
	  private String dbName;
	  private String dbUserName;
	  private String dbUserPass;
	  private String passPostFix;
	  
	
	  
	  public String getNewVersion() {
			return newVersion;
		}
		public void setNewVersion(String newVersion) {
			this.newVersion = newVersion;
		}
		public String getNewVersionNo() {
			return newVersionNo;
		}
		public void setNewVersionNo(String newVersionNo) {
			this.newVersionNo = newVersionNo;
		}
		  
	  
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getPortNo() {
		return portNo;
	}
	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getDbAddr() {
		return dbAddr;
	}
	public void setDbAddr(String dbAddr) {
		this.dbAddr = dbAddr;
	}
	public String getDbPort() {
		return dbPort;
	}
	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}
	
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbUserName() {
		return dbUserName;
	}
	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}
	public String getDbUserPass() {
		return dbUserPass;
	}
	public void setDbUserPass(String dbUserPass) {
		this.dbUserPass = dbUserPass;
	}
	public String getPassPostFix() {
		return passPostFix;
	}
	public void setPassPostFix(String passPostFix) {
		this.passPostFix = passPostFix;
	}
	
	  
	  
}
