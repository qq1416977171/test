package com.jeecg.client.inter.ws.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
/**
 * <pre>
 * Title:HTTP请求发送工具类
 * Description: 发送HTTP请求
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
public class HttpUtil {
	/**
	 * 验证主机名 
	 */
	static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
		     public boolean verify(String hostname, SSLSession session) {
		    	 System.out.println(hostname);
		    	 System.out.println(session);
		        return true;
	   }
	};
	
	 private static HttpURLConnection getConnection(String urlString,String method,String charSet) throws Exception {
		 
		 URL url = new URL(urlString);
		 HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		 if (connection instanceof HttpsURLConnection) {
			 ((HttpsURLConnection)connection).setHostnameVerifier(DO_NOT_VERIFY);
		 }
		 
		 connection.setRequestProperty("Charset", charSet);
		 connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset="+charSet);
		 
		 connection.setRequestMethod(method);//默认是GET
		 connection.setDoOutput(true);  //设置为输出模式
		 connection.setDoInput(true);

		 connection.setConnectTimeout(10000);
		 connection.setReadTimeout(30000);
		 connection.setUseCaches(false);


		 
		 return connection;
	}
	/**
	 * 请求http服务器，获取返回页面
	 * @param  urlAddr 链接地址
	 * @return pageContent 返回网页内容
	 * @throws Exception 
	 */
	public static String sendGet(String urlAddr, String charSet)
			throws Exception {
		System.out.println("URL:" + urlAddr);
		OutputStreamWriter out = null;
		InputStream inStream = null;
		BufferedReader reader = null;
		HttpURLConnection conn = getConnection(urlAddr, "GET", charSet);
		try {

			out = new OutputStreamWriter(conn.getOutputStream(), charSet);
			// out.write("username=****&password=*********");
			out.flush(); // 发送
			out.close(); // to clean up

			// 获取服务器的回应：
			String sCurrentLine = "";
			String pageContent = "";
			inStream = conn.getInputStream();

			reader = new BufferedReader(
					new InputStreamReader(inStream, charSet));
			while ((sCurrentLine = reader.readLine()) != null) {
				pageContent += sCurrentLine
						+ System.getProperty("line.separator");

			}
			return pageContent;

		} finally {
			conn.disconnect();
			try {
				if (null != out) {
					out.close();
				}
				if (null != inStream) {
					inStream.close();
				}
				if (null != reader) {
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
 
	/**
	 * 请求http服务器，获取返回页面
	 * @param  urlAddr 链接地址
	 * @param params 参数  &连接
	 * @return pageContent 返回网页内容
	 * @throws Exception 
	 */
	public static String sendPost(String urlAddr, String charSet, String params)
			throws Exception {
		System.out.println("URL:" + urlAddr);
		InputStream inStream = null;
		OutputStreamWriter out = null;
		BufferedReader reader = null;
		HttpURLConnection conn = getConnection(urlAddr, "POST", charSet);
		try {

			out = new OutputStreamWriter(conn.getOutputStream(), charSet);
			out.write(params);
			out.flush(); // 发送
			out.close(); // to clean up

			// 获取服务器的回应：
			String sCurrentLine = "";
			String pageContent = "";
			inStream = conn.getInputStream();

			reader = new BufferedReader(new InputStreamReader(
					inStream, charSet));
			while ((sCurrentLine = reader.readLine()) != null) {
				pageContent += sCurrentLine
						+ System.getProperty("line.separator");

			}
			return pageContent;

		} finally {
			conn.disconnect();
			try{
				if(null != inStream){
					inStream.close();
				}
				if(null != out){
					out.close();
				}
				if(null != reader){
					reader.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception{
		
		String str = HttpUtil.sendGet("http://116.228.21.162:8603/merFrontMgr/orderBusinessServlet", "UTF-8");
		System.out.println(str);
	}
}

