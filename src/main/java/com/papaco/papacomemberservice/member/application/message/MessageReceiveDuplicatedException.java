package com.papaco.papacomemberservice.member.application.message;

public class MessageReceiveDuplicatedException extends RuntimeException {
    public MessageReceiveDuplicatedException(Long duplicateId) {
        super("Message Receive Duplicated, eventId: " + duplicateId);
    }
}
