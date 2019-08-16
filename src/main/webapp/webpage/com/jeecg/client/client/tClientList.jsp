<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tClientList" title="客户端管理" actionUrl="tClientController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="客户端类型名称" field="clientTypeName"   width="150"></t:dgCol>
   <t:dgCol title="客户端类型" field="clientType"  hidden="true"></t:dgCol>
  
   <t:dgCol title="版本名称" field="verName"   width="150"></t:dgCol>
   <t:dgCol title="版本号" field="verCode"   width="60"></t:dgCol>
 <%--   <t:dgCol title="版本号" field="verCodeNo"   width="60"></t:dgCol> --%>
   <t:dgCol title="是否解压" field="fileType"  dictionary="is_unzip"   width="100"></t:dgCol>
    <t:dgCol title="文件路径" field="clientName"  dictionary="clientName"   width="150"></t:dgCol>
    <t:dgCol title="文件名" field="fileName"   width="190"></t:dgCol>
   <t:dgCol title="操作时间" field="operTime" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="版本状态" field="status" replace="生效_1,未生效_0"  width="100"></t:dgCol>
    <t:dgCol title="备注" field="remark"   width="160"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="320"></t:dgCol>
   <t:dgDelOpt title="删除" url="tClientController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
  <%--  <t:dgDelOpt title="生效" url="tClientController.do?active&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/> --%>
     <t:dgFunOpt funname="oneDone(id)" title="生效" urlclass="ace_button" urlStyle="background-color:#18a689;"  urlfont="fa-cog"></t:dgFunOpt> 
   
  
   <t:dgFunOpt funname="downClient(id)" title="文件列表" urlclass="ace_button" urlStyle="background-color:#18a689;"  urlfont="fa-cog"></t:dgFunOpt> 
     <t:dgFunOpt funname="uploadClient(id,status,fileName)" title="上传文件" urlclass="ace_button" urlStyle="background-color:#18a689;"  urlfont="fa-cog"></t:dgFunOpt>
   <t:dgToolBar title="录入" icon="icon-add" url="tClientController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tClientController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tClientController.do?addorupdate" funname="detail"></t:dgToolBar>
   </t:datagrid>
  </div>
 </div>
 
  <script type="text/javascript">

  
  
  function oneDone(id) {
		
	
		 
		$.messager.confirm("确认", "生效此客户端？", function (r) {
	        if (r) {	            
	        	var  url="tClientController.do?shengxiao";
	        	jQuery.ajax({
	        		url:url,
	        		type:"GET",
	        		dataType:"JSON",
	        		data:{id:id},
	        		async: false,
	        		success:function(data){
	        			//console.log(data);
	        			if(data.success){
	        				var msg = data.msg;
	        				tip(msg);
	        				reloadTable();
	        				//$.messager.alert('提示信息', "客户端已经生效");
	        			}
	        			
	        		}
	        	});

	 			
	        }
	    });
		
		
	 }
  
  
  
  
  
  function downClient(id){
	  
	  
	  
	  $.dialog({
			width:400,
			height:600,
	        id: 'LHG19788',
	        title: "文件列表",
	        max: false,
	        min: false,
	        resize: false,
	        content: 'url:tClientFileController.do?list&clientId='+id,
	        lock:true,
	     
	        close: function(){
	        }
	    });
	  
	
	  
	 
  }
  
  function uploadClient(id,status,fileName){
	  if(fileName.length>0){
		 // alert("已经成功上传了数据，不可再上传");
		  $.messager.alert('提示信息', "已经成功上传了数据，不可再上传");
		  return false;
	  }
	 
	 
	 
	  if(status=="0"){
		  var title = "客户端上传";
		  var addurl = "tClientController.do?upload&id="+id;
		  var width= "700px";
		  var height = "350px";
		
				width = width?width:700;
				height = height?height:400;
				if(width=="100%" || height=="100%"){
					width = window.top.document.body.offsetWidth;
					height =window.top.document.body.offsetHeight-100;
				}
			    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
				

					/*W.*/$.dialog({//使用W，即为使用顶级页面作为openner，造成打开的次级窗口获取不到关联的主窗口
						content: 'url:'+addurl,
						lock : true,
						width:width,
						zIndex:getzIndex(),
						height:height,
						parent:windowapi,
						title:title,
						opacity : 0.3,
						cache:false,
					    ok: function(){
					    	iframe = this.iframe.contentWindow;
							saveObj();
							return false;
					    },
					    okVal: $.i18n.prop('dialog.submit'),
					    cancelVal: $.i18n.prop('dialog.close'),
					    cancel: true /*为true等价于function(){}*/
					});
	  }else{
		  $.messager.alert('提示信息', "客户端已经生效，不可再上传");
		 // alert("客户端已经生效，不可再上传");
		  return false;
	  }
	  
	 

			
		    //--author：JueYue---------date：20140427---------for：弹出bug修改,设置了zindex()函数
			
	
  }
  </script>