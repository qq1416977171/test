<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户端类型</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
   <script type="text/javascript">
   
   function checkV(){
	   var id = $("#id").val();
		  if(id==null||id==""){
			  var code = $("#code").val();
			  var orderNum = $("#orderNum").val();
			  
			  var  url="tClientTypeController.do?checkV";
			  var flag = "0";
			  var ms = "";
	      	jQuery.ajax({
	      		url:url,
	      		type:"GET",
	      		dataType:"JSON",
	      		data:{code:code,orderNum:orderNum},
	      		async: false,
	      		success:function(data){
	      			if(!data.success){
	      				flag="1"
	      				ms=data.msg;
	      			}
	      			
	      		}
	      	});
			 if(flag=="1"){
				 tip(ms);
				 return false;
			 } 
		
		  }
		
		
	  }
   
   </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="tClientTypeController.do?save" beforeSubmit="checkV">
		<input id="id" name="id" type="hidden" value="${tClientTypePage.id }">
		<fieldset class="step">
			<div class="form">
		      <label class="Validform_label">客户端类型名称:</label>
		      <input class="inputxt" id="clientTypeName" datatype="*" name="clientTypeName" ignore="checked"   value="${tClientTypePage.clientTypeName}" />
		     <span><font style="color:red">*</font></span> <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">客户端类型编号:</label>
		      <c:if test="${empty tClientTypePage.id}">
		      <input class="inputxt" id="code" name="code" datatype="/^[0-9a-zA-Z_]{1,}$/" ignore="checked" errormsg="字母数字或者下划线"  value="${tClientTypePage.code}" />
		      <span><font style="color:red">*</font></span> <span class="Validform_checktip"></span>
		      </c:if>
		      <c:if test="${not empty tClientTypePage.id}">
		      <input class="inputxt" id="code" name="code" readonly="readonly" datatype="*" ignore="checked"   value="${tClientTypePage.code}" />
		      <span><font style="color:red">*</font></span> <span class="Validform_checktip"></span>
		      </c:if>
		      
		    </div>
			<div class="form">
		      <label class="Validform_label">客户端接口编号:</label> 
		      
		      <c:if test="${empty tClientTypePage.id}">
		      <input class="inputxt" id="orderNum" name="orderNum"  datatype="/^[1-9]\d{0,2}$/" errormsg="请输入3位数内规则的序号" ignore="checked"   value="${tClientTypePage.orderNum}" />
		      <span><font style="color:red">*</font></span> <span class="Validform_checktip"></span>
		      </c:if>
		      <c:if test="${not empty tClientTypePage.id}">
		      <input class="inputxt" id="orderNum" name="orderNum" readonly="readonly"  datatype="/^[1-9]\d{0,2}$/" errormsg="请输入3位数内规则的序号" ignore="checked"   value="${tClientTypePage.orderNum}" />
		      <span><font style="color:red">*</font></span> <span class="Validform_checktip"></span>
		      </c:if>
		      
		      
		    </div>
		    <%--  <div class="form">
		      <label class="Validform_label text-label">文件类型:</label>
				
				<t:dictSelect field="fileType" id="fileType" type="list"    defaultVal="${tClientPage.fileType}"  typeGroupCode="fileType" datatype="*"    hasLabel="false"  title="文件类型"></t:dictSelect>  
		      <span><font style="color:red">*</font></span><span class="Validform_checktip pad"></span>
		    </div> --%>
	    </fieldset>
  </t:formvalid>
 </body>