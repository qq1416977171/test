package com.jeecg.client.entity.log;

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
 * @Description: 接口日志
 * @author zhangdaihao
 * @date 2018-08-31 16:32:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_client_service_log", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TClientServiceLogEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**clientIp*/
	private java.lang.String clientIp;
	/**clientType*/
	private java.lang.String clientType;
	/**currentVersion*/
	private java.lang.String currentVersion;
	/**result*/
	private java.lang.String result;
	/**createTime*/
	private java.util.Date createTime;
	
	
	private java.lang.String interXml;
	/**currentVersion*/
	private java.lang.String funcName;
	/**result*/
	private java.lang.String logType;
	
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
	 *@return: java.lang.String  clientIp
	 */
	@Column(name ="CLIENT_IP",nullable=true,length=36)
	public java.lang.String getClientIp(){
		return this.clientIp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  clientIp
	 */
	public void setClientIp(java.lang.String clientIp){
		this.clientIp = clientIp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  clientType
	 */
	@Column(name ="CLIENT_TYPE",nullable=true,length=2)
	public java.lang.String getClientType(){
		return this.clientType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  clientType
	 */
	public void setClientType(java.lang.String clientType){
		this.clientType = clientType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  currentVersion
	 */
	@Column(name ="CURRENT_VERSION",nullable=true,length=36)
	public java.lang.String getCurrentVersion(){
		return this.currentVersion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  currentVersion
	 */
	public void setCurrentVersion(java.lang.String currentVersion){
		this.currentVersion = currentVersion;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  result
	 */
	@Column(name ="RESULT",nullable=true,length=32)
	public java.lang.String getResult(){
		return this.result;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  result
	 */
	public void setResult(java.lang.String result){
		this.result = result;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createTime
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createTime
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}

	@Column(name ="INTER_XML",nullable=true,length=500)
	public java.lang.String getInterXml() {
		return interXml;
	}

	public void setInterXml(java.lang.String interXml) {
		this.interXml = interXml;
	}

	@Column(name ="FUNC_NAME",nullable=true,length=36)
	public java.lang.String getFuncName() {
		return funcName;
	}

	public void setFuncName(java.lang.String funcName) {
		this.funcName = funcName;
	}

	@Column(name ="LOG_TYPE",nullable=true,length=2)
	public java.lang.String getLogType() {
		return logType;
	}

	public void setLogType(java.lang.String logType) {
		this.logType = logType;
	}
	
	
	
}
