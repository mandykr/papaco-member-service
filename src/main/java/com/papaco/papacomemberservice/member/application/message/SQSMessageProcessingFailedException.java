package com.papaco.papacomemberservice.member.application.message;

public class SQSMessageProcessingFailedException extends RuntimeException {
    public SQSMessageProcessingFailedException(Throwable cause) {
        super(cause);
    }
}
