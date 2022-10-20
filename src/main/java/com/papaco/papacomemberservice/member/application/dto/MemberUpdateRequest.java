package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.Career;
import com.papaco.papacomemberservice.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class MemberUpdateRequest {
    private List<Long> techStackIds;
    private List<CareerRequest> careers;

    public List<Career> getCareerEntities(Member member) {
        return careers.stream().map(c -> CareerRequest.toCareer(member, c)).collect(Collectors.toList());
    }
}
