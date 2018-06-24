package com.springever.util.java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 */
public class DateUtils {

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
            cal.add(2, Integer.parseInt(p_vl + ""));
            d = cal.getTime();
        } else if (p_ty.equals("y")) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(1, Integer.parseInt(p_vl + ""));
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
}
