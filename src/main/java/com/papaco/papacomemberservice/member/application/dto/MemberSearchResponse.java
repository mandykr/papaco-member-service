package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberSearchResponse {
    private Long id;
    private Long accountId;
    private boolean registeredReviewer;
    private List<TechStackResponse> techStacks;
    private List<CareerResponse> careers;

    public static MemberSearchResponse of(Member member) {
        return new MemberSearchResponse(
                member.getId(),
                member.getAccountId(),
                member.isRegisteredReviewer(),
                TechStackResponses.of(member.getMemberTechStacks()),
                CareerResponses.of(member.getCareers())
        );
    }
}
