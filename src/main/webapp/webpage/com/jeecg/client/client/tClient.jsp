<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客户端管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  
   <script type="text/javascript">
   
   $(document).ready(function() {
		
	  
	 
	  if("${tClientPage.id}".length==0){
		  $("#clientType option[value='ftp_client_file']").remove();   
		  $("#clientType option[value='visit_client_file']").remove(); 
	  }
	   
	  
	  if("${tClientPage.id}".length>0){
		  $("#clientType").attr("disabled",true);
		  $("#fileType").attr("disabled",true);
	  }
	
	  
	  
	  /* $('#clientType').change(function(){
		  
		   var value = $(this).children('option:selected').val();
		  
		   if(value.endsWith("_dic")){
			   $("#fileType").val("1");
			  
		   }else{
			   $("#fileType").val("0");
		   }
		 
		   }); */
	          
	});
   
   
  
   
   
   
   function fileName(){
	
	   
	   var id = $("#id").val();
	   if(id==null||id==""){
		   
		   var options=$("#clientType option:selected");  //获取选中的项
  		   var options2=$("#clientName option:selected");  //获取选中的项
  		   
  			var filet = options.text();
  			var filet2 = options2.text();
  		    var ver = $("#verCode").val();
  		    
  		    //filet2+"_"+
  		    var filName = filet+"_"+ver;
  		  $("#fileName").val(filName);
		   
		
		   
		   
		  
		   
	   }
	   
	}
	   
	  
  function checkV(){
	  var id = $("#id").val();
	  if(id==null||id==""){
		  var ctype = $("#clientType").val();
		  var cv = $("#verCode").val();
		  
		  var  url="tClientController.do?checkV";
		  var flag = "0";
		  var ms = "";
      	jQuery.ajax({
      		url:url,
      		type:"GET",
      		dataType:"JSON",
      		data:{clientType:ctype,verCode:cv},
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
		 $("#fileType").attr("disabled",false);
		 
		 if("${tClientPage.id}".length>0){
			  $("#clientType").attr("disabled",false);
		  }
		 
		 var options=$("#clientType option:selected");  //获取选中的项
		   var options2=$("#clientName option:selected");  //获取选中的项
		   
			var filet = options.text();
			var filet2 = options2.text();
		    var ver = $("#verCode").val();
		    
		    //filet2+"_"+
		    var filName = filet+"_"+ver;
		  //$("#fileName").val(filName);
	  }
	 // var ctype = 
	
  }
   function aaa(){
	   alert(1);
   }

   </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="div" action="tClientController.do?save" beforeSubmit="checkV">
		<input id="id" name="id" type="hidden" value="${tClientPage.id }">
		<fieldset class="step">
		   
		    <div class="form">
		      <label class="Validform_label text-label">客户端类型:</label>
				 
				
				<c:if test="${empty tClientPage.id}">
				
				<select id="clientType" name="clientType">
                <c:forEach items="${ctypelist}" var="icon">
                    <option value="${icon.code}" <c:if test="${icon.code==tClientPage.clientType }">selected="selected"</c:if>>${icon.clientTypeName}</option>
                </c:forEach>
            </select>
				
				
		      				<%--  <t:dictSelect  field="clientType" id="clientType"   type="list"    defaultVal="${tClientPage.clientType}"  typeGroupCode="clientType" datatype="*"    hasLabel="false"  title="客户端类型"></t:dictSelect>   --%>
		      </c:if>
		      <c:if test="${not empty tClientPage.id}">
		    <select id="clientType" name="clientType" readonly="readonly">
                <c:forEach items="${ctypelist}" var="icon">
                    <option value="${icon.code}" <c:if test="${icon.code==tClientPage.clientType }">selected="selected"</c:if>>${icon.clientTypeName}</option>
                </c:forEach>
            </select>
		      				<%--  <t:dictSelect field="clientType" id="clientType"  type="list" readonly="readonly" defaultVal="${tClientPage.clientType}"  typeGroupCode="clientType" datatype="*"    hasLabel="false"  title="客户端类型"></t:dictSelect> --%>  
		      </c:if> 
				
					     	
		           
		      <span><font style="color:red">*</font></span><span class="Validform_checktip pad"></span>
		    </div>
		
		    <%-- <div class="form">
		      <label class="Validform_label text-label">客户端名称:</label>
					
					
					<c:if test="${empty tClientPage.id}">
					<t:dictSelect field="clientName" id="clientName"  type="list"  defaultVal="${tClientPage.clientName}" typeGroupCode="clientName" datatype="*"    hasLabel="false"  title="客户端名称"></t:dictSelect>  
		      </c:if>
		      <c:if test="${not empty tClientPage.id}">
		      <t:dictSelect field="clientName" type="list" readonly="readonly" defaultVal="${tClientPage.clientName}" typeGroupCode="clientName" datatype="*"    hasLabel="false"  title="客户端名称"></t:dictSelect>
		      </c:if> 
					
					     	
		          
		      <span><font style="color:red">*</font></span><span class="Validform_checktip pad"></span>
		    </div> --%>
		    
			<div class="form">
		      <label class="Validform_label">版本名称:</label>
		      <input class="inputxt" id="verName" name="verName" datatype="*" ignore="checked"   value="${tClientPage.verName}" />
		     <span><font style="color:red">*</font></span> <span class="Validform_checktip"></span>
		    </div>
			<div class="form">
		      <label class="Validform_label">版本号:</label>
		      
		      <c:if test="${empty tClientPage.id}"><!-- datatype="v?\d{1,2}+\.\d{1,2}+\.\d{1,3}+/"   /\b\d{1,3}\b/g-->
		      <input class="inputxt" id="verCode" name="verCode" ignore="checked" datatype="/^[v]([1-9]\d|[1-9])(\.([1-9]\d|\d))$/" errormsg="请输入规则版本号v**.**"  value="${tClientPage.verCode}" /> 
		      	<span><font style="color:red">*</font></span><span class="Validform_checktip">请输入规则版本号v**.**</span>
		      </c:if>
		      <c:if test="${not empty tClientPage.id}">
		      <input class="inputxt" style="background-color: #eee" id="verCode" name="verCode" readonly="readonly" ignore="checked" datatype="*"  value="${tClientPage.verCode}" />
		     <span><font style="color:red">*</font></span> <span class="Validform_checktip">请输入规则版本号v.**.**.***</span>
		      </c:if> 
		      
		      
		    <!--  <span><font style="color:red">*</font></span> <span class="Validform_checktip"></span> -->
		    </div>
		<%-- 	<div class="form">
		      <label class="Validform_label">版本号:</label>
		      <input class="inputxt" id="verCodeNo" name="verCodeNo" ignore="checked" datatype="*"  value="${tClientPage.verCodeNo}" />
		     <span><font style="color:red">*</font></span> <span class="Validform_checktip"></span>
		    </div> --%>
		    
		    <div class="form">
		      <label class="Validform_label text-label">是否解压:</label>
				
				
				<c:if test="${empty tClientPage.id}">
		      				 <t:dictSelect field="fileType" id="fileType" type="list"    defaultVal="${tClientPage.fileType}"  typeGroupCode="is_unzip" datatype="*"    hasLabel="false"  title="是否解压"></t:dictSelect>  
		      </c:if>
		      <c:if test="${not empty tClientPage.id}">
		      				 <t:dictSelect field="fileType" id="fileType" type="list"  readonly="readonly" defaultVal="${tClientPage.fileType}"  typeGroupCode="is_unzip" datatype="*"    hasLabel="false"  title="是否解压"></t:dictSelect>  
		      </c:if> 
		      <span><font style="color:red">*</font></span><span class="Validform_checktip pad"></span>
		    </div>
		    <c:if test="${empty tClientPage.id}">
		 
		    <input class="inputxt" type="hidden" style="background-color: #eee;width: 226px;" id="fileName" name="fileName" readonly="readonly" ignore="ignore"    value="${tClientPage.fileName}" />
		    </c:if>
		    
		    <c:if test="${not empty tClientPage.id}">
		     <div class="form">
		    <label class="Validform_label">文件名:</label>
		      <input class="inputxt" style="background-color: #eee;width: 226px;" id="fileName" name="fileName" readonly="readonly" ignore="ignore"    value="${tClientPage.fileName}" />
		    </div>
		    </c:if>
		    
		    <div class="form">
		      <label class="Validform_label text-label">备注:</label>
		      <textarea class="inputxt" rows="5" name="remark" ignore="ignore"   style="width: 226px; height: 60px;"/>${tClientPage.remark}</textarea>
		     
		    </div>
			
	    </fieldset>
  </t:formvalid>
 </body>