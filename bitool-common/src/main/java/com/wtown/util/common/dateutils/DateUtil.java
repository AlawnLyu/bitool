/**
 * @author LYU
 * @create 2018年01月12日 16:49
 * @Copyright(C) 2010 - 2018 GBSZ
 * All rights reserved
 */

package com.wtown.util.common.dateutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    private static DateUtil du = new DateUtil();

    private DateUtil() {
    }

    public static DateUtil getInstance() {
        return du;
    }

    public String getDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public List<String> getDateList(String startTime, String endTime) throws ParseException {
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(endTime);
        Calendar dd1 = Calendar.getInstance();
        dd1.setTime(d1);
        Calendar dd2 = Calendar.getInstance();
        dd2.setTime(d2);
        List<String> dates = new ArrayList<String>();
        while (dd1.compareTo(dd2) <= 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(dd1.getTime());
            dates.add(str);
            dd1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }
}
