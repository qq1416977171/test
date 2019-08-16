<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>FTP与数据库管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="tFtpDbInfoController.do?save">
		<input id="id" name="id" type="hidden" value="${tFtpDbInfoPage.id }">
		<fieldset class="step">
		
		   <div class="form">
		      <label class="Validform_label text-label">客户端类型:</label>
		      
		      <c:if test="${empty tFtpDbInfoPage.id}">
		      <t:dictSelect field="clientType" type="list"  defaultVal="${tFtpDbInfoPage.clientType}"  typeGroupCode="ftp_type" datatype="*"    hasLabel="false"  title="客户端类型"></t:dictSelect> 
		      </c:if>
		      <c:if test="${not empty tFtpDbInfoPage.id}">
		      <t:dictSelect field="clientType" type="list" readonly="readonly" defaultVal="${tFtpDbInfoPage.clientType}"  typeGroupCode="ftp_type" datatype="*"    hasLabel="false"  title="客户端类型"></t:dictSelect> 
		      </c:if> 
		      	 
		      <span class="Validform_checktip pad"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">FTP IP地址:</label>
		      <input class="inputxt" id="ftpIp" name="ftpIp" ignore="ignore" datatype="*,url"    value="${tFtpDbInfoPage.ftpIp}" />
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">FTP端口:</label>
		      <input class="inputxt" id="ftpPort" name="ftpPort" ignore="ignore" datatype="n2-7"    value="${tFtpDbInfoPage.ftpPort}" />
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">FTP用户名:</label>
		      <input class="inputxt" id="ftpUserName" name="ftpUserName" ignore="ignore" datatype="*"    value="${tFtpDbInfoPage.ftpUserName}" />
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">FTP密码:</label>
		      <input class="inputxt" type = "password" id="ftpPassword" name="ftpPassword" ignore="ignore" datatype="*"    value="${tFtpDbInfoPage.ftpPassword}" />
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">确认FTP密码:</label>
		      <input class="inputxt" type = "password" id="confirmftpPassword"  ignore="ignore" datatype="*"  recheck="ftpPassword" errormsg="您输入的两次密码不一致"  value="${tFtpDbInfoPage.ftpPassword}" />
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">数据库地址:</label>
		      <input class="inputxt" id="dbIp" name="dbIp" ignore="ignore" datatype="*,url"   value="${tFtpDbInfoPage.dbIp}" />
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">数据库端口:</label>
		      <input class="inputxt" id="dbPort" name="dbPort" ignore="ignore" datatype="n2-7"    value="${tFtpDbInfoPage.dbPort}" />
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">数据库名:</label>
		      <input class="inputxt" id="dbName" name="dbName" ignore="ignore" datatype="/^[A-Za-z0-9_;]+$/"    value="${tFtpDbInfoPage.dbName}" />
		     <span class="Validform_checktip">多个数据库用“;”分开</span>
		    </div>
			<div class="form">
		      <label class="Validform_label">数据库用户名:</label>
		      <input class="inputxt" id="dbUserName" name="dbUserName" ignore="ignore" datatype="*"    value="${tFtpDbInfoPage.dbUserName}" />
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">数据库密码:</label>
		      <input class="inputxt" type = "password" id="dbPassword" name="dbPassword" ignore="ignore" datatype="*"    value="${tFtpDbInfoPage.dbPassword}" />
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">确认数据库密码:</label>
		      <input class="inputxt" type = "password" id="confirmdbPassword"  ignore="ignore" datatype="*" recheck="dbPassword" errormsg="您输入的两次密码不一致"   value="${tFtpDbInfoPage.dbPassword}" />
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">密码后缀:</label>
		      <input class="inputxt" id="passPostFix" name="passPostFix" ignore="ignore" datatype="/^[a-z]{1,32}$/"    value="${tFtpDbInfoPage.passPostFix}" />
		      <span class="Validform_checktip"></span>
		    </div>
	    </fieldset>
  </t:formvalid>
 </body>