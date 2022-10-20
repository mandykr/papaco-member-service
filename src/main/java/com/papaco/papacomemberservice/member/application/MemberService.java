package com.papaco.papacomemberservice.member.application;

import com.papaco.papacomemberservice.member.application.dto.MemberCreateRequest;
import com.papaco.papacomemberservice.member.application.dto.MemberResponse;
import com.papaco.papacomemberservice.member.application.dto.MemberUpdateRequest;
import com.papaco.papacomemberservice.member.domain.Career;
import com.papaco.papacomemberservice.member.domain.Member;
import com.papaco.papacomemberservice.member.domain.TechStack;
import com.papaco.papacomemberservice.member.domain.TechStackRepository;
import com.papaco.papacomemberservice.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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
        registerMemberCareers(member, request.getCareerEntities(member));
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

    private void registerMemberCareers(Member member, List<Career> careers) {
        member.registerCareers(careers);
    }

    private Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    // TODO: 리뷰어로 등록된 회원을 기술 스택으로 검색할 수 있다.
}
