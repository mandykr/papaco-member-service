package com.papaco.papacomemberservice.unit.member.domain;

import com.papaco.papacomemberservice.member.domain.Career;
import com.papaco.papacomemberservice.member.domain.TermOfOffice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CareerTest {
    private TermOfOffice termOfOffice;

    @BeforeEach
    void setUp() {
        termOfOffice = new TermOfOffice(
                LocalDate.of(2021, 01, 01),
                LocalDate.of(2022, 02, 01));
    }

    @DisplayName("경력을 생성한다")
    @Test
    void create() {
        assertThatCode(() ->
                new Career("주식회사", "개발자", termOfOffice))
                .doesNotThrowAnyException();
    }

    @DisplayName("회사명은 비어있지 않아야 한다")
    @ParameterizedTest
    @NullAndEmptySource
    void corporateNameIsEmpty(String corporateName) {
        assertThatThrownBy(() -> new Career(corporateName, "개발자", termOfOffice))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("직책은 비어있지 않아야 한다")
    @ParameterizedTest
    @NullAndEmptySource
    void dutyIsEmpty(String duty) {
        assertThatThrownBy(() -> new Career("주식회사", duty, termOfOffice))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
