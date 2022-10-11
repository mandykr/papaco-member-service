package com.papaco.papacomemberservice.member.domain.repository;

import com.papaco.papacomemberservice.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
