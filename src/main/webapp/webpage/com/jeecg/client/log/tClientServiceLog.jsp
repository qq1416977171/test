<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>接口日志</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="tClientServiceLogController.do?save">
		<input id="id" name="id" type="hidden" value="${tClientServiceLogPage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">clientIp:</label>
		      <input class="inputxt" id="clientIp" name="clientIp" ignore="ignore"   value="${tClientServiceLogPage.clientIp}" />
		      <span class="Validform_checktip"></span>
		    </div>
			<%-- <div class="form">
		      <label class="Validform_label">clientType:</label>
		      <input class="inputxt" id="clientType" name="clientType" ignore="ignore"   value="${tClientServiceLogPage.clientType}" />
		      <span class="Validform_checktip"></span>
		    </div> --%>
			<div class="form">
		      <label class="Validform_label">currentVersion:</label>
		      <input class="inputxt" id="currentVersion" name="currentVersion" ignore="ignore"   value="${tClientServiceLogPage.currentVersion}" />
		      <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">result:</label>
		      <input class="inputxt" id="result" name="result" ignore="ignore"   value="${tClientServiceLogPage.result}" />
		      <span class="Validform_checktip"></span>
		    </div>
		    <div class="form">
		      <label class="Validform_label">报文:</label>
		      
		      <input class="inputxt" id="interXml" name="interXml" ignore="ignore"   value="${tClientServiceLogPage.interXml}" />
		      <span class="Validform_checktip"></span>
		    </div>
			<%-- <div class="form">
		      <label class="Validform_label">createTime:</label>
		      <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createTime" name="createTime" ignore="ignore"     value="<fmt:formatDate value='${tClientServiceLogPage.createTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
		      <span class="Validform_checktip"></span>
		    </div> --%>
	    </fieldset>
  </t:formvalid>
 </body>