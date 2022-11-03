package com.papaco.papacomemberservice.member.application.message;

import java.util.Map;

public interface AccountConsumer {
    void receive(String payload, Map<String, String> headers);

    void checkDuplicate(Long duplicateId);

    void process(String payload, Map<String, String> headers);

    void saveProcessedMessage(Long eventId, Long aggregateId);
}
