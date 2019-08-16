package com.jeecg.client.entity.client;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.web.system.pojo.base.TSDepart;

import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 客户端文件
 * @author zhangdaihao
 * @date 2018-08-20 10:44:48
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_client_file", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TClientFileEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**clientId*/
	private java.lang.String clientId;
	/**filePath*/
	private java.lang.String filePath;
	/**fileName*/
	private java.lang.String fileName;
	
	private java.lang.String isRoot;
	
	//上级目录
	private TClientFileEntity TClientFileEntity;
	
	private List<TClientFileEntity> tClientFileEntitys = new ArrayList<TClientFileEntity>();//下属部门
	
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
	 *@return: java.lang.String  clientId
	 */
	@Column(name ="CLIENT_ID",nullable=true,length=36)
	public java.lang.String getClientId(){
		return this.clientId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  clientId
	 */
	public void setClientId(java.lang.String clientId){
		this.clientId = clientId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  filePath
	 */
	@Column(name ="FILE_PATH",nullable=true,length=255)
	public java.lang.String getFilePath(){
		return this.filePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  filePath
	 */
	public void setFilePath(java.lang.String filePath){
		this.filePath = filePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  fileName
	 */
	@Column(name ="FILE_NAME",nullable=true,length=255)
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  fileName
	 */
	public void setFileName(java.lang.String fileName){
		this.fileName = fileName;
	}

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentid")
	public TClientFileEntity getTClientFileEntity() {
		return TClientFileEntity;
	}

	public void setTClientFileEntity(TClientFileEntity tClientFileEntity) {
		TClientFileEntity = tClientFileEntity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TClientFileEntity")
	public List<TClientFileEntity> gettClientFileEntitys() {
		return tClientFileEntitys;
	}

	public void settClientFileEntitys(List<TClientFileEntity> tClientFileEntitys) {
		this.tClientFileEntitys = tClientFileEntitys;
	}

	@Column(name ="is_root",nullable=true,length=2)
	public java.lang.String getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(java.lang.String isRoot) {
		this.isRoot = isRoot;
	}
	
	
	
}
