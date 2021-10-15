package com.jutudy.lotto.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DefaultEntity {

    private LocalDateTime fstRgTs;
    private LocalDateTime ltChTs;

    @Override
    public String toString() {
        return "DefaultEntity{" +
                "fstRgTs=" + fstRgTs +
                ", ltChTs=" + ltChTs +
                '}';
    }
}
