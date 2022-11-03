package com.papaco.papacomemberservice.member.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProcessedMessage {
    @Id
    private Long id;
    private Long aggregateId;
}
