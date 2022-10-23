package com.papaco.papacomemberservice.member.application.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.papaco.papacomemberservice.member.domain.Account;
import com.papaco.papacomemberservice.member.domain.event.AccountEvent;
import com.papaco.papacomemberservice.member.domain.repository.AccountRepository;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AccountSQSConsumer {
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;

    @SqsListener(value = "QUEUE-apigateway-to-member.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receive(String payload, @Headers Map<String, String> headers) {
        try {
            AccountEvent accountEvent = objectMapper.readValue(payload, AccountEvent.class);
            accountRepository.findById(accountEvent.getId())
                    .ifPresentOrElse(
                            entity -> entity.update(accountEvent.getName(), accountEvent.getEmail()),
                            () -> saveAccount(accountEvent)
                    );
        } catch (JsonProcessingException | IllegalArgumentException e) {
            log.error("payload={}, headers={}", payload, headers, e);
            throw new SQSMessageProcessingFailedException(e);
        }
    }

    private void saveAccount(AccountEvent accountEvent) {
        Account account = accountEvent.toAccount();
        accountRepository.save(account);
    }
}
