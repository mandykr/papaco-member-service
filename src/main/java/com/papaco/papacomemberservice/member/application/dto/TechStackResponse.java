package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.TechStack;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TechStackResponse {
    private Long id;
    private String name;

    public static TechStackResponse of(TechStack techStack) {
        return new TechStackResponse(techStack.getId(), techStack.getName());
    }
}
