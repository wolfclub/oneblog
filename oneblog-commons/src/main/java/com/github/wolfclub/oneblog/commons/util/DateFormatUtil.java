package com.github.wolfclub.oneblog.commons.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author liuchan
 * @date 11:00 2018/2/7
 */
public class DateFormatUtil {
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String[] DEFAULT_FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy.dd HH:mm:ss",
            "yyyy.MM.dd'T'HH:mm:ddZ", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"};
    public static final ThreadLocal<Map<String, SimpleDateFormat>> sdfs = new ThreadLocal<Map<String, SimpleDateFormat>>(){
        @Override
        protected Map<String, SimpleDateFormat> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     *
     * @param pattern
     * @param timeZone
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String pattern, TimeZone timeZone){
        pattern = pattern == null? DEFAULT_DATETIME_FORMAT: pattern;
        timeZone = timeZone == null? TimeZone.getDefault(): timeZone;


        Map<String, SimpleDateFormat> sdfMap = sdfs.get();
        SimpleDateFormat sdf = sdfMap.get(pattern);
        if (sdf == null) {
            sdf = new SimpleDateFormat(pattern);
            sdfMap.put(pattern, sdf);
        }
        sdf.setTimeZone(timeZone);
        return sdf;
    }

    /**
     *
     * @param date
     * @return
     */
    public static String format(Date date){
        return format(date, DEFAULT_DATETIME_FORMAT);
    }

    /**
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern){
        return format(date, null, pattern);
    }

    /**
     *
     * @param date
     * @param timeZone
     * @param pattern
     * @return
     */
    public static String format(Date date, TimeZone timeZone, String pattern){
        Assert.assertArgumentNotNull(date, "date");
        return getSimpleDateFormat(pattern, timeZone).format(date);
    }


    /**
     *
     * @param dateString
     * @return
     */
    public static Date parse(String dateString){
        return parse(dateString, null, DEFAULT_FORMATS);
    }

    /**
     *
     * @param dateString
     * @param timeZone
     * @return
     */
    public static Date parse(String dateString, TimeZone timeZone){
        return parse(dateString, timeZone, DEFAULT_FORMATS);
    }

    /**
     *
     * @param dateString
     * @param patterns
     * @return
     */
    public static Date parse(String dateString, String... patterns){
        return parse(dateString, null, patterns);
    }

    /**
     *
     * @param dateString
     * @param timeZone
     * @param patterns
     * @return
     */
    public static Date parse(String dateString, TimeZone timeZone, String... patterns){
        Assert.assertArgumentNotNull(dateString, "dateString");
        patterns = patterns == null || patterns.length==0 ? DEFAULT_FORMATS: patterns;
        timeZone = timeZone == null? TimeZone.getDefault(): timeZone;

        Exception pe = null;
        for(String pattern: patterns){
            try {
                Date date = getSimpleDateFormat(pattern, timeZone).parse(dateString);
                if(date != null){
                    return date;
                }
            } catch (Exception e) {
                pe = e;
            }
        }
        throw new RuntimeException(pe);
    }


    public static void main(String args[]) throws ParseException {
        String date1 = DateFormatUtil.format(new Date(), "yyyy-MM-dd");
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String date2 = DateFormatUtil.format(new Date());
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String date3 = DateFormatUtil.format(new Date());
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);

        Date date11 = DateFormatUtil.parse(date1, "yyyy-MM-dd");
        Date date21 = DateFormatUtil.parse(date2);
        Date date31 = DateFormatUtil.parse(date3);
        System.out.println(date11);
        System.out.println(date21);
        System.out.println(date31);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ddZ");
        String str = null;
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        sdf.setTimeZone(TimeZone.getDefault());
        str = sdf.format(new Date());
        System.out.println(str);
        System.out.println(sdf.parse(str));

    }

}
