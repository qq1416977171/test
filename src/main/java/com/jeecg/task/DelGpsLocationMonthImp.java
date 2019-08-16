package com.jeecg.task;

import org.apache.log4j.Logger;
import org.jeecgframework.web.system.service.SystemService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

//import com.jeecg.app.interceptor.MobileInterceptor;

/**
 * 
 * 每月1号删除上上个月的campus_gps_location_his表里的数据
 * @author liye
 *
 */
@Service("delGpsLocationMonthImp")
public class DelGpsLocationMonthImp implements Job{

	/**
     * Log日志
     */
    //private static Logger log = Logger.getLogger(MobileInterceptor.class);
    
	/**
	 * 定义systemService
	 */
    @Autowired
	private SystemService systemService;
	
    
    
	public void run(){
		try {
			systemService.executeSql("DELETE  from campus_gps_location_his where location_time < date_add(curdate(),INTERVAL -2 month)");
			//log.debug("输出成功");
		} catch (Exception e) {
			e.printStackTrace();
			//log.error("执行失败");
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		run();
	}
	

	
}
