package com.papaco.papacomemberservice.member.application;

import com.papaco.papacomemberservice.member.application.dto.*;
import com.papaco.papacomemberservice.member.domain.Member;
import com.papaco.papacomemberservice.member.domain.TechStack;
import com.papaco.papacomemberservice.member.domain.repository.TechStackRepository;
import com.papaco.papacomemberservice.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final TechStackRepository techStackRepository;

    public MemberResponse createMember(MemberCreateRequest request) {
        Member member = memberRepository.save(request.toMember());
        return MemberResponse.of(member);
    }

    public MemberResponse findMember(Long id) {
        Member member = findMemberById(id);
        return MemberResponse.of(member);
    }

    public MemberResponse updateMember(Long id, MemberUpdateRequest request) {
        Member member = findMemberById(id);
        registerMemberTechStacks(member, request.getTechStackIds());
        member.registerCareers(request.getCareerEntities(member));
        if (request.isRegisteredReviewer()) {
            member.registerReviewer();
        }

        memberRepository.flush();
        return MemberResponse.of(member);
    }

    private void registerMemberTechStacks(Member member, List<Long> techStackIds) {
        List<TechStack> techStacks = techStackRepository.findAllById(techStackIds);
        if (techStackIds.size() != techStacks.size()) {
            throw new IllegalStateException();
        }
        member.registerTechStacks(techStacks);

    }

    private Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public Page<MemberSearchResponse> searchReviewers(Pageable page, MemberSearchRequest request) {
        Page<Member> reviewers = memberRepository.findAllIByTechStackIdIn(request.getTechStackIds(), page);
        return reviewers.map(MemberSearchResponse::of);
    }
}
