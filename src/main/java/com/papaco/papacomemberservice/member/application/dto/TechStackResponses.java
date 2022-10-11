package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.MemberTechStack;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TechStackResponses {
    public static List<TechStackResponse> of(List<MemberTechStack> memberTechStacks) {
        return memberTechStacks == null ?
                Collections.emptyList() :
                memberTechStacks.stream()
                        .map(s -> TechStackResponse.of(s.getTechStack()))
                        .collect(Collectors.toList());
    }
}
