package com.jeecg.clienttype.controller.clienttype;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.jeecg.client.entity.client.TClientEntity;
import com.jeecg.clienttype.entity.clienttype.TClientTypeEntity;
import com.jeecg.clienttype.service.clienttype.TClientTypeServiceI;

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
 * @Description: 客户端类型
 * @author zhangdaihao
 * @date 2018-10-15 10:23:26
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tClientTypeController")
public class TClientTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TClientTypeController.class);

	@Autowired
	private TClientTypeServiceI tClientTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 客户端类型列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/clienttype/clienttype/tClientTypeList");
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
	public void datagrid(TClientTypeEntity tClientType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TClientTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tClientType, request.getParameterMap());
		this.tClientTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除客户端类型
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TClientTypeEntity tClientType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tClientType = systemService.getEntity(TClientTypeEntity.class, tClientType.getId());
		String sql = "select t.id id from t_client t where t.CLIENT_TYPE='"+tClientType.getCode()+"'";
		List<Map<String, Object>> list =  systemService.findForJdbc(sql);
		if(list!=null&&list.size()>0){
			j.setMsg( "客户端类型已经被应用，不可删除");
			return j;
		}
		
		message = "客户端类型删除成功";
		tClientTypeService.delete(tClientType);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加客户端类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TClientTypeEntity tClientType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tClientType.getId())) {
			message = "客户端类型更新成功";
			TClientTypeEntity t = tClientTypeService.get(TClientTypeEntity.class, tClientType.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tClientType, t);
				tClientTypeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "客户端类型更新失败";
			}
		} else {
			message = "客户端类型添加成功";
			tClientTypeService.save(tClientType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 客户端类型列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TClientTypeEntity tClientType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tClientType.getId())) {
			tClientType = tClientTypeService.getEntity(TClientTypeEntity.class, tClientType.getId());
			req.setAttribute("tClientTypePage", tClientType);
		}
		return new ModelAndView("com/jeecg/clienttype/clienttype/tClientType");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TClientTypeEntity> list() {
		List<TClientTypeEntity> listTClientTypes=tClientTypeService.getList(TClientTypeEntity.class);
		return listTClientTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TClientTypeEntity task = tClientTypeService.get(TClientTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TClientTypeEntity tClientType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TClientTypeEntity>> failures = validator.validate(tClientType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tClientTypeService.save(tClientType);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = tClientType.getId();
		URI uri = uriBuilder.path("/rest/tClientTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TClientTypeEntity tClientType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TClientTypeEntity>> failures = validator.validate(tClientType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		tClientTypeService.saveOrUpdate(tClientType);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		tClientTypeService.deleteEntityById(TClientTypeEntity.class, id);
	}
	
	@RequestMapping(params = "checkV")
	@ResponseBody
	public AjaxJson checkV(TClientTypeEntity tClientType, HttpServletRequest request) {
		
		AjaxJson j = new AjaxJson();
		
		String sql = "select t.id id,t.client_type_name clientTypeName from t_client_type t where t.code='"+tClientType.getCode()+"'";
		List<Map<String, Object>> clist =  systemService.findForJdbc(sql);
		if(clist!=null&&clist.size()>0){
			
					j.setSuccess(false);
					j.setMsg("版本类型编号重复");
			
					return j;
		}
		
		String sql2 = "select t.id id,t.client_type_name clientTypeName from t_client_type t where t.order_num='"+tClientType.getOrderNum()+"'";
		List<Map<String, Object>> clist2 =  systemService.findForJdbc(sql2);
		if(clist2!=null&&clist2.size()>0){
			
					j.setSuccess(false);
					j.setMsg("版本类型序号重复");
					return j;
			
		}
		
		return j;
	}
}
