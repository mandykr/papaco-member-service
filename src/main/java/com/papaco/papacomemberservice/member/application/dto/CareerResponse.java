package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.Career;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class CareerResponse {
    private Long id;
    private String corporateName;
    private String duty;
    private LocalDate joinedDate;
    private LocalDate leavedDate;


    public static CareerResponse of(Career career) {
        return new CareerResponse(
                career.getId(),
                career.getCorporateName(),
                career.getDuty(),
                career.getTermOfOffice().getJoinedDate(),
                career.getTermOfOffice().getLeavedDate()
        );
    }
}
