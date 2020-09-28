package com.fei.movieticket.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateTimeUtil {

	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static String initCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 根据日期样式获取时间字符串
	 */
	public static String getDateTime(String type) {
		type = type == null || type.equals("") ? "yyyy-MM-dd HH:mm:ss" : type;
		return new SimpleDateFormat(type).format(new Date());
	}

	/**
	 * 根据日期样式获取时间字符串
	 * create by guxiaowei
	 */
	public static String getDateTime(int day, String type) {
		type = type == null || type.equals("") ? "yyyy-MM-dd HH:mm:ss" : type;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		Date time = cal.getTime();
		return new SimpleDateFormat(type).format(time);
	}

	/**
	 * 直接返回带毫秒的时间字符串
	 * 
	 * @return
	 */
	public static String getDateTimeSSS() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")
				.format(new Date());
	}

	/**
	 * 直接返回带时分时间字符串
	 * 
	 * @return
	 */
	public static String getDateTimeHHMM() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
	}

	/**
	 * 直接返回时间字符串
	 * 
	 * @return
	 */
	public static String getDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static String getWeekDay(String day) {
		try {
			String dw = "";
			Calendar c = Calendar.getInstance();
			c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(day));
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			switch (dayOfWeek) {
			case 1:
				dw = "日";
				break;
			case 2:
				dw = "一";
				break;
			case 3:
				dw = "二";
				break;
			case 4:
				dw = "三";
				break;
			case 5:
				dw = "四";
				break;
			case 6:
				dw = "五";
				break;
			case 7:
				dw = "六";
				break;
			}
			return dw;
		} catch (ParseException e) {
			return "";
		}
	}

	/**
	 * 根据正负天数返回之前的或之后的时间:yyyy-MM-dd
	 */
	public static String getFrontOrAfterDate(int days) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, days);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(now.getTime());
	}

	/**
	 * 根据正负天数返回之前的或之后的时间:yyyy-MM-dd HH
	 */
	public static String getFrontOrAfterHour(int hour) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR, hour);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH");
		return sf.format(now.getTime());
	}

	/**
	 * 根据正负分钟返回之前的或之后的时间:yyyy-MM-dd HH:mm
	 */
	public static String getFrontOrAfterMin(int mins) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, mins);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sf.format(now.getTime());
	}

	/**
	 * 根据正负分钟返回之前的或之后的时间:yyyy-MM-dd HH:mm
	 */
	public static String getFrontOrAfterMin(String time, int mins) {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.YEAR, 2018);
		now.set(Calendar.MONTH, 1);
		now.set(Calendar.DAY_OF_MONTH, 22);
		now.set(Calendar.HOUR_OF_DAY, Integer.valueOf(time.split(":")[0]));
		now.set(Calendar.MINUTE, Integer.valueOf(time.split(":")[1]));
		now.set(Calendar.SECOND, 0);
		now.add(Calendar.MINUTE, mins);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sf.format(now.getTime());
	}

	/**
	 * 计算时间之间的时长
	 * 
	 * @param startTime
	 * @param endTime
	 * @return *秒
	 */
	public static long countSecondTimes(String startTime, String endTime) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fromTime = formatter.parse(startTime);
		Date toTime = formatter.parse(endTime);
		return (toTime.getTime() - fromTime.getTime()) / 1000;

	}

	/**
	 * 计算时间之间的时长
	 * 
	 * @param startTime
	 * @param endTime
	 * @return *天*小时*分钟*秒
	 */
	public static String countTimes(String startTime, String endTime) throws Exception{
		String timeStr;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fromTime = formatter.parse(startTime);
		Date toTime = formatter.parse(endTime);
		long ms = (toTime.getTime() - fromTime.getTime()) / 1000;
		long day = ms / 60 / 60 / 24;
		long hour = ms / 60 / 60 % 24;
		long min = ms / 60 % 60;
		long sec = ms % 60;
		timeStr = day == 0 ? "" : day + "天";
		timeStr += hour == 0 ? "" : hour + "小时";
		timeStr += min == 0 ? "" : min + "分";
		timeStr += sec == 0 ? "" : sec + "秒";
		timeStr = timeStr.equals("") ? "0秒" : timeStr;

		return timeStr;
	}

	/**
	 * 计算两个日期之间间隔天数
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return 间隔天数
	 * @throws Exception
	 */
	public static int getDaysBetween(String date1, String date2) {
		try {
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(convertStringToDate(date1));
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(convertStringToDate(date2));
			if (cal1.after(cal2)) {
				Calendar swap = cal1;
				cal1 = cal2;
				cal2 = swap;
			}
			int days = cal2.get(Calendar.DAY_OF_YEAR)
					- cal1.get(Calendar.DAY_OF_YEAR);
			int year = cal2.get(Calendar.YEAR);
			if (cal1.get(Calendar.YEAR) != year) {
				cal1 = (Calendar) cal1.clone();
				do {
					days = cal1.getActualMaximum(Calendar.DAY_OF_YEAR);
					cal1.add(Calendar.YEAR, 1);
				} while (cal1.get(Calendar.YEAR) != year);
			}
			return days + 1;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 转换字符串到日期
	 * 
	 * @param str
	 *            字符串yyyy-MM-dd
	 * @return 日期Date
	 * @throws Exception
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String str) {
		Date date = null;
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.parse(str);
			return date;
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 根据指定日期查询前几天
	 * 
	 * @param dateString
	 *            指定日期 (yyyy-MM-dd)
	 * @param beforeDays
	 *            天数(前几天)
	 * @return
	 * @throws ParseException
	 * @author ljc
	 */
	public static String getBeforeDate(String dateString, int beforeDays){
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date inputDate = dateFormat.parse(dateString);
	
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputDate);
	
			int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear - beforeDays);
			return dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据指定日期查询后几天
	 * 
	 * @param dateString
	 *            指定日期 (yyyy-MM-dd)
	 * @param afterDays
	 *            天数(后几天)
	 * @return
	 * @throws ParseException
	 * @author ssq
	 */
	public static String getAfterDate(String dateString, int afterDays) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date inputDate = dateFormat.parse(dateString);

			Calendar cal = Calendar.getInstance();
			cal.setTime(inputDate);

			int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			cal.set(Calendar.DAY_OF_YEAR, inputDayOfYear + afterDays);
			return dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 比较两个时间的大小：时间1是否大于时间2
	 * 
	 * @param time1
	 *            格式 12:12
	 * @param time2
	 *            格式 13:13
	 * @return
	 */
	public static boolean checkTimeOneIsGreaterThanTimeTwo(String time1,
                                                           String time2) {
		String[] time1Hm = time1.split(":");
		String[] time2Hm = time2.split(":");
		if (Integer.valueOf(time1Hm[0]) > Integer.valueOf(time2Hm[0])) {
			return true;
		} else if (Integer.valueOf(time1Hm[0]) < Integer.valueOf(time2Hm[0])) {
			return false;
		} else {
			return Integer.valueOf(time1Hm[1]) > Integer.valueOf(time2Hm[1]);
		}
	}

	/**
	 * 返回两个时间之间的分钟数
	 * d
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static long countMinTimes(String startTime, String endTime) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fromTime = formatter.parse(startTime);
		Date toTime = formatter.parse(endTime);
		long ms = (toTime.getTime() - fromTime.getTime()) / 1000;
		return ms / 60;
	}
	/**
	 * 根据传入的日期，得到月份的最后一天
	 * @param date
	 * @return
	 */
	public static String getMonthLastDay(String date){
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date inputDate = dateFormat.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputDate);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			return dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据给定日期，获取其所在月份的第一天
	 * @param date
	 * @return
	 */
	public static String getFirstDayOfMonth(String date){
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date inputDate = dateFormat.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(inputDate);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			return dateFormat.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断给定日期，是否为该月第一天
	 * @param date
	 * @return
	 */
	public static boolean isFirstDayofMonth(String date)throws Exception{
		if(date!=null && !"".equals(date)){
			boolean flag = false;
			Calendar cal = Calendar.getInstance();
			int today = cal.get(Calendar.DAY_OF_MONTH);
			if(1==today){
				flag = true;
			}
			return flag;
		}else{
			throw new Exception("时间参数异常");
		}

	}

	/**
	 * 根据给定日期，获取上一个月第一天和最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Map<String,Object> getLastMonthTimeFrame(String date){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateFormat.parse(date));
			//获取上一个月份
			cal.add(Calendar.MONTH,-1);
			//获取上一个月第一天和最后一天
			cal.set(Calendar.DAY_OF_MONTH,1);
			String firstDayOfLastMont = dateFormat.format(cal.getTime());
			cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			String lastDayOfLastMont = dateFormat.format(cal.getTime());
			map.put("firstDay",firstDayOfLastMont);
			map.put("lastDay",lastDayOfLastMont);
			return map;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据当前月份获取之前或之后月份
	 * @Author gaozhen
	 * @Date: 2019/4/2
	 * @param date 当前月份
	 * @param i 如-1前一个月，1后一个月
	 */
	public static String getLastMonth(String date, int i) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		dateFormat.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.add(Calendar.MONTH,i);
		String format = dateFormat.format(calendar.getTime());
		return format;
	}
	
	/**
	 * 根据日期获取前一个月或后一个月的日期
	 * @Author gaozhen
	 * @Date: 2019/4/2
	 * @param date 当前日期（yyyy-MM-dd） 
	 * @param i 如-1前一个月，1后一个月
	 */
	public static String getLastMonthDay(String date, int i) throws Exception {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.add(Calendar.MONTH,i);
		String format = dateFormat.format(calendar.getTime());
		return format;
	}

	/**
	 * 比较两个日期的大小格式："yyyy-MM-dd"
	 * @Author gaozhen
	 * @Date: 2019/5/9
	 * @param date1
	 * @param date2
	 */
	public static boolean checkDateOneIsGreaterThanTwo(String date1, String date2){
		String[] split1 = date1.split("-");
		String[] split2 = date2.split("-");
		String str1=split1[0]+split1[1]+split1[2];
		String str2=split2[0]+split2[1]+split2[2];
		if (Integer.valueOf(str1) > Integer.valueOf(str2)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 格式化出入库时间为“yyyy-MM-dd HH:mm:ss”
	 * @Author gaozhen
	 * @Date: 2019/5/22
	 * @param oldDate
	 */
	public static String formatStockDate(String oldDate){
		if(oldDate.length()>=19){
			return oldDate.substring(0,19);
		}else if(oldDate.length() == 16){
			return oldDate+":00";
		}else if(oldDate.length() == 10){
			return oldDate+" 00:00:00";
		}else{
			return null;
		}
	}

	/**
	 * 取得两个日期之间的日期列表
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> getDateIntervalList(Date startDate, Date endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> dateList = new ArrayList<String>();

		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(startDate);

		// 测试此日期是否在指定日期之后
		while (endDate.compareTo(calBegin.getTime()) >= 0) {
			dateList.add(sdf.format(calBegin.getTime()));
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dateList;
	}
}
