package com.papaco.papacomemberservice.unit.member.domain;

import com.papaco.papacomemberservice.member.domain.Member;
import com.papaco.papacomemberservice.member.domain.TechStack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class MemberTest {

    @DisplayName("회원을 생성한다")
    @Test
    void create() {
        assertThatCode(() ->
                new Member(1L, true))
                .doesNotThrowAnyException();
    }

    @DisplayName("기술 스택을 등록한다")
    @Test
    void techStack() {
        // given
        Member member = new Member(1L);

        List<TechStack> techStacks = new ArrayList<>();
        techStacks.add(new TechStack("java"));
        techStacks.add(new TechStack("mysql"));

        // when
        member.registerTechStacks(techStacks);

        // then
        assertThat(member.getMemberTechStacks()).hasSize(2);
    }

    @DisplayName("리뷰어로 등록한다")
    @Test
    void register_reviewer() {
        // given
        Member member = new Member(1L);

        // when
        member.registerReviewer();

        // then
        assertThat(member.isRegisteredReviewer()).isTrue();
    }

    @DisplayName("리뷰어 등록을 취소한다")
    @Test
    void deregister_reviewer() {
        // given
        Member member = new Member(1L);

        // when
        member.deregisterReviewer();

        // then
        assertThat(member.isRegisteredReviewer()).isFalse();
    }
}
