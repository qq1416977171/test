package com.jeecg.clienttype.service.impl.clienttype;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.clienttype.service.clienttype.TClientTypeServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("tClientTypeService")
@Transactional
public class TClientTypeServiceImpl extends CommonServiceImpl implements TClientTypeServiceI {
	
}