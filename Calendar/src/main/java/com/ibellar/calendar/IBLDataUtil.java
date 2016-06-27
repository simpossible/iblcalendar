package com.ibellar.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class IBLDataUtil {
	 public static String DATE_FORMAT = "yyyy-MM-dd";
	 
	 // 长日期格式
	 public static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	 
	 /**
	  * 将日期格式的字符串转换为长整型
	  * 
	  * @param date
	  * @param format
	  * @return
	  */
	 public static long convertTime2long(String date, String format) {
		
		 return  0;
	 }
	 
	 /**
	  * 将长整型数字转换为日期格式的字符串 毫秒级别
	  * 
	  * @param time 1970 到现在的毫秒数
	  * @param format
	  * @return
	  */
	 public static String convertTime2String(long time, String format) {
		 SimpleDateFormat formate = new SimpleDateFormat(TIME_FORMAT);
		 Date dt = new Date(time);
		
		 return  formate.format(dt);
	 }
	 
	 
	 
	 public static long convertDate2Long(String date, String format) {
		 
		 return 0;
	 }
	 
	 /***
	  *  将日期 转化为 字符串 日期级别
	  * @param time
	  * @param format
	  * @return
	  */
	 public static String convertDate2String(long time, String format) {
		 SimpleDateFormat formate = new SimpleDateFormat(DATE_FORMAT);
		 Date dt = new Date(time);
		
		 return  formate.format(dt);
	 }
	 
	 /**
	  * 获取当前系统的日期
	  * 
	  * @return
	  */
	 public static long currentTimeMillis() {
	  return System.currentTimeMillis();
	 }
	 

}
