package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.Career;
import com.papaco.papacomemberservice.member.domain.Member;
import com.papaco.papacomemberservice.member.domain.TermOfOffice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CareerRequest {
    private String corporateName;
    private String duty;
    private LocalDate joinedDate;
    private LocalDate leavedDate;

    public static Career toCareer(Member member, CareerRequest request) {
        return new Career(request.corporateName, request.duty, new TermOfOffice(request.joinedDate, request.leavedDate), member);
    }
}
