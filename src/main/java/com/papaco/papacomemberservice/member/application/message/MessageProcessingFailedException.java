package com.papaco.papacomemberservice.member.application.message;

public class MessageProcessingFailedException extends RuntimeException {
    public MessageProcessingFailedException(Throwable cause) {
        super(cause);
    }
}
