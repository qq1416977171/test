package com.jeecg.client.controller.log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jeecg.client.entity.log.TClientServiceLogEntity;
import com.jeecg.client.service.log.TClientServiceLogServiceI;

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
 * @Description: 接口日志
 * @author zhangdaihao
 * @date 2018-08-31 16:32:29
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tClientServiceLogController")
public class TClientServiceLogController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TClientServiceLogController.class);

	@Autowired
	private TClientServiceLogServiceI tClientServiceLogService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 接口日志列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/client/log/tClientServiceLogList");
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
	public void datagrid(TClientServiceLogEntity tClientServiceLog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TClientServiceLogEntity.class, dataGrid);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createTime", SortDirection.desc);
		cq.setOrder(map);
		cq.add();
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tClientServiceLog, request.getParameterMap());
		this.tClientServiceLogService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除接口日志
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TClientServiceLogEntity tClientServiceLog, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tClientServiceLog = systemService.getEntity(TClientServiceLogEntity.class, tClientServiceLog.getId());
		message = "接口日志删除成功";
		tClientServiceLogService.delete(tClientServiceLog);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加接口日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TClientServiceLogEntity tClientServiceLog, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tClientServiceLog.getId())) {
			message = "接口日志更新成功";
			TClientServiceLogEntity t = tClientServiceLogService.get(TClientServiceLogEntity.class, tClientServiceLog.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tClientServiceLog, t);
				tClientServiceLogService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "接口日志更新失败";
			}
		} else {
			message = "接口日志添加成功";
			tClientServiceLogService.save(tClientServiceLog);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 接口日志列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TClientServiceLogEntity tClientServiceLog, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tClientServiceLog.getId())) {
			tClientServiceLog = tClientServiceLogService.getEntity(TClientServiceLogEntity.class, tClientServiceLog.getId());
			req.setAttribute("tClientServiceLogPage", tClientServiceLog);
		}
		return new ModelAndView("com/jeecg/client/log/tClientServiceLog");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TClientServiceLogEntity> list() {
		List<TClientServiceLogEntity> listTClientServiceLogs=tClientServiceLogService.getList(TClientServiceLogEntity.class);
		return listTClientServiceLogs;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TClientServiceLogEntity task = tClientServiceLogService.get(TClientServiceLogEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TClientServiceLogEntity tClientServiceLog, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TClientServiceLogEntity>> failures = validator.validate(tClientServiceLog);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tClientServiceLogService.save(tClientServiceLog);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tClientServiceLog.getId();
		URI uri = uriBuilder.path("/rest/tClientServiceLogController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TClientServiceLogEntity tClientServiceLog) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TClientServiceLogEntity>> failures = validator.validate(tClientServiceLog);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tClientServiceLogService.saveOrUpdate(tClientServiceLog);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		tClientServiceLogService.deleteEntityById(TClientServiceLogEntity.class, id);
	}
}
