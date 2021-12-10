package com.jutudy.lottoproject.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date findRecentSaturday(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);
        int before = -1;
        // 토요일은 7, 토요일이 아니면 가장 직전 토요일을 찾는다.
        if (dayNum == 7) {
            before = -7;
        }
        calendar.add(Calendar.DATE, (-1) * dayNum);

        return calendar.getTime();
    }
}
