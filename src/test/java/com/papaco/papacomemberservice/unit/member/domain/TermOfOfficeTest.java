package com.papaco.papacomemberservice.unit.member.domain;

import com.papaco.papacomemberservice.member.domain.TermOfOffice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TermOfOfficeTest {
    @DisplayName("입사일, 퇴사일로 재직 기간을 생성한다")
    @Test
    void create() {
        assertThatCode(() ->
                new TermOfOffice(
                        LocalDate.of(2021, 01, 01),
                        LocalDate.of(2022, 02, 01)))
                .doesNotThrowAnyException();
    }

    @DisplayName("재직중인 경우 입사일만 가진다")
    @Test
    void working() {
        assertThatCode(() ->
                new TermOfOffice(
                        LocalDate.of(2021, 01, 01),
                        null))
                .doesNotThrowAnyException();
    }

    @DisplayName("입사일이 퇴사일보다 클 수 없다")
    @Test
    void after() {
        assertThatThrownBy(() ->
                new TermOfOffice(
                        LocalDate.of(2022, 01, 01),
                        LocalDate.of(2021, 02, 01)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입사일이 퇴사일과 같을 수 없다")
    @Test
    void equal() {
        assertThatThrownBy(() ->
                new TermOfOffice(
                        LocalDate.of(2022, 01, 01),
                        LocalDate.of(2022, 01, 01)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
