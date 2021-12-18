package com.jutudy.lottoproject.winLotto.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WinLottoRepository extends JpaRepository<WinLotto, Long> {

    WinLotto findByRound(Long round);

    WinLotto findTopByOrderByRoundDesc();

    WinLotto findTopByOrderByDateDesc();

    List<WinLotto> findAllByOrderByRoundDesc();

}
