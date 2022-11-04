package com.papaco.papacomemberservice.member.application;

import com.papaco.papacomemberservice.member.application.dto.TechStackCreateRequest;
import com.papaco.papacomemberservice.member.application.dto.TechStackResponse;
import com.papaco.papacomemberservice.member.domain.TechStack;
import com.papaco.papacomemberservice.member.domain.repository.TechStackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class TechStackService {
    private final TechStackRepository techStackRepository;

    public List<TechStackResponse> createTechStacks(TechStackCreateRequest request) {
        List<TechStack> techStacks = techStackRepository.saveAll(request.toTechStacks());
        return techStacks.stream()
                .map(TechStackResponse::of)
                .collect(Collectors.toList());
    }
}
