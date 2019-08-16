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
 * @Description: 客户端状态
 * @author zhangdaihao
 * @date 2018-08-20 11:28:53
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_client_status", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TClientStatusEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**客户端类型*/
	private java.lang.String clientType;
	/**客户端名称*/
	private java.lang.String clientName;
	/**状态*/
	private java.lang.String status;
	
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
	 *@return: java.lang.String  状态
	 */
	@Column(name ="STATUS",nullable=true,length=8)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
}
