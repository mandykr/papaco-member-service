package com.papaco.papacomemberservice.member.ui;

import com.papaco.papacomemberservice.member.application.MemberService;
import com.papaco.papacomemberservice.member.application.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberCreateRequest request) {
        MemberResponse member = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/members/" + member.getId())).body(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable Long id) {
        MemberResponse member = memberService.findMember(id);
        return ResponseEntity.ok().body(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequest request) {
        MemberResponse member = memberService.updateMember(id, request);
        return ResponseEntity.ok().body(member);
    }

    @GetMapping("/reviewers")
    public ResponseEntity<Page<MemberSearchResponse>> searchReviewers(
            @PageableDefault(size = 10, page = 0) Pageable page,
            @RequestBody MemberSearchRequest request) {
        Page<MemberSearchResponse> reviewers = memberService.searchReviewers(page, request);
        return ResponseEntity.ok().body(reviewers);
    }
}
