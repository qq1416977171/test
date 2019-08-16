package com.jeecg.client.inter.ws.utils;

import java.io.File;

public class ProjectResource {

	public static final String ROOT_PATH = new File("").getAbsolutePath();
	
	static {
		System.out.println("ROOT_PATH=" + ROOT_PATH);
	}

}
