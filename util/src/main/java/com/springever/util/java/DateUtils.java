package com.springever.util.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.apache.commons.lang.time.DateFormatUtils.format;

/**
 * 日期工具类
 */
public class DateUtils {

    public static final String DATE_FORMATTER = "yyyy-MM-dd";

    public static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.SSS");
    public static final SimpleDateFormat ymd = new SimpleDateFormat(
            "yyyy-MM-dd");

    public static String fillStringWith(String p_str, String p_fill, int p_len) {
        if ((p_fill == null) || (p_fill.equals(""))) {
            return p_str;
        }

        while (p_str.length() < p_len) {
            p_str = p_fill + p_str;
        }
        return p_str;
    }

    /**
     * @purpose dateAdd("y",-1,"2016-12-01"),结果为2015-12-01
     * @param p_ty d表示d天，h表示小时，y表示年，m表示秒
     * @param p_vl 数量
     * @param date 初始时间 yyyy-MM-dd HH:mm:ss yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static String dateAdd(String p_ty, long p_vl, String date)
            throws ParseException {
        int istart = date.indexOf(":");
        DateFormat fmt;
        if (istart > -1) {
            istart = date.indexOf(":", istart + 1);
            if (istart == -1) {
                date = date + ":00";
            }
            fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            fmt = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date d = fmt.parse(date);
        d = dateAdd(p_ty, p_vl, d);
        String v = d.getYear() + "1900";
        String l_ret = v
                + "-"
                + fillStringWith(
                new StringBuilder(String.valueOf(d.getMonth() + 1))
                        .toString(),
                "0", 2)
                + "-"
                + fillStringWith(
                new StringBuilder(String.valueOf(d.getDate()))
                        .toString(),
                "0", 2);
        return l_ret;
    }

    /**
     * @purpose dateAdd("y",-1,"2016-12-01"),结果为2015-12-01
     * @param p_ty d表示d天，h表示小时，y表示年，m表示秒
     * @param p_vl 数量
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date dateAdd(String p_ty, long p_vl,
                               Date date) {
        long l_tm = p_vl + date.getTime();
        Date d = null;
        if (p_ty.equals("d")) {
            p_vl *= 86400000L;
            l_tm += p_vl + 1L;
            d = new Date(l_tm);
        } else if (p_ty.equals("h")) {
            p_vl *= 3600000L;
            l_tm += p_vl + 1L;
            d = new Date(l_tm);
        } else if (p_ty.equals("m")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, Integer.parseInt(p_vl + ""));
            d = cal.getTime();
        } else if (p_ty.equals("y")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.YEAR, Integer.parseInt(p_vl + ""));
            d = cal.getTime();
        }
        return d;
    }

    /**
     * @param p_ty d表示d天，h表示小时，y表示年，m表示秒
     * @param date1 时间 yyyy-MM-dd HH:mm:ss yyyy-MM-dd
     * @param date2 时间 yyyy-MM-dd HH:mm:ss yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static double dateDiff(String p_ty, String date1, String date2)
            throws ParseException {
        int istart = date1.indexOf(":");
        DateFormat fmt;
        if (istart > -1) {
            istart = date1.indexOf(":", istart + 1);
            if (istart == -1) {
                date1 = date1 + ":00";
            }
            fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            fmt = new SimpleDateFormat("yyyy-MM-dd");
        }

        Date d1 = fmt.parse(date1);

        istart = date2.indexOf(":");
        if (istart > -1) {
            istart = date2.indexOf(":", istart + 1);
            if (istart == -1) {
                date2 = date2 + ":00";
            }
            fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            fmt = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date d2 = fmt.parse(date2);
        return dateDiff(p_ty, d1, d2);
    }

    /**
     * @param p_ty d表示d天，h表示小时，y表示年，m表示秒
     * @param date1 时间 yyyy-MM-dd HH:mm:ss yyyy-MM-dd
     * @param date2 时间 yyyy-MM-dd HH:mm:ss yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static double dateDiff(String p_ty, Date date1,
                                  Date date2) {
        double diff = date2.getTime() - date1.getTime();
        double ret = 0.0D;
        if (p_ty.equals("d"))
            ret = diff / 86400000.0D;
        else if (p_ty.equals("h")) {
            ret = diff / 3600000.0D;
        }
        return ret;
    }

    public static String formatDateToStr(Date date, String formatter) {
        if (date == null)
            return "";
        try {
            return new SimpleDateFormat(formatter).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String todayStr(String format) {
        return formatDateToStr(new Date(), format);
    }

    /**
     * @author maowenping 2015-12-12
     * @description 产生时间戳
     * @return
     */
    public static String getDateTime() {
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
                Locale.getDefault());
        String formatDate = format.format(new Date());
        return formatDate;
    }

    /**
     * @author maowenping 2015-12-17
     * @description 格式化日期
     * @param date
     * @return
     */
    public static String getFormatDate(Date date){
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
                Locale.getDefault());
        String formatDate = format.format(date);
        return formatDate;
    }

    /**
     * @author maowenping 2015-12-12
     * @description 得到二个日期间的间隔天数
     * @return
     */
    public static String getTwoDay(String sj1, String sj2) {
        sj1 = sj1.replaceAll("-|\\/", "").trim();
        sj2 = sj2.replaceAll("-|\\/", "").trim();
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * @author maowenping 2015-12-12
     * @description 日期+几天；日期-几天
     * @return
     */
    public static Date getDateFutureOrBefore(String sj1, long day) {
        sj1 = sj1.replaceAll("-|\\/", "").trim();
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
        Date mydate = null;
        try {
            Date date = myFormatter.parse(sj1);
            mydate=new Date((date.getTime()+day*24 * 60 * 60 * 1000));
        } catch (Exception e) {
            return null;
        }
        return mydate;
    }

    /**
     * 計算指定年月的日期數
     *
     * @param year
     * @param month
     * @return
     */
    public static int maxDayOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                boolean isRunYear = (year % 400 == 0)
                        || (year % 4 == 0 && year % 100 != 0);
                return isRunYear ? 29 : 28;
            default:
                return 31;
        }
    }

    /**
     * 获取指定年月的日期數
     *
     * @param year
     * @param month
     * @return
     */
    public static int maxDayOfMonth(String year, String month) {
        return maxDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
    }

    /**
     * 获取指定年月上月月末日期
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastMonthDate(String year, String month) {
        return getLastMonthDate(Integer.parseInt(year), Integer.parseInt(month));
    }

    /**
     * 获取指定年月上月月末日期
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastMonthDate(int year, int month) {
        if (month <= 1) {
            year -= 1;
            month = 12;
        } else {
            month -= 1;
        }
        StringBuffer bfDate = new StringBuffer();
        bfDate.append(year);
        if (month < 10)
            bfDate.append("0");
        bfDate.append(month);
        bfDate.append(maxDayOfMonth(year, month));
        return bfDate.toString();
    }

    /**
     * 提前N天的日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date beforeDate(Date date, int days) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, -days);
        return c.getTime();

    }

    /**
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }

    /**
     * 一周前的日期
     *
     * @return
     */
    public static Date getLastWeek() {
        return getNextDay(-7);
    }

    /**
     * 取相对天数，正数为向后，负数为向前
     *
     * @param day
     * @return
     */
    public static Date getNextDay(int day) {
        Calendar c = new GregorianCalendar();
        c.add(Calendar.DAY_OF_YEAR, day);
        return c.getTime();
    }

    public static int curHour(Calendar cal) {
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int curMinute(Calendar cal) {
        return cal.get(Calendar.MINUTE);
    }

    public static int curSecond(Calendar cal) {
        return cal.get(Calendar.SECOND);
    }

    public static String curTimeStr() {
        Calendar cal = new GregorianCalendar();
        // 分别取得当前日期的年、月、日
        StringBuffer bf = new StringBuffer(10);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour < 10)
            bf.append("0");
        bf.append(hour);
        bf.append(":");
        int minite = cal.get(Calendar.MINUTE);
        if (minite < 10)
            bf.append("0");
        bf.append(minite);
        bf.append(":");
        int second = cal.get(Calendar.SECOND);
        if (second < 10)
            bf.append("0");
        bf.append(second);
        return bf.toString();
    }

    /***************************************************************************
     * @功能 计算当前日期某年的第几周
     * @return interger
     * @throws ParseException
     **************************************************************************/
    public static int getWeekNumOfYear() {
        Calendar calendar = new GregorianCalendar();
        int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
        return iWeekNum;
    }

    /***************************************************************************
     * @功能 计算指定日期某年的第几周
     * @return interger
     * @throws ParseException
     **************************************************************************/
    public static int getWeekNumOfYearDay(String strDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(parseDate(strDate,"yyyy-MM-dd"));
        int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
        return iWeekNum;
    }

    /***************************************************************************
     * @功能 计算某年某周的开始日期
     * @return interger
     * @throws ParseException
     **************************************************************************/
    public static String getWeekFirstDay(int yearNum, int weekNum) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // 分别取得当前日期的年、月、日
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DATE));
        return tempYear + "-" + tempMonth + "-" + tempDay;
    }

    public static String getWeekFirstDay(int weekNum) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.WEEK_OF_YEAR, weekNum);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // 分别取得当前日期的年、月、日
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DATE));
        return cal.get(Calendar.YEAR) + "-" + tempMonth + "-" + tempDay;
    }

    /***************************************************************************
     * @功能 计算某年某周的结束日期
     * @return interger
     * @throws ParseException
     **************************************************************************/
    public static String getWeekEndDay(int yearNum, int weekNum) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, yearNum);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // 分别取得当前日期的年、月、日
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DATE));
        return cal.get(Calendar.YEAR) + "-" + tempMonth + "-" + tempDay;
    }

    public static String getWeekEndDay(int weekNum) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // 分别取得当前日期的年、月、日
        String tempMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
        String tempDay = Integer.toString(cal.get(Calendar.DATE));
        return cal.get(Calendar.YEAR) + "-" + tempMonth + "-" + tempDay;
    }

    public static String getDatePreHours(int preHours) {
        // 当前日期
        Date date = new Date();
        // 格式化对象
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATTER
                + " HH:mm:ss");
        // 日历对象
        Calendar calendar = new GregorianCalendar();
        // 设置当前日期
        calendar.setTime(date);
        // 小时增减
        calendar.add(Calendar.HOUR_OF_DAY, preHours);

        return sdf.format(calendar.getTime());
    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取指定日期的下一周日期
     *
     * @return
     */
    public static String getNextWeekDay(String dateStr, int weekday) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(parseDate(dateStr, "yyyy-MM-dd"));
        int weekNum = cal.get(Calendar.WEEK_OF_YEAR);
        cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
        cal.set(Calendar.DAY_OF_WEEK, weekday + 1);
        return format(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取指定日期的当前一周日期
     *
     * @return
     */
    public static String getCurrWeekDay(String dateStr, int weekday) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(parseDate(dateStr, "yyyy-MM-dd"));
        cal.set(Calendar.DAY_OF_WEEK, weekday + 1);
        return format(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取指定日期的下个月日期
     *
     * @return
     */
    public static String getCurrMonthDay(String dateStr, int day) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(parseDate(dateStr, "yyyy-MM-dd"));
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int endDay = maxDayOfMonth(year, month);
        int valDay = day;
        if (day > endDay) {
            valDay = endDay;
        }
        cal.set(Calendar.DATE, valDay);
        return format(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取指定日期的下个月日期
     *
     * @return
     */
    public static String getNextMonthDay(String dateStr, int day) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(parseDate(dateStr, "yyyy-MM-dd"));
        int month = cal.get(Calendar.MONTH) + 2;
        cal.set(Calendar.MONTH, month - 1);
        int year = cal.get(Calendar.YEAR);
        int endDay = maxDayOfMonth(year, month);
        int valDay = day;
        if (day > endDay) {
            valDay = endDay;
        }
        cal.set(Calendar.DATE, valDay);
        return format(cal.getTime(), "yyyy-MM-dd");
    }

    /**
     * 前几个月日期
     *
     * @param dateStr
     * @param number
     * @return
     */
    public static String beforeMonthDate(String dateStr, int number) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(parseDate(dateStr, DATE_FORMATTER));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        int endDay = maxDayOfMonth(year, month);
        day = Math.min(endDay, day);
        cal.set(Calendar.MONTH, month - number);
        cal.set(Calendar.DATE, day);

        return format(cal.getTime(), DATE_FORMATTER);
    }

    /**
     * 获取指定日期的预约日期 weekday
     *
     * @return
     */
    public static String getPlanWeekDay(String startDate, String endDate,
                                        int day) {
        String dt = getCurrWeekDay(startDate, day);
        if (dt.compareTo(startDate) < 0) {
            dt = getNextWeekDay(startDate, day);
        }
        if (dt.compareTo(endDate) > 0) {
            dt = "";
        }
        return dt;
    }

    /**
     * 获取指定日期的预约日期day
     *
     * @return
     */
    public static String getPlanMonthDay(String startDate, String endDate,
                                         int day) {
        String dt = getCurrMonthDay(startDate, day);
        if (dt.compareTo(startDate) < 0) {
            dt = getNextMonthDay(startDate, day);
        }
        if (dt.compareTo(endDate) > 0) {
            dt = "";
        }
        return dt;
    }

    /**
     * 将字符串yyyyMMdd 格式成 字符串yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String fmtmat(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            Date newDate = df.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(newDate);
        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 将字符串yyyyMMddHHmmss 格式成 字符串yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String fmtmatfulldate(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Date newDate = df.parse(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(newDate);
        } catch (Exception ex) {
            return date;
        }
    }

    /**
     * 通用格式转换
     *
     * @param dateStr
     *            输入日期字符串
     * @return boolean :true,可正常转换；false，不可正常转换或者异常
     */
    public static boolean dateFormat(String dateStr, String formatter) {

        try {
            Date date = DateUtils.parseDate(dateStr,
                    DATE_FORMATTER);
            SimpleDateFormat format = new SimpleDateFormat(formatter);
            return !StringUtils.isSpace(format.format(date));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Date parseDate(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr))
            return null;
        Date date = null;
        try {
            java.text.DateFormat df = new java.text.SimpleDateFormat(format);
            String dt = dateStr;// .replaceAll("-", "/");
            if ((!dt.equals("")) && (dt.length() < format.length())) {
                dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
                        "0");
            }
            date = (Date) df.parse(dt);
        } catch (Exception e) {
        }
        return date;
    }
}
