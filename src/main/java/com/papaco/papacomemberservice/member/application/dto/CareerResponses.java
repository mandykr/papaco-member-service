package com.papaco.papacomemberservice.member.application.dto;

import com.papaco.papacomemberservice.member.domain.Career;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CareerResponses {
    public static List<CareerResponse> of(List<Career> careers) {
        return careers == null ? Collections.emptyList() :
                careers.stream()
                        .map(CareerResponse::of)
                        .collect(Collectors.toList());
    }
}
