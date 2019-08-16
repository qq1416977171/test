package com.jeecg.client.entity.client;

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
 * @Description: 客户端管理
 * @author zhangdaihao
 * @date 2018-08-17 08:41:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_client", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TClientEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**客户端类型*/
	private java.lang.String clientType;
	/**客户端名称*/
	private java.lang.String clientName;
	/**版本名称*/
	private java.lang.String verName;
	/**版本*/
	private java.lang.String verCode;
	/**版本号*/
	private java.lang.String verCodeNo;
	/**操作时间*/
	private java.util.Date operTime;
	/**版本状态*/
	private java.lang.String status;
	
	/**备注*/
	private java.lang.String remark;
	
	private java.lang.String rootPath;
	
	/**备注*/
	private java.lang.String fileType;
	
	private java.lang.String fileName;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户端类型
	 */
	@Column(name ="CLIENT_TYPE",nullable=true,length=32)
	public java.lang.String getClientType(){
		return this.clientType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户端类型
	 */
	public void setClientType(java.lang.String clientType){
		this.clientType = clientType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户端名称
	 */
	@Column(name ="CLIENT_NAME",nullable=true,length=32)
	public java.lang.String getClientName(){
		return this.clientName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户端名称
	 */
	public void setClientName(java.lang.String clientName){
		this.clientName = clientName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本名称
	 */
	@Column(name ="VER_NAME",nullable=true,length=255)
	public java.lang.String getVerName(){
		return this.verName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本名称
	 */
	public void setVerName(java.lang.String verName){
		this.verName = verName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本
	 */
	@Column(name ="VER_CODE",nullable=true,length=32)
	public java.lang.String getVerCode(){
		return this.verCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本
	 */
	public void setVerCode(java.lang.String verCode){
		this.verCode = verCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本号
	 */
	@Column(name ="VER_CODE_NO",nullable=true,length=32)
	public java.lang.String getVerCodeNo(){
		return this.verCodeNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本号
	 */
	public void setVerCodeNo(java.lang.String verCodeNo){
		this.verCodeNo = verCodeNo;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  操作时间
	 */
	@Column(name ="OPER_TIME",nullable=true)
	public java.util.Date getOperTime(){
		return this.operTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  操作时间
	 */
	public void setOperTime(java.util.Date operTime){
		this.operTime = operTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本状态
	 */
	@Column(name ="STATUS",nullable=true,length=32)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}

	@Column(name ="REMARK",nullable=true,length=255)
	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	@Column(name ="ROOT_PATH",nullable=true,length=255)
	public java.lang.String getRootPath() {
		return rootPath;
	}

	public void setRootPath(java.lang.String rootPath) {
		this.rootPath = rootPath;
	}

	@Column(name ="FILE_TYPE",nullable=true,length=2)
	public java.lang.String getFileType() {
		return fileType;
	}

	public void setFileType(java.lang.String fileType) {
		this.fileType = fileType;
	}

	@Column(name ="FILE_NAME",nullable=true,length=255)
	public java.lang.String getFileName() {
		return fileName;
	}

	
	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}
	
	
	
	
}
