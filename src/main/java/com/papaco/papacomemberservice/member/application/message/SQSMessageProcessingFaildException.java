package com.papaco.papacomemberservice.member.application.message;

public class SQSMessageProcessingFaildException extends RuntimeException {
    public SQSMessageProcessingFaildException(Throwable cause) {
        super(cause);
    }
}
