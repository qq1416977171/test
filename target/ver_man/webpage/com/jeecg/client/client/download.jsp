<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.jeecgframework.core.util.SysThemesUtil,org.jeecgframework.core.enums.SysThemesEnum"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<%
  session.setAttribute("lang","zh-cn");
  SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
  String lhgdialogTheme = SysThemesUtil.getLhgdialogTheme(sysTheme);
%>
<html>
<title>资源下载</title>
<head>
<!-- <script type="text/javascript" src="../../plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../../plug-in/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="../../plug-in/login/js/jquery.tipsy.js"></script> -->
<script type="text/javascript">

/* function fileDownload(fileType){
	
	
	$.dialog({
		width:400,
		height:600,
        id: 'LHG19788',
        title: "文件列表",
        max: false,
        min: false,
        resize: false,
        content: 'url:tClientFileController.do?downlist&type='+fileType,
        lock:true,
     
        close: function(){
        }
    });
	
} */
/* var checkFlag = false;

$.ajax({
     async : false,
     cache : false,
     type : 'POST',
     url : '../../loginController.do?checkDownloadFile&file_type='+fileType,// 请求的action路径
     error : function() {// 请求失败处理函数
     },
     success : function(data) {
       var d = $.parseJSON(data);
       if (d.success) {
			checkFlag = true;
		}else{
			return;
		}
     }
});
if(checkFlag){
	var url = '../../'+ 'loginController.do?fileDownload&file_type='+fileType;
	window.location.href= url;
}else{
	alert("要下载文件不存在，请联系管理员");
} */


function downFileClient(filePath,fileName){
	 //fileName = encodeURI(fileName);
	 fileName = encodeURI(fileName);
	 
	 var file = {
			 filePath: filePath,
			 fileName: fileName
         }; 
	    $.ajax({
         type:"POST",
         url: 'tClientFileController.do?checkFile',
         data: JSON.stringify(file),
         dataType: 'json',
         contentType:"application/json", // 指定这个协议很重要 
         success: function (data) {
        	 if(data.success){
        		 console.info(data.success);
                 fileName = encodeURI(fileName);
               var url = "tClientFileController.do?downLoadFile&filePath="+filePath+"&fileName="+fileName;
         		
         		
         		window.location.href= url;
        	 }else{
        		 alert("要下载文件不存在，请联系管理员");
        	 }
            
         }
     });
	 
	 
	 
	 
	 
	 // alert(fileName);
		//var url = "tClientFileController.do?downLoadFile&filePath="+filePath+"&fileName="+fileName;
		
		
		//window.location.href= url;
 }
</script>
</head>
<body background="plug-in/BG.jpg" style="background-repeat:no-repeat;background-position: center;">
	<div align="center">
	<img src="plug-in/LT.png" style="filter:progid:DXImageTransform.Microsoft.Glow(color=#ccFF66,strength=30">
       </div>
	<div class="row" style="margin-top: -20px;" align="center" >
		<div class="heads">
			<ul class="tile">
			<br/>
			
			
			
			<br/>
			<br/>
			<br/>
            <div class="center"><a  href="loginController.do?login"> <h1 style="color:blue;font-size:20px ">版本管理系统</h1></a></div> 
            <br/>
			<!-- 客户端文件_1,操作手册_2,插件_3 -->
				<a style="text-decoration: underline"><strong>目录</strong></a>
				
				
				<c:forEach items="${list}" var="obj">
				
				<p>
				<li><a style="text-decoration: underline" href="#" onclick="downFileClient('${obj.path}','${obj.fileName}')">${obj.fileName}</a></li>
				
					<%-- <tr>
					<td>${obj.verName}</td><td>${obj.fileName}</td><td align="center"><a style="color:blue" href="#" onclick="downFileClient('${obj.filePath}','${obj.fileName}')">下载</a></td>
					</tr> --%>
		</c:forEach>
				
				
				<!-- <p>
				<li><a style="text-decoration: underline" href="#" onclick="fileDownload('1')">文件上传客户端</a></li>
				<p>
				<li><a style="text-decoration: underline" href="#" onclick="fileDownload('2')">操作手册</a></li>
				<p>
				<li><a style="text-decoration: underline" href="#" onclick="fileDownload('3')">运行插件</a></li> -->
			</ul>						
		</div>
	</div>
<%=lhgdialogTheme %>
</body>
</html>