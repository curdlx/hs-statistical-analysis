package cn.com.homestar.as.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {
    /**
     * 取得某一天的下周周日的00:00
     * @param date
     * @return
     */
    public static Date getNextSunDayBegingOfOneDay(Date date){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.setTime(date);
        currentDate.add(Calendar.DAY_OF_YEAR,7);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);  
        currentDate.set(Calendar.MINUTE, 0);  
        currentDate.set(Calendar.SECOND, 0);  
        return (Date)currentDate.getTime().clone();  
    }
    /**
     * 取得某一天的上周周日的00:00
     * @param date
     * @return
     */
    public static Date getLastSunDayBegingOfOneDay(Date date){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.setTime(date);
        currentDate.add(Calendar.DAY_OF_YEAR,-7);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);  
        currentDate.set(Calendar.MINUTE, 0);  
        currentDate.set(Calendar.SECOND, 0);  
        return (Date)currentDate.getTime().clone();  
    }

    /**
     * 获取两个时间之间的天差,date1-date2
     * @param date1
     * @param date2
     * @return
     */
    public static int getDayDiff(Date date1,Date date2){
        date1 = getTheBiginingOfOneDay(date1);
        date2 = getTheBiginingOfOneDay(date2);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        int day1 = c1.get(Calendar.DAY_OF_YEAR);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int day2 = c2.get(Calendar.DAY_OF_YEAR);
        if(year1-year2==1){
            int daysOfYear2 = getdaysOfYear(year2);
            return (daysOfYear2-day2)+day1;
        }
        //TODO 这里存在bug
        return day1-day2;
    }
    public static int getdaysOfYear(int year){ 
        int days = 365; 
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) { 
         days = 366; 
         } 
        return days; 
     }
    /**
     * 判断当前日期是星期几
     * 
     * @param date
     *            修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static int getDayForWeek(Date date) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }
    public static Date getTheBiginingOfOneDay(Date date){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);  
        currentDate.set(Calendar.MINUTE, 0);  
        currentDate.set(Calendar.SECOND, 0);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheEndingOfOneDay(Date date){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);  
        currentDate.set(Calendar.MINUTE, 59);  
        currentDate.set(Calendar.SECOND, 59);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheBiginingOfYesterday(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);  
        currentDate.set(Calendar.MINUTE, 0);  
        currentDate.set(Calendar.SECOND, 0);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheEndingOfYesterday(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -1);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);  
        currentDate.set(Calendar.MINUTE, 59);  
        currentDate.set(Calendar.SECOND, 59);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheBiginingOfToday(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);  
        currentDate.set(Calendar.MINUTE, 0);  
        currentDate.set(Calendar.SECOND, 0);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheEndingOfToday(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 23);  
        currentDate.set(Calendar.MINUTE, 59);  
        currentDate.set(Calendar.SECOND, 59);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheBiginingOfOneDaysWeek(Date date){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
        currentDate.set(Calendar.HOUR_OF_DAY, 0);  
        currentDate.set(Calendar.MINUTE, 0);  
        currentDate.set(Calendar.SECOND, 0);  
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheEndingOfOneDaysWeek(Date date){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
        currentDate.set(Calendar.HOUR_OF_DAY, 23);  
        currentDate.set(Calendar.MINUTE, 59);  
        currentDate.set(Calendar.SECOND, 59);  
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheBiginingOfThisWeek(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
        currentDate.set(Calendar.HOUR_OF_DAY, 0);  
        currentDate.set(Calendar.MINUTE, 0);  
        currentDate.set(Calendar.SECOND, 0);  
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheEndingOfThisWeek(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
        currentDate.set(Calendar.HOUR_OF_DAY, 23);  
        currentDate.set(Calendar.MINUTE, 59);  
        currentDate.set(Calendar.SECOND, 59);  
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheBiginingOfLastWeek(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
        currentDate.set(Calendar.HOUR_OF_DAY, 0);  
        currentDate.set(Calendar.MINUTE, 0);  
        currentDate.set(Calendar.SECOND, 0);  
        currentDate.add(Calendar.WEEK_OF_YEAR, -1);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheEndingOfLastWeek(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
        currentDate.set(Calendar.HOUR_OF_DAY, 23);  
        currentDate.set(Calendar.MINUTE, 59);  
        currentDate.set(Calendar.SECOND, 59);  
        currentDate.add(Calendar.WEEK_OF_YEAR, -1);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheBiginingOfLastMonth(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
        currentDate.set(Calendar.HOUR_OF_DAY, 0);  
        currentDate.set(Calendar.MINUTE, 0);  
        currentDate.set(Calendar.SECOND, 0);  
        currentDate.add(Calendar.MONTH, -1);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
        return (Date)currentDate.getTime().clone();  
    }
    public static Date getTheEndingOfLastMonth(){
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);  
        currentDate.set(Calendar.HOUR_OF_DAY, 23);  
        currentDate.set(Calendar.MINUTE, 59);  
        currentDate.set(Calendar.SECOND, 59);  
        currentDate.add(Calendar.MONTH, -1);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
        return (Date)currentDate.getTime().clone();  
    }
//    public static List<StartEndDatePair> partitionDate(Date startDate,Date endDate){
//        List<StartEndDatePair> pairList = new ArrayList<StartEndDatePair>();
//        try {
//
//            //结束时间离开始时间差几天
//            startDate = DateUtil.getTheBiginingOfOneDay(startDate);
//            endDate = DateUtil.getTheBiginingOfOneDay(endDate);
//            int diff = DateUtil.getDayDiff(endDate, startDate);
//            if(diff<6){
//                pairList.add(new StartEndDatePair(StartEndDatePairType.DAY,startDate, endDate));
//                return pairList;
//            }else{
//                //开始时间的当周周一
//                Date monDayOfStartDate = DateUtil.getTheBiginingOfOneDaysWeek(startDate);
//                //开始时间的当周周日
//                Date sunDayOfStartDate = DateUtil.getTheBiginingOfOneDay(DateUtil.getTheEndingOfOneDaysWeek(startDate));
//                //结束时间的当周周一
//                Date monDayOfEndDate = DateUtil.getTheBiginingOfOneDaysWeek(endDate);
//                //结束时间的当周周日
//                Date sunDayOfEndDate = DateUtil.getTheBiginingOfOneDay(DateUtil.getTheEndingOfOneDaysWeek(endDate));
//                //开始时间到"开始时间当周周一"的差值
//                int deffOfTheMonDayOfStartDateAndStartDate = DateUtil.getDayDiff(startDate, monDayOfStartDate);
//                //结束时间到"结束时间当周周日"的差值
//                int deffOfEndDateAndTheSunDayOfEndDate = DateUtil.getDayDiff(sunDayOfEndDate, endDate);
//                //开始时间的下周日
//                Date nextWeekSunDayOfStarDate = DateUtil.getNextSunDayBegingOfOneDay(startDate);
//                //结束时间的上周日
//                Date lastWeekSunDayOfEndDate = DateUtil.getLastSunDayBegingOfOneDay(endDate);
//                Date begin = (Date)nextWeekSunDayOfStarDate.clone();
//                Date end = (Date)lastWeekSunDayOfEndDate.clone();
//                //游标
//                Date vernier = (Date)begin.clone();
//                if(vernier.compareTo(end)<=0){
//                    StartEndDatePair weekPair = new StartEndDatePair(StartEndDatePairType.WEEK,begin, vernier);
//                    while (vernier.compareTo(end)<=0) {
//                        weekPair = new StartEndDatePair(StartEndDatePairType.WEEK,begin, vernier);
//                        vernier = DateUtil.getNextSunDayBegingOfOneDay(vernier);
//                    }
//                    pairList.add(weekPair);
//                }
//                if(deffOfTheMonDayOfStartDateAndStartDate==0&&deffOfEndDateAndTheSunDayOfEndDate==0){
//                    //开始时间的当周周日
//                    pairList.add(new StartEndDatePair(StartEndDatePairType.WEEK,sunDayOfStartDate, sunDayOfStartDate));
//                    if(sunDayOfEndDate.compareTo(sunDayOfStartDate)!=0){
//                        pairList.add(new StartEndDatePair(StartEndDatePairType.WEEK,sunDayOfEndDate, sunDayOfEndDate));
//                    }
//                }else if(deffOfTheMonDayOfStartDateAndStartDate!=0&&deffOfEndDateAndTheSunDayOfEndDate==0){
//                    //开始时间当天到开始时间的当周周日
//                    pairList.add(new StartEndDatePair(StartEndDatePairType.DAY,startDate, sunDayOfStartDate));
//                    pairList.add(new StartEndDatePair(StartEndDatePairType.WEEK,sunDayOfEndDate, sunDayOfEndDate));
//                }else if(deffOfTheMonDayOfStartDateAndStartDate==0&&deffOfEndDateAndTheSunDayOfEndDate!=0) {
//                    pairList.add(new StartEndDatePair(StartEndDatePairType.WEEK,sunDayOfStartDate, sunDayOfStartDate));
//                    //结束时间当周周一到结束时间当天
//                    pairList.add(new StartEndDatePair(StartEndDatePairType.DAY,monDayOfEndDate, endDate));
//                }else {
//                    //开始时间当天到开始时间的当周周日
//                    pairList.add(new StartEndDatePair(StartEndDatePairType.DAY,startDate, sunDayOfStartDate));
//                    //结束时间当周周一到结束时间当天
//                    pairList.add(new StartEndDatePair(StartEndDatePairType.DAY,monDayOfEndDate, endDate));
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return pairList;
//    }
    public static List<Date> getConsecutiveDateByDay(Date startDate,Date endDate){
        Date point =(Date) startDate.clone();
        List<Date> list = new ArrayList<Date>();
        while (point.compareTo(endDate)<=0) {
            list.add(point);
            Calendar c = Calendar.getInstance();
            c.setTime(point);
            c.add(Calendar.DAY_OF_YEAR, 1);
            point = c.getTime();
        }
        return list;
        
    }
    public static void main(String args[]) throws ParseException{
//        Calendar cal = Calendar.getInstance();
//        //n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
//        int n = 1;
//        String monday;
//        cal.add(Calendar.DATE, n*7);
//        //想周几，这里就传几Calendar.MONDAY（TUESDAY...）
//        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
//        monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        List<Date> list = getConsecutiveDateByDay(sdf.parse("2014-11-01"), sdf.parse("2014-12-29"));
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        System.out.println(DateUtil.getTheBiginingOfToday());
    }
}
