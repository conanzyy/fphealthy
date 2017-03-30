package com.friday.pro.core.web.publics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类描述：工具类
 * @author: lxie
 */
public class DateConverter{
	/**
	 * 判断当前日期是否超过截至日期
	 * @param strDate 截止日期
	 * return true：没有超过    flase：已超过 
	 */
	public static boolean timeoutDate(String aMask,String strDate){
		Date timeoutDate = DateConverter.strToDate(aMask, strDate);
		String today = DateConverter.getToday(aMask);
		return DateConverter.strToDate(aMask, today).before(timeoutDate);
	}
	
	public static boolean timeoutDate(String strDate){
		String aMask = "yyyy-MM-dd HH:mm:ss";
		Date timeoutDate = DateConverter.strToDate(aMask, strDate);
		String today = DateConverter.getToday(aMask);
		return DateConverter.strToDate(aMask, today).before(timeoutDate);
	}
	
	/**
	 * 日期按指定格式转化成字符串
	 */
	public static String dateToStr(String aMask, Date date) {
		SimpleDateFormat df = new SimpleDateFormat(aMask);
		String dateAsString = df.format(date);
		return dateAsString;
	}

	public static String getCurrentTime() {
		return getToday("HH:mm:ss");
	}
	
	public static String getCurrentHM() {
		return getToday("HH:mm");
	}
	
	public static String getCurrentH() {
		return getToday("HH");
	}
	/**
	 * 按指定格式返回当天日期的字符串形式
	 */
	public static String getToday(String aMask) {
		Date today = new Date();
		String todayAsString = dateToStr(aMask, today);
		return todayAsString;
	}

	/**
	 * 按默认格式返回当天日期的字符串形式
	 */
	public static String getToday() {
		return getToday("yyyy-MM-dd");
	}

	/**
	 * 把字符串按指定格式转化成Date
	 */
	public static Date strToDate(String aMask, String strDate) {
		SimpleDateFormat format = new SimpleDateFormat(aMask);
		Date date = null;
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 把字符串按默认格式转化成Date
	 */
	public static Date strToDate(String strDate) {
		return strToDate("yyyy-MM-dd", strDate);
	}

	/**
	 * 两个日期相差
	 * @return 分钟
	 */
	public static String dateMathSub(String aMask,String date_str1,String date_str2){
		Date date1 = strToDate(aMask, date_str1);
		Date date2 = strToDate(aMask, date_str2);
        long temp = date2.getTime() - date1.getTime();    //相差毫秒数 
        //long hours = temp / 1000 / 3600;                //相差小时数 
        //long temp2 = temp % (1000 * 3600); 
        //System.out.println("date2 与 date 相差" + hours + "小时"+ mins + "分钟"); 
        long mins = temp / 1000 / 60;                    //相差分钟数 
        return mins+"";
	}
}
