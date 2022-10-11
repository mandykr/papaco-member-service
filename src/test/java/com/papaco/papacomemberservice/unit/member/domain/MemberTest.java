package com.papaco.papacomemberservice.unit.member.domain;

import com.papaco.papacomemberservice.member.domain.Member;
import com.papaco.papacomemberservice.member.domain.MemberTechStack;
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

        TechStack java = new TechStack("java");
        TechStack mysql = new TechStack("mysql");

        List<MemberTechStack> memberTechStacks = new ArrayList<>();
        memberTechStacks.add(new MemberTechStack(member, java));
        memberTechStacks.add(new MemberTechStack(member, mysql));

        // when
        member.registerTechStacks(memberTechStacks);

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
