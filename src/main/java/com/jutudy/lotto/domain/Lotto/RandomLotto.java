package com.jutudy.lotto.domain.Lotto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class RandomLotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer randomLottoNo;
}
