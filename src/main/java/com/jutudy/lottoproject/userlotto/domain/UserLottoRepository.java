package com.jutudy.lottoproject.userlotto.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLottoRepository extends JpaRepository<UserLotto, Long> {

    @Query("select l from UserLotto l where l.userId = :userId and l.round = :round order by l.createdDate")
    List<UserLotto> findAllByRound(@Param("userId") String userId, @Param("round") int round);
}
