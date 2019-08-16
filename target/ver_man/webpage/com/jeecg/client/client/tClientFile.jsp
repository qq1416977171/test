<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>文件管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tClientFileController.do?save">
			<input id="id" name="id" type="hidden" value="${tClientFilePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							clientId:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="clientId" name="clientId" ignore="ignore"  value="${tClientFilePage.clientId}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							filePath:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="filePath" name="filePath" ignore="ignore"  value="${tClientFilePage.filePath}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							fileName:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="fileName" name="fileName" ignore="ignore"  value="${tClientFilePage.fileName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>