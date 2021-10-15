package com.jutudy.lotto.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class RandomLottoRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<RandomLotto> selectAll(RandomLotto randomLotto) {
        String query =
                "SELECT " +
                        "A.USER_ID, " +
                        "A.DATE, " +
                        "A.NO, " +
                        "A.NUM1, " +
                        "A.NUM2, " +
                        "A.NUM3, " +
                        "A.NUM4, " +
                        "A.NUM5, " +
                        "A.NUM6, " +
                        "A.EXTRA_NUM," +
                        "A.FST_RG_TS, " +
                        "A.LT_CH_TS " +
                        "FROM " +
                        "RANDOM_LOTTO A " +
                        "WHERE " +
                        "A.USER_ID = ? AND " +
                        "A.DATE = ? " +
                        "ORDER BY NO ";

        List<RandomLotto> result = jdbcTemplate.query(
                query,
                (rs, count) -> new RandomLotto(
                        rs.getString("user_id"),
                        rs.getString("date"),
                        rs.getInt("no"),
                        rs.getInt("num1"),
                        rs.getInt("num2"),
                        rs.getInt("num3"),
                        rs.getInt("num4"),
                        rs.getInt("num5"),
                        rs.getInt("num6"),
                        rs.getInt("extra_num"),
                        rs.getTimestamp("fst_rg_ts").toLocalDateTime(),
                        rs.getTimestamp("lt_ch_ts").toLocalDateTime()),
                randomLotto.getUserId(), randomLotto.getDate());

        return result;
    }

    public int insert(RandomLotto randomLotto) {
        String query =
                "INSERT INTO RANDOM_LOTTO ( " +
                        "USER_ID, " +
                        "DATE, " +
                        "NO, " +
                        "NUM1, " +
                        "NUM2, " +
                        "NUM3, " +
                        "NUM4, " +
                        "NUM5, " +
                        "NUM6, " +
                        "EXTRA_NUM, " +
                        "FST_RG_TS, " +
                        "LT_CH_TS " +
                        ") " +
                        "VALUES ( " +
                        "?, " +
                        "?, " +
                        "(SELECT IFNULL(MAX(NO),0) + 1 FROM RANDOM_LOTTO SUBTABLE WHERE USER_ID = ? AND DATE = ?), " +
                        "?, " +
                        "?, " +
                        "?, " +
                        "?, " +
                        "?, " +
                        "?, " +
                        "?, " +
                        "NOW(), " +
                        "NOW() " +
                        ")";
        return jdbcTemplate.update(query, randomLotto.getUserId(), randomLotto.getDate(), randomLotto.getUserId(),
                randomLotto.getDate(), randomLotto.getNum1(), randomLotto.getNum2(), randomLotto.getNum3(),
                randomLotto.getNum4(), randomLotto.getNum5(), randomLotto.getNum6(), randomLotto.getExtraNum());
    }
}



