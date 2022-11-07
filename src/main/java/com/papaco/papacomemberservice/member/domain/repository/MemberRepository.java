package com.papaco.papacomemberservice.member.domain.repository;

import com.papaco.papacomemberservice.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m" +
            " from Member m" +
            " where m.id in (" +
                "select mt.member.id" +
                " from MemberTechStack mt" +
                " where mt.techStack.id in :techStackIds" +
            ")")
    Page<Member> findAllIByTechStackIdIn(List<Long> techStackIds, Pageable page);
}
