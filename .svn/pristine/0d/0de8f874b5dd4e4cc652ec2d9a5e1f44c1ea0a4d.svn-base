package com.jeecg.client.controller.client;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.jeecg.client.entity.client.FileVo;
import com.jeecg.client.entity.client.TClientEntity;
import com.jeecg.client.entity.client.TClientFileEntity;
import com.jeecg.client.entity.log.TClientServiceLogEntity;
import com.jeecg.client.ftp.FtpUtil;
import com.jeecg.client.service.client.TClientFileServiceI;

/**   
 * @Title: Controller
 * @Description: 文件管理
 * @author zhangdaihao
 * @date 2018-08-21 11:09:42
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tClientFileController")
public class TClientFileController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TClientFileController.class);

	@Autowired
	private TClientFileServiceI tClientFileService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	@Autowired
	private JdbcDao jdbcDao;
	
	@Autowired
	private TClientFileServiceI tClientFileServiceI;

	/**
	 * 文件管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		String clientId= request.getParameter("clientId");
		
		//tClientFileServiceI.syncFile(clientId);
      //  String sql = "select t.file_name fileName from t_client_file t where t.client_id='"+clientId+"'";
       // List<Map<String, Object>> filelist = systemService.findForJdbc(sql);
      //  if(filelist==null||filelist.size()<1){
		
		
       // }
		
		
		request.setAttribute("clientId", clientId);
		return new ModelAndView("com/jeecg/client/client/tClientFileList");
	}
	
	
	@RequestMapping(params = "syncFiles")
	@ResponseBody
	public AjaxJson syncFiles(TClientFileEntity tClientFile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TClientEntity tClient = systemService.getEntity(TClientEntity.class, tClientFile.getId());
		FtpUtil ftp = FtpUtil.getInstance();
		ftp.cleanList();
		ftp.getAllFileAnDic(tClient.getClientName(),tClientFile.getId());
		List<TClientFileEntity> list = ftp.getList();
		
		for(TClientFileEntity str:list){
			
				//System.out.println(str.getId()+","+str.getClientId()+","+str.getFileName()+","+str.getTClientFileEntity().getId());	
				String isroot = "0";
				if(str.getTClientFileEntity().getId()==null){
					isroot = "1";
				}
				String insql="insert into t_client_file (id,client_id,file_name,parentid,is_root) values ('"+str.getId()+"','"+str.getClientId()+"','"+str.getFileName()+"','"+str.getTClientFileEntity().getId()+"','"+isroot+"')";
				jdbcDao.update(insql);
			
		}
		if(list!=null&&list.size()>0){
			//tClientFileServiceI.batchSave(list);
		}
		ftp.cleanList();
		
		j.setMsg(message);
		return j;
	}
	
	
	@RequestMapping(params = "getFileList")
	@ResponseBody
	public AjaxJson getFileList(HttpServletRequest request, HttpServletResponse response){
		
		AjaxJson j = new AjaxJson();
		
		String clientId = request.getParameter("clientId");
		
		
		
		String parentid = request.getParameter("parentid");
		
		List<TClientFileEntity> tClientFileEntitys = new ArrayList<TClientFileEntity>();
		
		StringBuffer hql = new StringBuffer(" from TClientFileEntity t where clientId ='"+clientId+"' ");
		if(StringUtils.isNotBlank(parentid)){
			
			TClientFileEntity tClientFileEntity = this.systemService.getEntity(TClientFileEntity.class, parentid);
			
			hql.append(" and TClientFileEntity = ?");
			tClientFileEntitys = this.systemService.findHql(hql.toString(), tClientFileEntity);
		} else {
			
			hql.append(" and t.isRoot = ?");
			tClientFileEntitys = this.systemService.findHql(hql.toString(), "1");
		//	hql.append(" and t.TClientFileEntity.id is null");
			//tClientFileEntitys = this.systemService.findHql(hql.toString(), "1");
			//tClientFileEntitys = this.systemService.findHql(hql.toString());
		}
		List<Map<String,Object>> dateList = new ArrayList<Map<String,Object>>();
		if(tClientFileEntitys.size()>0){
			Map<String,Object> map = null;
			String sql = null;
			 Object[] params = null;
			for(TClientFileEntity file:tClientFileEntitys){
				map = new HashMap<String,Object>();
				map.put("id", file.getId());
				map.put("name", file.getFileName());

				map.put("code",file.getId());

			/*	if(ids.length>0){
					for(String id:ids){
						if(id.equals(depart.getId())){
							map.put("checked", true);
						}
					}
				}*/
				
				if(StringUtils.isNotBlank(parentid)){
					map.put("pId", parentid);
				} else{
					map.put("pId", "1");
				}
				//根据id判断是否有子节点
				sql = "select count(1) from t_client_file t where t.parentid = ?";
				params = new Object[]{file.getId()};
				long count = this.systemService.getCountForJdbcParam(sql, params);
				if(count>0){
					map.put("isParent",true);
				}
				dateList.add(map);
			}
		}
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(dateList);
		j.setMsg(jsonArray.toString());
		return j;
	}
	
	
	@RequestMapping(params = "downlist")
	public ModelAndView downlist(HttpServletRequest request) {
		String type= request.getParameter("type");
		request.setAttribute("type", type);
		String sql = "select t.VER_NAME verName,f.id id,f.client_id clientId,f.file_path filePath,f.file_name fileName from t_client t,t_client_file f where t.CLIENT_TYPE='"+type+"' and t.ID = f.client_id";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		request.setAttribute("list", list);
		return new ModelAndView("com/jeecg/client/client/downloadFileList");
	}
	
	
	@RequestMapping(params = "downdatagrid")
	public void downdatagrid(FileVo tClientFile,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String type= request.getParameter("type");
		/*CriteriaQuery cq = new CriteriaQuery(TClientFileEntity.class, dataGrid);
		//查询条件组装器
		String clientId= request.getParameter("clientId");
		cq.eq("clientId", clientId);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tClientFile, request.getParameterMap());
		this.tClientFileService.getDataGridReturn(cq, true);*/
		String sql = "select t.VER_NAME verName,f.id id,f.client_id clientId,f.file_path filePath,f.file_name fileName from t_client t,t_client_file f where t.CLIENT_TYPE='"+type+"' and t.ID = f.client_id";
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		dataGrid.setResults(list);
		TagUtil.datagrid(response, dataGrid);
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
	public void datagrid(TClientFileEntity tClientFile,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TClientFileEntity.class, dataGrid);
		//查询条件组装器
		String clientId= request.getParameter("clientId");
		cq.eq("clientId", clientId);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tClientFile, request.getParameterMap());
		this.tClientFileService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除文件管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TClientFileEntity tClientFile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tClientFile = systemService.getEntity(TClientFileEntity.class, tClientFile.getId());
		message = "文件管理删除成功";
		tClientFileService.delete(tClientFile);
		
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	
	
	@RequestMapping(params = "downLoadFile")
	public void downLoadFile(HttpServletRequest request, HttpServletResponse response, TClientFileEntity file) {
		
				
				FtpUtil ftp = FtpUtil.getInstance();
			
				    byte[] buffer=null;
			        // 清空response
					try{
						String fileName = URLDecoder.decode(file.getFileName(),"UTF-8");
						
						String filePath = file.getFilePath();
						String type =  filePath.split("/")[1];
						String[] arr = {};
						/*String sql = "select t.suffix suffix from t_s_type t where t.suffix is not null and t.typecode='"+type+"' and t.typegroupid = (select s.id from t_s_typegroup s where s.typegroupcode='clientType')";
						//String ext = "";
						String[] arr = {};
						List<Map<String, Object>> yxList =  systemService.findForJdbc(sql);
						if(yxList!=null&&yxList.size()>0){
							String	ext = yxList.get(0).get("suffix").toString();
							arr = ext.split(";");
							
						}*/
						
						//buffer = ftp.downFileByte(file.getFilePath(),fileName);
						Map<String,Object> map = ftp.downFileByte(file.getFilePath(),fileName,arr);
						buffer =  (byte[] ) map.get("array");
						fileName = map.get("fileName").toString();
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
					        
					        TClientServiceLogEntity entity = new TClientServiceLogEntity();
							entity.setFuncName("下载客户端");
							entity.setCreateTime(new Date());
							
							entity.setLogType("1");
							
							entity.setResult("下载成功");
							systemService.save(entity);
					        
					}catch(Exception e){
						   TClientServiceLogEntity entity = new TClientServiceLogEntity();
							entity.setFuncName("下载客户端");
							entity.setCreateTime(new Date());
							
							entity.setLogType("1");
							
							entity.setResult("下载失败");
							systemService.save(entity);
						logger.error("下载客户端失败", e);
					}
	}
	
	
	@RequestMapping(params = "checkFile", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson checkFile(@RequestBody TClientFileEntity file,HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		try {
			String fileName = URLDecoder.decode(file.getFileName(),"UTF-8");
			String filePath = file.getFilePath();
			String type =  filePath.split("/")[1];
			
			//String sql = "select t.suffix suffix from t_s_type t where t.suffix is not null and t.typecode='"+type+"' and t.typegroupid = (select s.id from t_s_typegroup s where s.typegroupcode='clientType')";
			//String ext = "";
			String[] arr = {};
		/*	List<Map<String, Object>> yxList =  systemService.findForJdbc(sql);
			if(yxList!=null&&yxList.size()>0){
				String	ext = yxList.get(0).get("suffix").toString();
				arr = ext.split(";");
				
			}*/
			FtpUtil ftp = FtpUtil.getInstance();
			boolean flag = ftp.checkFileExsit(filePath, fileName,arr);
			if(flag){
				j.setSuccess(true);
			}else{
				j.setSuccess(false);
			}
		} catch (Exception e) {
			j.setSuccess(false);
			e.printStackTrace();
		}
		return j;
	}
	
	

	/**
	 * 添加文件管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TClientFileEntity tClientFile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tClientFile.getId())) {
			message = "文件管理更新成功";
			TClientFileEntity t = tClientFileService.get(TClientFileEntity.class, tClientFile.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tClientFile, t);
				tClientFileService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "文件管理更新失败";
			}
		} else {
			message = "文件管理添加成功";
			tClientFileService.save(tClientFile);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 文件管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TClientFileEntity tClientFile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tClientFile.getId())) {
			tClientFile = tClientFileService.getEntity(TClientFileEntity.class, tClientFile.getId());
			req.setAttribute("tClientFilePage", tClientFile);
		}
		return new ModelAndView("com/jeecg/client/client/tClientFile");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TClientFileEntity> list() {
		List<TClientFileEntity> listTClientFiles=tClientFileService.getList(TClientFileEntity.class);
		return listTClientFiles;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TClientFileEntity task = tClientFileService.get(TClientFileEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TClientFileEntity tClientFile, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TClientFileEntity>> failures = validator.validate(tClientFile);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tClientFileService.save(tClientFile);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tClientFile.getId();
		URI uri = uriBuilder.path("/rest/tClientFileController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TClientFileEntity tClientFile) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TClientFileEntity>> failures = validator.validate(tClientFile);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tClientFileService.saveOrUpdate(tClientFile);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		tClientFileService.deleteEntityById(TClientFileEntity.class, id);
	}
}
