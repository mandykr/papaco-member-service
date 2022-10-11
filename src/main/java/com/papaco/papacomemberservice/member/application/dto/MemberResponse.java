package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private Long accountId;
    private boolean registeredReviewer;
    private List<TechStackResponse> techStacks;
    private List<CareerResponse> careers;

    public static MemberResponse of(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getAccountId(),
                member.isRegisteredReviewer(),
                TechStackResponses.of(member.getMemberTechStacks()),
                CareerResponses.of(member.getCareers())
        );
    }
}
