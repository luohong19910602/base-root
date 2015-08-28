package com.skg.luohong.utils.date;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 日期工具类
 * @author 骆宏
 * 
 * */
public class DateUtils{
	private static transient int gregorianCutoverYear = 1582;
	/** 闰年中每月天数 */
	private static final int[] DAYS_P_MONTH_LY= {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	/** 非闰年中每月天数 */
	private static final int[] DAYS_P_MONTH_CY= {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	/** 代表数组里的年、月、日 */
	private static final int Y = 0, M = 1, D = 2,H=3,m=4,s=5;



	private final static SimpleDateFormat dateLineFormatter;
	/** yyyy-MM-dd */
	public final static String dateLinePattern = "yyyy-MM-dd";

	private final static SimpleDateFormat dateTimeFormatter;
	/** yyyy-MM-dd HH:mm:ss */
	private final static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

	private final static SimpleDateFormat timeLineFormatter;
	
	/** HH-mm */
	private final static String timeLinePattern = "HH-mm";
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
	
	static {
		dateTimeFormatter = new SimpleDateFormat(dateTimePattern);
		dateLineFormatter = new SimpleDateFormat(dateLinePattern);
		timeLineFormatter = new SimpleDateFormat(timeLinePattern);
	}

	public static final String now() {
		return dateTimeFormatter.format(new Date());
	}

	public static final String nowDateLine() {
		return dateLineFormatter.format(new Date());
	}

	public static final String nowTimeLine() {
		return timeLineFormatter.format(new Date());
	}


	public static final String toGMT(Date date) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"EEE, dd-MMM-yyyy HH:mm:ss zzz");
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			format.setCalendar(cal);
			return format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 日期转成字符串
	 *
	 * @param aDate
	 * @return
	 * @author 颜超敏
	 */
	public static final String toString(Date aDate) {
		return dateTimeFormatter.format(aDate);
	}

	public static final String toString(Date aDate, String pattern) {
		if ((pattern == null) || (aDate == null)) {
			return "";
		}
		SimpleDateFormat df = null;
		String returnValue = "";
		df = new SimpleDateFormat(pattern);
		returnValue = df.format(aDate);

		return (returnValue);
	}

	public static final String toString(long time) {
		return dateTimeFormatter.format(new Date(time));
	}

	public static final String toWeekStr(int week) {
		String[] dayOfWeek = {"", "日", "一", "二", "三", "四", "五", "六"}; 
		return dayOfWeek[week]; 
	}
	
	/**
	 * 将代表日期的字符串分割为代表年月日的整形数组
	 * @param date yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static int[] splitYMDhms(String date){
		date = date.replace("-", "");
		int[] splitYMDhms = {0, 0, 0, 0, 0, 0};
		splitYMDhms[Y] = Integer.parseInt(date.substring(0, 4));
		splitYMDhms[M] = Integer.parseInt(date.substring(4, 6));
		splitYMDhms[D] = Integer.parseInt(date.substring(6, 8));
		try
		{
			String[] Hms=date.split(" ")[1].split(":");
			splitYMDhms[H]=Integer.parseInt(Hms[0]);
			splitYMDhms[m]=Integer.parseInt(Hms[1]);
			splitYMDhms[s]=Integer.parseInt(Hms[2]);
		} catch (Exception e)
		{
			System.err.println("无时分秒");
			splitYMDhms[H]=0;
			splitYMDhms[m]=0;
			splitYMDhms[s]=0;
		}
		return splitYMDhms;
	}
	/**
	 * 检查传入的参数代表的年份是否为闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return year >= gregorianCutoverYear ?
				((year%4 == 0) && ((year%100 != 0) || (year%400 == 0))) : (year%4 == 0); 
	}    
	/**
	 * 日期加1天
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private static int[] addOneDay(int year, int month, int day){
		if(isLeapYear( year )){
			day++;
			if( day > DAYS_P_MONTH_LY[month -1 ] ){
				month++;
				if(month > 12){
					year++;
					month = 1;
				}
				day = 1;
			}
		}else{
			day++;
			if( day > DAYS_P_MONTH_CY[month -1 ] ){
				month++;
				if(month > 12){
					year++;
					month = 1;
				}
				day = 1;
			}
		}
		int[] ymd = {year, month, day};
		return ymd;
	}
	private static int[] add1Day(int[] YMDhms)
	{
		int year=YMDhms[Y],month=YMDhms[M],day=YMDhms[D];
		if(isLeapYear( year )){
			day++;
			if( day > DAYS_P_MONTH_LY[month -1 ] ){
				month++;
				if(month > 12){
					year++;
					month = 1;
				}
				day = 1;
			}
		}else{
			day++;
			if( day > DAYS_P_MONTH_CY[month -1 ] ){
				month++;
				if(month > 12){
					year++;
					month = 1;
				}
				day = 1;
			}
		}
		YMDhms[Y]=year;
		YMDhms[M]=month;
		YMDhms[D]=day;
		return YMDhms;

	}

	/**
	 * 将不足两位的月份或日期补足为两位
	 * @param decimal
	 * @return
	 */
	 public static String formatMonthDay(int decimal){
		DecimalFormat df = new DecimalFormat("00");
		return df.format( decimal );
	}
	public static String format2Length(int decimal){
		DecimalFormat df = new DecimalFormat("00");
		return df.format( decimal );
	}

	/**
	 * 将不足四位的年份补足为四位
	 * @param decimal
	 * @return
	 */
	public static String formatYear(int decimal){
		DecimalFormat df = new DecimalFormat("0000");
		return df.format( decimal );
	}

	/**
	 * 计算两个日期之间相隔的天数
	 * @param begin
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public static long countDay(String begin,String end){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate , endDate;
		long day = 0;
		try {
			beginDate= format.parse(begin);
			endDate=  format.parse(end);
			day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);  
		} catch (ParseException e) {
			e.printStackTrace();
		}                
		return day;
	}
	public static List<String> getEveryDay(Date beginDate , Date endDate)
	{
		return getEveryDay(toString(beginDate),toString(endDate));
	}
	/**
	 * 以循环的方式计算日期
	 * @param beginDate endDate
	 * @param days
	 * @return
	 */
	public static List<String> getEveryDay(String beginDate , String endDate){
		long days = countDay(beginDate, endDate);
		int[] ymd = splitYMDhms( beginDate );
		List<String> everyDays = new ArrayList<String>();
		everyDays.add(beginDate.split(" ")[0]);
		for(int i = 0; i < days; i++){
			ymd = addOneDay(ymd[Y], ymd[M], ymd[D]);
			everyDays.add(formatYear(ymd[Y])+"-"+formatMonthDay(ymd[M])+"-"+formatMonthDay(ymd[D]));
		}
		return everyDays;
	}


	public static List<String> getEveryHour(Date beginDate , Date endDate)
	{
		return getEveryHour(toString(beginDate),toString(endDate));
	}

	public static List<String> getEveryHour(String beginDate , String endDate){
		long days = countDay(beginDate, endDate);
		int[] ymdhms = splitYMDhms( beginDate );
		int[] ymdhmsEnd = splitYMDhms( endDate );
		List<String> everyHours = new ArrayList<String>();
		everyHours.add(formatYear(ymdhms[Y])+"-"+formatMonthDay(ymdhms[M])+"-"+formatMonthDay(ymdhms[D])+" "+format2Length(ymdhms[H])+":00:00");
		for(int i = 0; i < days; i++){
			ymdhms = add1Day(ymdhms);//每一天
			String everyHour="";

			//everyHours.add(formatYear(ymdhms[Y])+"-"+formatMonthDay(ymdhms[M])+"-"+formatMonthDay(ymdhms[D]));
			//24小时制
			if(i!=days-1)
			{
				for (int j=ymdhms[H];j<24;j++)
				{
					everyHour =formatYear(ymdhms[Y])+"-"+formatMonthDay(ymdhms[M])+"-"+formatMonthDay(ymdhms[D])+" "+format2Length(j)+":00:00";
					everyHours.add(everyHour);
					//1 9时~2 10时
					//1的时候 >9<24
					//2>0<10

				}
			}
			else 
			{
				for (int j=0;j<=ymdhmsEnd[H];j++)
				{
					//1 9时~2 10时
					//1的时候 >9<24
					//2>0<10
					everyHour =formatYear(ymdhms[Y])+"-"+formatMonthDay(ymdhms[M])+"-"+formatMonthDay(ymdhms[D])+" "+format2Length(j)+":00:00";
					everyHours.add(everyHour);
				}
			}

		}
		return everyHours;
	}
	public static List<String> getMonth(String beginDate , String endDate){
		long days = countDay(beginDate, endDate);
		int[] ymdhms = splitYMDhms( beginDate );
		int[] ymdhmsEnd = splitYMDhms( endDate );
		List<String> everyDays = new ArrayList<String>();
		everyDays.add(formatYear(ymdhms[Y])+"-"+formatMonthDay(ymdhms[M])+"-"+formatMonthDay(ymdhms[D])+" "+format2Length(ymdhms[H])+":00:00");
		for(int i = 0; i < days; i++){
			ymdhms = add1Day(ymdhms);//每一天
			String everyHour="";

			//everyDays.add(formatYear(ymdhms[Y])+"-"+formatMonthDay(ymdhms[M])+"-"+formatMonthDay(ymdhms[D]));
			//24小时制
			if(i!=days-1)
			{
				for (int j=ymdhms[H];j<24;j++)
				{
					everyHour =formatYear(ymdhms[Y])+"-"+formatMonthDay(ymdhms[M])+"-"+formatMonthDay(ymdhms[D])+" "+format2Length(j)+":00:00";
					everyDays.add(everyHour);
					//1 9时~2 10时
					//1的时候 >9<24
					//2>0<10

				}
			}
			else 
			{
				for (int j=0;j<=ymdhmsEnd[H];j++)
				{
					//1 9时~2 10时
					//1的时候 >9<24
					//2>0<10
					everyHour =formatYear(ymdhms[Y])+"-"+formatMonthDay(ymdhms[M])+"-"+formatMonthDay(ymdhms[D])+" "+format2Length(j)+":00:00";
					everyDays.add(everyHour);
				}
			}

		}
		return everyDays;
	}
	
	//加一个月
	public static Date addMonth(Date date,int n) {  
		Calendar rightNow = Calendar.getInstance();  
		rightNow.setTime(date);  

		rightNow.add(Calendar.MONTH,n);  
		return rightNow.getTime();  

	} 
	public static void main(String[] args) {
		//		System.out.println(toWeekStr(7));
		//		System.err.println(parseDate("2015/12/12 55:23"));
		//		System.err.println(parseDate("xxx20315-12-12 55:233:23"));
		List<String> list = DateUtils.getEveryDay("2014-08-29", "2015-09-02");
		for (String result : list) {
			System.out.println(result);
		}
	}

}
