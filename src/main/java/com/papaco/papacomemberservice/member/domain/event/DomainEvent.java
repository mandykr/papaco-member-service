package com.papaco.papacomemberservice.member.domain.event;

public interface DomainEvent {
    EventType getEventType();
    Long getAggregateId();
}
