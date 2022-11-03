package com.papaco.papacomemberservice.member.domain.repository;

import com.papaco.papacomemberservice.member.domain.event.ProcessedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedEventRepository extends JpaRepository<ProcessedMessage, Long> {
}
