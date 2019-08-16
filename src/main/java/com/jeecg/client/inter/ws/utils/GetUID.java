package com.jeecg.client.inter.ws.utils;

import java.util.Random;

public class GetUID {
	
	public static long getUID(){
		return System.currentTimeMillis() * 1000L + (long)(new Random()).nextInt(999);
	}
}
