package com.jutudy.lottoproject.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilTests {

    @Test
    public void 직전토요일구하기() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        simpleDateFormat.parse("20211212");
        Date sat = DateUtil.findRecentSaturday(simpleDateFormat.parse("20211212"));
        assertThat(simpleDateFormat.format(sat)).isEqualTo("20211211");
    }
}
