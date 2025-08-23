package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static Date convertStringToDate(String dateString) {
        if (dateString == null) {
            return null;
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(dateString);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format: " + dateString);
        }
    }

    public static String convertDateToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}

