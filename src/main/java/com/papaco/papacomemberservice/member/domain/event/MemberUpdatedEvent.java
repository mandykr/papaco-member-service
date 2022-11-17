package com.papaco.papacomemberservice.member.domain.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberUpdatedEvent implements MemberEvent {
    private EventType eventType;
    private Long aggregateId;
    private Long accountId;
    private String userName;
    private boolean registeredReviewer;

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public Long getAggregateId() {
        return aggregateId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isRegisteredReviewer() {
        return registeredReviewer;
    }
}
