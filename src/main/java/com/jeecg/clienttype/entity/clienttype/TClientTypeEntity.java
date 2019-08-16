package com.jeecg.clienttype.entity.clienttype;

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
 * @Description: 客户端类型
 * @author zhangdaihao
 * @date 2018-10-15 10:23:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_client_type", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TClientTypeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**clientTypeName*/
	private java.lang.String clientTypeName;
	/**code*/
	private java.lang.String code;
	/**orderNum*/
	private java.lang.String orderNum;
	
	private java.lang.String fileType;
	
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
	 *@return: java.lang.String  clientTypeName
	 */
	@Column(name ="CLIENT_TYPE_NAME",nullable=true,length=255)
	public java.lang.String getClientTypeName(){
		return this.clientTypeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  clientTypeName
	 */
	public void setClientTypeName(java.lang.String clientTypeName){
		this.clientTypeName = clientTypeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  code
	 */
	@Column(name ="CODE",nullable=true,length=255)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  code
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  orderNum
	 */
	@Column(name ="ORDER_NUM",nullable=true,length=10)
	public java.lang.String getOrderNum(){
		return this.orderNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  orderNum
	 */
	public void setOrderNum(java.lang.String orderNum){
		this.orderNum = orderNum;
	}

	@Column(name ="FILE_TYPE",nullable=true,length=2)
	public java.lang.String getFileType() {
		return fileType;
	}

	public void setFileType(java.lang.String fileType) {
		this.fileType = fileType;
	}
	
	
	
}
