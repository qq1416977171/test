<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tClientServiceLogList" title="接口日志" actionUrl="tClientServiceLogController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="日志类型" field="logType"  dictionary="log_type"   width="120"></t:dgCol>
   <t:dgCol title="功能名称" field="funcName"   width="120"></t:dgCol>
   <t:dgCol title="执行结果" field="result"   width="120"></t:dgCol>
   <t:dgCol title="客户端IP地址" field="clientIp"   width="120"></t:dgCol>
   <t:dgCol title="客户端类型" field="clientType"  dictionary="ftp_type"  width="120"></t:dgCol>
   <t:dgCol title="当前版本" field="currentVersion"   width="120"></t:dgCol>
  <%--  <t:dgCol title="接口报文" field="interXml"   width="180"></t:dgCol> --%>
    
   <t:dgCol title="创建时间" field="createTime" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
  <%--  <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tClientServiceLogController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="tClientServiceLogController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tClientServiceLogController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tClientServiceLogController.do?addorupdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>