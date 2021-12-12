package com.jutudy.lottoproject.userLotto.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserLottoRepository extends JpaRepository<UserLotto, Long> {

    List<UserLotto> findAllByUserIdAndRoundOrderByCreatedDate(String userId, long round);
}
