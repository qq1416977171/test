<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tFtpDbInfoList" title="FTP与数据库管理" actionUrl="tFtpDbInfoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="客户端类型" field="clientType" dictionary="ftp_type"  width="150"></t:dgCol>
   <t:dgCol title="FTP IP地址" field="ftpIp"   width="120"></t:dgCol>
   <t:dgCol title="FTP端口" field="ftpPort"   width="120"></t:dgCol>
   <t:dgCol title="FTP用户名" field="ftpUserName"   width="120"></t:dgCol>
   <t:dgCol title="FTP密码" field="ftpPassword"   width="120"></t:dgCol>
   <t:dgCol title="数据库地址" field="dbIp"   width="120"></t:dgCol>
   <t:dgCol title="数据库端口" field="dbPort"   width="120"></t:dgCol>
   <t:dgCol title="数据库名" field="dbName"   width="120"></t:dgCol>
   <t:dgCol title="数据库用户名" field="dbUserName"   width="120"></t:dgCol>
   <t:dgCol title="数据库密码" field="dbPassword"   width="120"></t:dgCol>
   <t:dgCol title="密码后缀" field="passPostFix"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tFtpDbInfoController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="tFtpDbInfoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tFtpDbInfoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tFtpDbInfoController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>