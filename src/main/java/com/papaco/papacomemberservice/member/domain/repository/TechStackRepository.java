package com.papaco.papacomemberservice.member.domain.repository;

import com.papaco.papacomemberservice.member.domain.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechStackRepository extends JpaRepository<TechStack, Long> {
}
