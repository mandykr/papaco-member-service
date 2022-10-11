package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {
    private Long accountId;

    public Member toMember() {
        return new Member(accountId);
    }
}
