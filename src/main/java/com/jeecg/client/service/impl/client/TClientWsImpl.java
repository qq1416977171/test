package com.jeecg.client.service.impl.client;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.jeecg.client.service.client.TClientWsI;


@WebService(targetNamespace="com.serviceTargetName",endpointInterface="com.jeecg.client.service.client.TClientWsI")
@Component("upgradeVersion")//spring注入用
public class TClientWsImpl implements TClientWsI {

	@Override
	public String upgradeVersion(String name) {
		
		return "你好，"+name+"  你已成功访问了webservice服务端！" ;
	}

}
