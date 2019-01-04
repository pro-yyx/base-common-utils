package com.yyx.utils;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 时间工具类 (joda-time)
 * @Auther: yinyuxin
 * @Date: 2018/4/25 17:15
 */
public class DateUtily {

    private static final Logger LOGGER=LoggerFactory.getLogger(DateUtily.class);

    /**
     * 默认输出格式 年月日 时分秒
     */
    public static final String FORMAT_DEFAULT="yyyy-MM-dd HH:mm:ss";

    /**
     * 输出格式 年月日
     */
    public static final String FROMAT_DAtE="yyyy-MM-dd";

    /**
     * 输出格式 年月
     */
    public static final String FORMAT_DATE_WITHOUT_DAY="yyyy-MM";

    /**
     * 线程安全的转换工具
     */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    private static final Object LOCKOBJ = new Object();


    private static SimpleDateFormat getSdf(final String pattern) {

        ThreadLocal<SimpleDateFormat> ts = sdfMap.get(pattern);

        if (ts == null) {
            synchronized (LOCKOBJ) {
                ts = sdfMap.get(pattern);
                if (ts == null) {
                    ts = ThreadLocal.withInitial(() -> new SimpleDateFormat(pattern));
                    sdfMap.put(pattern, ts);
                }
            }
        }
        return ts.get();
    }

    /**
     * 获取当前系统时间对象
     * @Auther: yinyuxin@gome.com.cn
     * @return
     */
    public static  Date getCurrentDate(){
        DateTime dateTime=new DateTime();
        return dateTime.toDate();
    }

    /**
     * 按指定格式获取当前系统时间
     * @Auther: yinyuxin@gome.com.cn
     * @param pattern
     * @return
     */
    public static String getCurrentDate(String pattern){
        try {
            DateTime dateTime=new DateTime();
            return dateTime.toString(pattern==null?FORMAT_DEFAULT:pattern);
        } catch (Exception e) {
           LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
           return null;
        }
    }

    /**
     * 按指定格式转换日期
     * @Auther: yinyuxin@gome.com.cn
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(Date date,String pattern){
        try {
            if (Objects.isNull(date)){
                return null;
            }
            if (null==pattern||"".equals(pattern)){
                pattern=FORMAT_DEFAULT;
            }
            return getSdf(pattern).format(date);
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }

    /**
     * 将日期转为指定格式字符串
     * @Auther: yinyuxin@gome.com.cn
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date string2Date(String dateString,String pattern){
        try {
            if (null==dateString||"".equals(dateString)){
                return null;
            }
            if (null==pattern||"".equals(pattern)){
                pattern=FORMAT_DEFAULT;
            }
            return getSdf(pattern).parse(dateString);
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }

    /** 10位或者13位时间戳转成Date
     * @Auther: yinyuxin@gome.com.cn
     * @param dateLong
     * @param pattern
     * @return
     */
    public static Date long2Date(Long dateLong,String pattern){
        try {
            if (null==dateLong){
                return null;
            }
            String dateString=dateLong+"";
            while (dateString.length()<13){
                dateString += "0";
            }
            dateLong=Long.valueOf(dateString);
            Date date = new Date(dateLong);
            String dateString2= getSdf(pattern).format(date);
            return getSdf(pattern).parse(dateString2);
        } catch (Exception e) {
            LOGGER.error("工具类执行失败:{}",ExceptionUtils.getFullStackTrace(e));
            return null;
        }
    }

    /**
     * 计算两个时间相差多少天
     * @Auther: yinyuxin@gome.com.cn
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer diffDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        DateTime dt1 = new DateTime(startDate);
        DateTime dt2 = new DateTime(endDate);
        int day = Days.daysBetween(dt1, dt2).getDays();
        return Math.abs(day);
    }

    /**
     * 日期计算
     * @param date 当前时间对象
     * @param days 计算的数目 正数加  负数减
     * @param dayType 类型  0：天  1：月  2:年
     * @return
     */
    public static Date timeCalculation(Date date,Integer days,Integer dayType){
        DateTime dateTime=new DateTime(date);
        DateTime resultTime=new DateTime();
        switch (dayType){
            case 0:
                resultTime=dateTime.plusDays(days);
                break;
            case 1:
                resultTime=dateTime.plusMonths(days);
                break;
            case 2:
                resultTime=dateTime.plusYears(days);
                break;
             default:
                 break;
        }
        return resultTime.toDate();
    }

}
