package com.papaco.papacomemberservice.member.ui;

import com.papaco.papacomemberservice.member.application.TechStackService;
import com.papaco.papacomemberservice.member.application.dto.TechStackCreateRequest;
import com.papaco.papacomemberservice.member.application.dto.TechStackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/techstacks")
@RestController
public class TechStackController {
    private final TechStackService techStackService;

    @PostMapping
    public ResponseEntity<List<TechStackResponse>> createTechStacks(@RequestBody TechStackCreateRequest request) {
        List<TechStackResponse> techStacks = techStackService.createTechStacks(request);
        return ResponseEntity.created(URI.create("/techstacks")).body(techStacks);
    }
}
