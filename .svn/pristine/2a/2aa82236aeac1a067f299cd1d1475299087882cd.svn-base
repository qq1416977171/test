package com.jeecg.client.entity.ftpdb;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: FTP与数据库管理
 * @author zhangdaihao
 * @date 2018-08-29 10:25:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_ftp_db_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TFtpDbInfoEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**ftpIp*/
	private java.lang.String ftpIp;
	/**ftpPort*/
	private java.lang.String ftpPort;
	/**ftpUserName*/
	private java.lang.String ftpUserName;
	/**ftpPassword*/
	private java.lang.String ftpPassword;
	/**dbIp*/
	private java.lang.String dbIp;
	/**dbPort*/
	private java.lang.String dbPort;
	/**dbUserName*/
	private java.lang.String dbUserName;
	/**dbPassword*/
	private java.lang.String dbPassword;
	
	private java.lang.String clientType;
	
	private java.lang.String dbName;
	
	private java.lang.String passPostFix;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ftpIp
	 */
	@Column(name ="FTP_IP",nullable=true,length=32)
	public java.lang.String getFtpIp(){
		return this.ftpIp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ftpIp
	 */
	public void setFtpIp(java.lang.String ftpIp){
		this.ftpIp = ftpIp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ftpPort
	 */
	@Column(name ="FTP_PORT",nullable=true,length=32)
	public java.lang.String getFtpPort(){
		return this.ftpPort;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ftpPort
	 */
	public void setFtpPort(java.lang.String ftpPort){
		this.ftpPort = ftpPort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ftpUserName
	 */
	@Column(name ="FTP_USER_NAME",nullable=true,length=32)
	public java.lang.String getFtpUserName(){
		return this.ftpUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ftpUserName
	 */
	public void setFtpUserName(java.lang.String ftpUserName){
		this.ftpUserName = ftpUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ftpPassword
	 */
	@Column(name ="FTP_PASSWORD",nullable=true,length=255)
	public java.lang.String getFtpPassword(){
		return this.ftpPassword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ftpPassword
	 */
	public void setFtpPassword(java.lang.String ftpPassword){
		this.ftpPassword = ftpPassword;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  dbIp
	 */
	@Column(name ="DB_IP",nullable=true,length=32)
	public java.lang.String getDbIp(){
		return this.dbIp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  dbIp
	 */
	public void setDbIp(java.lang.String dbIp){
		this.dbIp = dbIp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  dbPort
	 */
	@Column(name ="DB_PORT",nullable=true,length=32)
	public java.lang.String getDbPort(){
		return this.dbPort;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  dbPort
	 */
	public void setDbPort(java.lang.String dbPort){
		this.dbPort = dbPort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  dbUserName
	 */
	@Column(name ="DB_USER_NAME",nullable=true,length=32)
	public java.lang.String getDbUserName(){
		return this.dbUserName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  dbUserName
	 */
	public void setDbUserName(java.lang.String dbUserName){
		this.dbUserName = dbUserName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  dbPassword
	 */
	@Column(name ="DB_PASSWORD",nullable=true,length=255)
	public java.lang.String getDbPassword(){
		return this.dbPassword;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  dbPassword
	 */
	public void setDbPassword(java.lang.String dbPassword){
		this.dbPassword = dbPassword;
	}

	@Column(name ="client_type",nullable=true,length=2)
	public java.lang.String getClientType() {
		return clientType;
	}

	public void setClientType(java.lang.String clientType) {
		this.clientType = clientType;
	}

	@Column(name ="db_name",nullable=true,length=255)
	public java.lang.String getDbName() {
		return dbName;
	}

	public void setDbName(java.lang.String dbName) {
		this.dbName = dbName;
	}

	@Column(name ="pass_post_fix",nullable=true,length=32)
	public java.lang.String getPassPostFix() {
		return passPostFix;
	}

	public void setPassPostFix(java.lang.String passPostFix) {
		this.passPostFix = passPostFix;
	}



	
	
	
}
