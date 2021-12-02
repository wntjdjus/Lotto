package com.jutudy.lottoproject.winLotto.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinLottoRepository extends JpaRepository<WinLotto, Long> {
}
