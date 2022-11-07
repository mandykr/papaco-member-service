package com.papaco.papacomemberservice.member.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemberSearchRequest {
    private List<Long> techStackIds;
}
