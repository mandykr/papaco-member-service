package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.TechStack;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class TechStackCreateRequest {
    private List<String> techStackNames;

    public List<TechStack> toTechStacks() {
        return techStackNames.stream().map(TechStack::new).collect(Collectors.toList());
    }
}
