package com.jeecg.client.controller.client;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;

import com.jeecg.client.entity.client.TClientEntity;
import com.jeecg.client.entity.client.TClientVo;
import com.jeecg.client.entity.log.TClientServiceLogEntity;
import com.jeecg.client.ftp.FtpUtil;
import com.jeecg.client.service.client.TClientServiceI;
import com.jeecg.clienttype.entity.clienttype.TClientTypeEntity;
import com.jeecg.demo.entity.TSDocument;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: 客户端管理
 * @author zhangdaihao
 * @date 2018-08-17 08:41:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tClientController")
public class TClientController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TClientController.class);

	@Autowired
	private TClientServiceI tClientService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 客户端管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/client/client/tClientList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TClientVo tClient,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		/*CriteriaQuery cq = new CriteriaQuery(TClientEntity.class, dataGrid);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operTime", SortDirection.desc);
		cq.setOrder(map);
		cq.add();
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tClient, request.getParameterMap());
		this.tClientService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);*/
		
		List<Map<String, Object>> showList = null;//显示前端当前页的数据

		int showLen = dataGrid.getRows();//动态得到前端用户需要当前页面显示多少条
		StringBuffer sb = new StringBuffer("");
		//sb.append(str)
		sb.append("select t.id id,t.CLIENT_TYPE clientType,g.client_type_name clientTypeName,t.CLIENT_NAME clientName,t.VER_NAME verName,t.VER_CODE verCode,t.OPER_TIME operTime,t.STATUS status,t.FILE_TYPE fileType,t.REMARK remark,t.file_name fileName from t_client t,t_client_type g where t.CLIENT_TYPE = g.code order by t.OPER_TIME desc");
	
		//判断当前角色--为班级管理
		//查询角色
	
		String sql = sb.toString();
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		int totalPage =list.size()%showLen==0?list.size()/showLen:list.size()/showLen+1;//得到当前页
		if(dataGrid.getPage() == 1){
			if(showLen > list.size()){
			showLen = list.size();
			}
			showList = list.subList(0,showLen);
		}else{

		//总共有40条数据,以每10条进行分页,现在点击第二页显示值应该为 11 - 20

		if(dataGrid.getPage() == totalPage){

		showList =

		list.subList(((dataGrid.getPage()-1)*showLen),list.size());

		}else{

		showList =

		list.subList(((dataGrid.getPage()-1)*showLen),((dataGrid.getPage()-1)*showLen)+showLen);

		}

		}

		dataGrid.setResults(showList); //显示数据

		dataGrid.setTotal(list.size());
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除客户端管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TClientEntity tClient, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tClient = systemService.getEntity(TClientEntity.class, tClient.getId());
		message = "客户端管理删除成功";
		if("1".equals(tClient.getStatus())){
			message="生效的客户端不允许删除";
		}else{
			tClientService.delClient(tClient);
			
			TClientServiceLogEntity entity = new TClientServiceLogEntity();
			entity.setFuncName("删除客户端");
			entity.setCreateTime(new Date());
			
			entity.setLogType("1");
			
			entity.setResult("删除成功");
			systemService.save(entity);
			
			
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}
		/*FtpUtil ftp = FtpUtil.getInstance();
		ftp.reall("/"+tClient.getClientType()+"/"+tClient.getVerCode());*/
		
		
		j.setMsg(message);
		return j;
	}
	
	
	


	/**
	 * 添加客户端管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TClientEntity tClient, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tClient.getId())) {
			message = "客户端管理更新成功";
			TClientEntity t = tClientService.get(TClientEntity.class, tClient.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tClient, t);
				tClientService.saveOrUpdate(t);
				
				TClientServiceLogEntity entity = new TClientServiceLogEntity();
				entity.setFuncName("修改客户端");
				entity.setCreateTime(new Date());
				
				entity.setLogType("1");
				
				entity.setResult("修改成功");
				systemService.save(entity);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "客户端管理更新失败";
			}
		} else {
			
			//j.setSuccess(false);
			message = "客户端管理添加成功";
			tClient.setStatus("0");
			tClient.setOperTime(new Date());
			int root = (int)((Math.random()*9+1)*100000);
			String rootPath = String.valueOf(root);
			tClient.setRootPath(rootPath);
			String path = "/"+tClient.getClientType()+"/"+tClient.getVerCode();
			tClient.setClientName(path);
			
			String verCodeNo = tClient.getVerCode().replaceAll("v", "");//.replaceAll("\\.","")
			
			String[] arr = verCodeNo.split("\\.");
			String arr1 = arr[0];
			String arr2 = arr[1];
			while(arr1.length()<2){
				arr1 = "0"+arr1;
			}
			while(arr2.length()<2){
				arr2 = "0"+arr2;
			}
			String vn = arr1+arr2;
			Integer vno = Integer.parseInt(vn);
			tClient.setVerCodeNo(vno+""); 
			tClientService.saveClient(tClient);
			
			TClientServiceLogEntity entity = new TClientServiceLogEntity();
			entity.setFuncName("添加客户端");
			entity.setCreateTime(new Date());
			
			entity.setLogType("1");
			
			entity.setResult("添加成功");
			systemService.save(entity);
			
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	
	
	@RequestMapping(params = "checkV")
	@ResponseBody
	public AjaxJson checkV(TClientEntity tClient, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		
		//String sql = "select max(t.VER_CODE) maxNo from t_client t where t.CLIENT_TYPE='"+tClient.getClientType()+"'";
		String sql = "select a.VER_CODE maxNo from t_client a where a.CLIENT_TYPE='"+tClient.getClientType()+"' and (a.VER_CODE_NO+0)=(select max(t.VER_CODE_NO+0)  from t_client t where t.CLIENT_TYPE='"+tClient.getClientType()+"')";
		List<Map<String, Object>> clist =  systemService.findForJdbc(sql);
		if(clist!=null&&clist.size()>0){
			if(clist.get(0).get("maxNo")!=null){
				String maxVerNo = clist.get(0).get("maxNo").toString();
				String currenV = tClient.getVerCode();
				/*String maxNo = maxVerNo.replaceAll("v", "");*/
				//String currenV = tClient.getVerCode().replaceAll("v", "");
				//float mNo = Float.parseFloat(maxNo);
				
				//float curNo = Float.parseFloat(currenV);
				int co = compareVersion(currenV,maxVerNo);
				if(co<=0){
					j.setSuccess(false);
					j.setMsg("请添加比"+maxVerNo+"更高版本");
				}
			}
			
		}
		
		return j;
	}
	
	@RequestMapping(params = "shengxiao")
	@ResponseBody
	public AjaxJson shengxiao(String id, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TClientEntity tClient = systemService.getEntity(TClientEntity.class, id);
		if("".equals(tClient.getFileName())||tClient.getFileName()==null){
			j.setMsg("客户端未上传文件，不可生效");
			//j.setSuccess(false);
			return j;
		}
		
		
		message = "客户端生效成功";
		
		tClientService.activeClient(tClient);
		TClientServiceLogEntity entity = new TClientServiceLogEntity();
		entity.setFuncName("生效客户端");
		entity.setCreateTime(new Date());
		
		entity.setLogType("1");
		
		entity.setResult("生效成功");
		systemService.save(entity);
		//systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}
	
public  int compareVersion(String version1, String version2)  {  
		   
		
		String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."； 
		for(int i = 0 ; i<versionArray1.length ; i++){ //如果位数只有一位则自动补零（防止出现一个是04，一个是5 直接以长度比较）
		if(versionArray1[i].length() == 1){
		versionArray1[i] = "0" + versionArray1[i];
		}
		}
		String[] versionArray2 = version2.split("\\."); 
		for(int i = 0 ; i<versionArray2.length ; i++){//如果位数只有一位则自动补零
		if(versionArray2[i].length() == 1){
		versionArray2[i] = "0" + versionArray2[i];
		}
		}
		int idx = 0; 
		int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值 
		int diff = 0; 
		while (idx < minLength 
		&& (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度 
		&& (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符 
		++idx; 
		} 
		//如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大； 
		diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length; 
		return diff; 
		}
	
	@RequestMapping(params = "upload")
	public ModelAndView upload(TClientEntity client, ModelMap map,HttpServletRequest request) {
		if (StringUtil.isNotEmpty(client.getId())) {
			client = systemService.getEntity(TClientEntity.class, client.getId());
			map.put("id", client.getId());
			map.put("ftype", client.getClientType());
			/*TSAttachment attachment = systemService.get(TSAttachment.class, doc.getId());
			map.put("attachment",attachment);*/
		}
		return new ModelAndView("com/jeecg/client/client/uploadClient");
	}
	
	
	@RequestMapping(params = "downLoadFile")
	public void downLoadFile(HttpServletRequest request, HttpServletResponse response, TClientEntity client) {
		if (StringUtil.isNotEmpty(client.getId())) {
			String fileName = client.getClientName();
			client = systemService.getEntity(TClientEntity.class, client.getId());
			String type = client.getClientType();
			if("1".equals(type)){
				String path = "/"+client.getClientName()+"/"+client.getVerCode();
				FtpUtil ftp = FtpUtil.getInstance();
			
				    byte[] buffer=null;
			        // 清空response
					try{
						//buffer = ftp.downFileByte(path,fileName);
						   //response.reset();
					        response.setContentType("text/html;charset=UTF-8");
					        // 设置response的Header
					        //        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"),"ISO-8859-1"));
					        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(),"ISO-8859-1"));
					        //response.addHeader("Content-Length", "" + fis.length());
					        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
					        response.setContentType("application/octet-stream");
					        toClient.write(buffer);
					        toClient.flush();
					        toClient.close();
					}catch(Exception e){
						e.printStackTrace();
					}

			}
			
			
		}
		
	}
	
	
	
	@RequestMapping(params = "getFileList")
	@ResponseBody
	public AjaxJson getFileList(String id,HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		try {
			String sql = "select t.file_name fileName from t_client_file t where t.client_id='"+id+"'";
			List<Map<String, Object>> fileList =  systemService.findForJdbc(sql);
			
			//将List转换成JSON存储
			JSONArray result = new JSONArray();
			
			if(fileList!=null && fileList.size()>0){
				for(int i=0;i<fileList.size();i++){
					JSONObject jsonParts = new JSONObject();
					jsonParts.put("fileName", fileList.get(i).get("fileName"));
					result.add(jsonParts);	
				}
			}
			Map<String,Object> attrs = new HashMap<String, Object>();
			attrs.put("fileList", result);
			j.setAttributes(attrs);
		
		} catch (Exception e) {
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, TClientEntity client) {
		AjaxJson j = new AjaxJson();
		
		Map<String, Object> attributes = new HashMap<String, Object>();
		client = systemService.getEntity(TClientEntity.class, client.getId());
		UploadFile uploadFile = new UploadFile(request);
		String message = tClientService.uploadClient(uploadFile, client);
		
		
		
		j.setMsg(message);
		j.setAttributes(attributes);
		return j;
	}

	/**
	 * 客户端管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TClientEntity tClient, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tClient.getId())) {
			tClient = tClientService.getEntity(TClientEntity.class, tClient.getId());
			req.setAttribute("tClientPage", tClient);
		}
		
		List<TClientTypeEntity> ctypelist = systemService.getList(TClientTypeEntity.class);
		req.setAttribute("ctypelist", ctypelist);
		
		return new ModelAndView("com/jeecg/client/client/tClient");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TClientEntity> list() {
		List<TClientEntity> listTClients=tClientService.getList(TClientEntity.class);
		return listTClients;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TClientEntity task = tClientService.get(TClientEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TClientEntity tClient, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TClientEntity>> failures = validator.validate(tClient);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tClientService.save(tClient);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tClient.getId();
		URI uri = uriBuilder.path("/rest/tClientController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TClientEntity tClient) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TClientEntity>> failures = validator.validate(tClient);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tClientService.saveOrUpdate(tClient);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		tClientService.deleteEntityById(TClientEntity.class, id);
	}
}
