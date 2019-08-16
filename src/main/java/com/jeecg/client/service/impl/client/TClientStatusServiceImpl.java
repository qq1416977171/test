package com.jeecg.client.service.impl.client;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.client.service.client.TClientStatusServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tClientStatusService")
@Transactional
public class TClientStatusServiceImpl extends CommonServiceImpl implements TClientStatusServiceI {
	
}