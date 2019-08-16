package com.jeecg.client.inter.ws.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * <pre>
 * Title:MD5加密工具类
 * Description: MD5加密
 * </pre>
 * 
 * @author 李健
 * @version 1.00.00
 * @since 2013-8-31
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public final class Md5Util {


/**
 * @description  MD5加密方法 32位
 * @param str 待加密字符串
 * @return MD5加密后的32位字符串
 * @throws NoSuchAlgorithmException 
 */
public static String md5(String str) throws NoSuchAlgorithmException {

    // 操作字符串
    StringBuffer buf = new StringBuffer();

    MessageDigest md = MessageDigest.getInstance("MD5");
   
    // 添加要进行计算摘要的信息,使用 str 的 byte 数组更新摘要。
    md.update(str.getBytes());

    // 计算出摘要,完成哈希计算。
    byte b[] = md.digest();
    int i;

    for (int offset = 0; offset < b.length; offset++) {

	     i = b[offset];
	
	     if (i < 0) {
	      i += 256;
	     }
	
	     if (i < 16) {
	      buf.append("0");
	     }
	
	     // 将整型 十进制 i 转换为16位，用十六进制参数表示的无符号整数值的字符串表示形式。
	     buf.append(Integer.toHexString(i));

    }

   return buf.toString();
}
/**
 * 
 * @description  MD5加密方法 16位
 * @param str 待加密字符串
 * @return MD5加密后的16位字符串
 * @throws NoSuchAlgorithmException 
 */
public static String md5For16(String str) throws NoSuchAlgorithmException{
	return md5(str).substring(8,24);
}

}

