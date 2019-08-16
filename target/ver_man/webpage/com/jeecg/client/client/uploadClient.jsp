<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>文件列表</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" layout="div" dialog="true" beforeSubmit="upload">
	<input type="hidden" id="clientId" value="${id}">
	<fieldset class="step">
	
	<div class="form"> <!-- extend="*.xls;*.xlsx" -->
	<%-- <c:if test="${ftype=='opr'}">
	<t:upload name="fiels" buttonText="上传文件" fileSizeLimit="500MB" uploader="tClientController.do?saveFiles&id=${id}" multi="false" extend="*.doc;*.docx" id="file_upload" formData="documentTitle"></t:upload>
	</c:if>
	
	<c:if test="${ftype!='opr'}">
	</c:if> --%>
	<t:upload name="fiels" buttonText="上传文件" fileSizeLimit="500MB" uploader="tClientController.do?saveFiles&id=${id}" multi="false" extend="*.rar;*.zip;*.7z,*.doc;*.docx" id="file_upload" formData="documentTitle"></t:upload>
	
	
		
	</div>
	<div class="form" id="filediv" style="height: 50px">
		<%-- <c:if test="${not empty attachment }">
			<div class="row" style="margin-left:20px;">
				${attachment.attachmenttitle }.${attachment.extend }
			</div>
		</c:if> --%>
	</div>
	</fieldset>
</t:formvalid>
</body>
</html>
