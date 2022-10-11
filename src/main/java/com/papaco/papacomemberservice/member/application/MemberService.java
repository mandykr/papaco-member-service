package com.papaco.papacomemberservice.member.application;

import com.papaco.papacomemberservice.member.application.dto.MemberCreateRequest;
import com.papaco.papacomemberservice.member.application.dto.MemberResponse;
import com.papaco.papacomemberservice.member.domain.Member;
import com.papaco.papacomemberservice.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse createMember(MemberCreateRequest request) {
        Member member = memberRepository.save(request.toMember());
        return MemberResponse.of(member);
    }
}
