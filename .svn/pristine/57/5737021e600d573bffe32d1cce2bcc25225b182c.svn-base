package com.jeecg.client.util;

import sun.misc.BASE64Decoder;

public class Base64Util {

	/**
	 * 字符串转Base64编码
	 * 
	 * @param s
	 * @return
	 */
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	/**
	 * 将 字符数组 进行 BASE64 编码
	 * 
	 * @param s
	 * @return
	 */
	public static String getBASE64Byte(byte[] s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s);
	}

	// 将 BASE64 编码的字符串 s 进行解码
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		// String str = "123456";
		String str = "wt123456";

		System.out.println("加密后："+Base64Util.getBASE64(str));
		System.out.println("解密后："+Base64Util.getFromBASE64(Base64Util.getBASE64(str)));
	}
}
