package com.jeecg.client.controller.ftpdb;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jeecg.client.entity.ftpdb.TFtpDbInfoEntity;
import com.jeecg.client.service.ftpdb.TFtpDbInfoServiceI;
import com.jeecg.client.util.Base64Util;
import com.jeecg.client.util.Des;

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
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

/**   
 * @Title: Controller
 * @Description: FTP与数据库管理
 * @author zhangdaihao
 * @date 2018-08-29 10:25:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tFtpDbInfoController")
public class TFtpDbInfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TFtpDbInfoController.class);

	@Autowired
	private TFtpDbInfoServiceI tFtpDbInfoService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * FTP与数据库管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/client/ftpdb/tFtpDbInfoList");
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
	public void datagrid(TFtpDbInfoEntity tFtpDbInfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TFtpDbInfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tFtpDbInfo, request.getParameterMap());
		this.tFtpDbInfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除FTP与数据库管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TFtpDbInfoEntity tFtpDbInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tFtpDbInfo = systemService.getEntity(TFtpDbInfoEntity.class, tFtpDbInfo.getId());
		message = "FTP与数据库管理删除成功";
		tFtpDbInfoService.delete(tFtpDbInfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加FTP与数据库管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TFtpDbInfoEntity tFtpDbInfo, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		
		if(tFtpDbInfo.getFtpPassword()!=null&&!"".equals(tFtpDbInfo.getFtpPassword())){
			String newfP = Base64Util.getBASE64(tFtpDbInfo.getFtpPassword());//Des.jiami(tFtpDbInfo.getFtpPassword());
			tFtpDbInfo.setFtpPassword(newfP);
		}
		
		if(tFtpDbInfo.getDbPassword()!=null&&!"".equals(tFtpDbInfo.getDbPassword())){
			String newdp = Base64Util.getBASE64(tFtpDbInfo.getDbPassword());//Des.jiami(tFtpDbInfo.getDbPassword());
			
			tFtpDbInfo.setDbPassword(newdp);
		}
		
		
		if (StringUtil.isNotEmpty(tFtpDbInfo.getId())) {
			message = "FTP与数据库管理更新成功";
			TFtpDbInfoEntity t = tFtpDbInfoService.get(TFtpDbInfoEntity.class, tFtpDbInfo.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tFtpDbInfo, t);
				tFtpDbInfoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "FTP与数据库管理更新失败";
			}
		} else {
			message = "FTP与数据库管理添加成功";
			tFtpDbInfoService.saveFtp(tFtpDbInfo);;
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * FTP与数据库管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TFtpDbInfoEntity tFtpDbInfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tFtpDbInfo.getId())) {
			tFtpDbInfo = tFtpDbInfoService.getEntity(TFtpDbInfoEntity.class, tFtpDbInfo.getId());
			
			String newfP = Base64Util.getFromBASE64(tFtpDbInfo.getFtpPassword());//Des.jiemi(tFtpDbInfo.getFtpPassword());
			String newdp = Base64Util.getFromBASE64(tFtpDbInfo.getDbPassword());//Des.jiemi(tFtpDbInfo.getDbPassword());
			tFtpDbInfo.setFtpPassword(newfP);
			tFtpDbInfo.setDbPassword(newdp);
			
			req.setAttribute("tFtpDbInfoPage", tFtpDbInfo);
		}
		return new ModelAndView("com/jeecg/client/ftpdb/tFtpDbInfo");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TFtpDbInfoEntity> list() {
		List<TFtpDbInfoEntity> listTFtpDbInfos=tFtpDbInfoService.getList(TFtpDbInfoEntity.class);
		return listTFtpDbInfos;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TFtpDbInfoEntity task = tFtpDbInfoService.get(TFtpDbInfoEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TFtpDbInfoEntity tFtpDbInfo, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TFtpDbInfoEntity>> failures = validator.validate(tFtpDbInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tFtpDbInfoService.save(tFtpDbInfo);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tFtpDbInfo.getId();
		URI uri = uriBuilder.path("/rest/tFtpDbInfoController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TFtpDbInfoEntity tFtpDbInfo) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TFtpDbInfoEntity>> failures = validator.validate(tFtpDbInfo);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tFtpDbInfoService.saveOrUpdate(tFtpDbInfo);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		tFtpDbInfoService.deleteEntityById(TFtpDbInfoEntity.class, id);
	}
}
