<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<%-- <div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tClientFileList" title="" actionUrl="tClientFileController.do?downdatagrid&type=${type}" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="clientId" field="clientId" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="filePath" field="filePath" hidden="true"  width="120"></t:dgCol> 
   <t:dgCol title="verName" field="verName"   width="120"></t:dgCol>
   <t:dgCol title="fileName" field="fileName"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgFunOpt funname="downFileClient(filePath,fileName)" title="文件下载" urlclass="ace_button" urlStyle="background-color:#18a689;"  urlfont="fa-cog"></t:dgFunOpt>
   <t:dgDelOpt title="删除" url="tClientFileController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="tClientFileController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tClientFileController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tClientFileController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div> --%>
 <body style="overflow-x:hidden">
 
  <br/>
  <br/>
   <br/>
 <div class="row" style="margin-top: -20px;" align="center" >
		<div class="heads">
		<table border="1" width="385" >
		<tr>
		<th>版本名称</th><th>文件名</th><th>操作</th>
		</tr>
		<c:forEach items="${list}" var="obj">
		<tr>
		<td>${obj.verName}</td><td>${obj.fileName}</td><td align="center"><a style="color:blue" href="#" onclick="downFileClient('${obj.filePath}','${obj.fileName}')">下载</a></td>
		</tr>
		</c:forEach>
		
		</table>
		
									
		</div>
	</div>
 </body>

 
 <script type="text/javascript">

  
  function downFileClient(filePath,fileName){
	 fileName = encodeURI(fileName);
	 fileName = encodeURI(fileName);
	 // alert(fileName);
		var url = "tClientFileController.do?downLoadFile&filePath="+filePath+"&fileName="+fileName;
		console.info(url);
		
		window.location.href= url;
  }
  
  
  </script>