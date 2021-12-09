package com.jutudy.lottoproject.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class DateUtilTests {

    @Test
    public void 직전토요일구하기(){
        Date today = new Date();
        Date sat = DateUtil.findRecentSaturday(today);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        assertThat(simpleDateFormat.format(sat)).isEqualTo("20211204");
    }
}
