package com.papaco.papacomemberservice.unit.member.domain;

import com.papaco.papacomemberservice.member.domain.TechStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TechStackTest {
    @DisplayName("이름으로 기술 스택을 생성한다")
    @Test
    void create() {
        assertThatCode(() ->
                new TechStack("java"))
                .doesNotThrowAnyException();
    }

    @DisplayName("회사명은 비어있지 않아야 한다")
    @ParameterizedTest
    @NullAndEmptySource
    void corporateNameIsEmpty(String name) {
        assertThatThrownBy(() -> new TechStack(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
