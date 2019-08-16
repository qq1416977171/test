package com.jeecg.client.inter.ws.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * Title:日期操作工具类
 * Description: 日期操作
 * </pre>
 * 
 * @author 李健
 * @version 1.00.00
 * @since 2011-8-31
 * 
 *        <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 * </pre>
 */
public class DateUtil {

	public static String DATE_FORMT_NORMAL = "yyyy-MM-dd HH:mm:ss";

	public static String getSysDate(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern,
				java.util.Locale.US);
		return sdf.format(new Date());
	}

	public static String transFormat(String dateStr, String from, String to) {

		DateFormat fromFormat = new SimpleDateFormat(from);
		DateFormat toFormat = new SimpleDateFormat(to);
		Date _date;
		try {
			_date = fromFormat.parse(dateStr);
			dateStr = toFormat.format(_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dateStr;
	}

	/**
	 * 日期加减法
	 * 
	 * @param dNow
	 *            源日期时间
	 * @param minute
	 *            需要加减的单位,比如:Calendar.MINUTE 分钟;Calendar.DAY_OF_MONTH
	 *            天,Calendar.HOUR_OF_DAY 小时等
	 * @param num
	 *            加减的数量,正数为加,负数为见
	 * @param outdateformat
	 *            返回日期格式 比如:yyyyMMdd
	 * @return outTime 处理后的日期
	 * 
	 * @author koudongxu
	 */
	public static String timeAddOrSub(Date dNow, int minute, int num,
			String outdateformat) {
		SimpleDateFormat sdf = new SimpleDateFormat(outdateformat); // 设置返回时间格式
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(dNow);// 把当前时间赋给日历
		calendar.add(minute, num); // 设置操作数量和单位
		dBefore = calendar.getTime(); // 得到处理后的时间
		String outTime = sdf.format(dBefore); // 格式化日期
		String nowTime = sdf.format(dNow); // 格式化源日期时间
		System.out.println(outTime);
		System.out.println(nowTime);
		return outTime;
	}
	/**
	 * Upmp  
	 * ligDate参数没有年，一体化临时补充
	 * 本方法要求：一体化与银联服务器时间误差不能超过1个月。
	 */
	public static String getUpmpLigdate(String ligDate) {
		if(ligDate.length()!=4){
			return ligDate;
		}
		
		String str = "";
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH)+1;
	    int day = cal.get(Calendar.DATE);
//	    String nowDate = month+""+day;
//		System.out.println(year);
//		System.out.println(month);
//		System.out.println(day);
		if(ligDate.substring(0, 2).equals("12")&&month==1){
			str = (year-1)+""+ligDate;
		}else if(ligDate.substring(0, 2).equals("01")&&month==12){
			str = (year+1)+""+ligDate;
		}else{
			str = (year)+""+ligDate;
		}
		
		return str;
	}

	public static void main(String[] args) throws ParseException {
		// String date = "2013-08-22 14:45:24";
		// String a = DateUtil.transFormat(date, DateUtil.DATE_FORMT_NORMAL,
		// "yyyyMMdd");
		// System.out.println(a);
		// System.out.println(DateUtil.getSysDate(""));
//		timeAddOrSub(new Date(), Calendar.MINUTE, -30,
//				"yyyyMMddHHmmss");
		
		System.out.println(DateUtil.getSysDate("yyyyMMddhhmmss"));
	}
}
