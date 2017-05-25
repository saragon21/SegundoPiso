/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author SARAGON
 */
public class DateUtil {

    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public static final String DD_MM_YY_HH_MM_SS = "ddMMyyHHmmss";

    public static final String DD_MM_YY_HH_MM = "dd/MM/yy HH:mm";
    
    public static final String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";
    
    public static final String DDMMYYHHMMSS = "ddMMyyHHmmss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }

    public static boolean validDate(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DD_MM_YYYY);
            format.parse(date);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }
    
    public static Date formatDate(String date, String formatStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            return format.parse(date);
        } catch (ParseException pe) {
            return null;
        }
    }
    
    public static Date datePlusDays(String date, int numberDays) {
        Date dateObj = formatDate(date, DD_MM_YYYY);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateObj);
        calendar.add(Calendar.DAY_OF_MONTH, numberDays);
        return calendar.getTime();
    }
    
    public static Date datePlusDays(Date date, int numberDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, numberDays);
        return calendar.getTime();
    }
    
    public static Date format(Date date, String format) {
        String dateStr = formatDate(date, format);
        return formatDate(dateStr, format);
    }
}
