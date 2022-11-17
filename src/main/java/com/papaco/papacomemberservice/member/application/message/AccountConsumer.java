package com.papaco.papacomemberservice.member.application.message;

import java.util.Map;

public interface AccountConsumer {
    void receive(String payload, Map<String, String> headers);

    void checkDuplicate(Long duplicateId, String aggregateType);

    void process(String payload, Map<String, String> headers);

    void saveProcessedMessage(Long messageId, Long aggregateId, String aggregateType);
}
