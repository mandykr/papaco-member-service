package com.papaco.papacomemberservice.member.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.papaco.papacomemberservice.member.application.message.AccountConsumer;
import com.papaco.papacomemberservice.member.application.message.MessageReceiveDuplicatedException;
import com.papaco.papacomemberservice.member.application.message.MessageProcessingFailedException;
import com.papaco.papacomemberservice.member.domain.Account;
import com.papaco.papacomemberservice.member.domain.event.AccountEvent;
import com.papaco.papacomemberservice.member.domain.event.ProcessedMessage;
import com.papaco.papacomemberservice.member.domain.repository.AccountRepository;
import com.papaco.papacomemberservice.member.domain.repository.ProcessedEventRepository;
import io.awspring.cloud.messaging.core.SqsMessageHeaders;
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
public class AccountSQSConsumer implements AccountConsumer {
    private final ProcessedEventRepository eventRepository;
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;

    @Override
    @SqsListener(value = "sqs-login-account-update.fifo", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receive(String payload, @Headers Map<String, String> headers) {
        Long messageId = Long.getLong(headers.get(SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER));
        Long aggregateId = Long.getLong(headers.get(SqsMessageHeaders.SQS_GROUP_ID_HEADER));

        checkDuplicate(messageId);
        process(payload, headers);
        saveProcessedMessage(messageId, aggregateId);
    }

    @Override
    public void checkDuplicate(Long duplicateId) {
        if (eventRepository.findById(duplicateId).isPresent()) {
            throw new MessageReceiveDuplicatedException(duplicateId);
        }
    }

    @Override
    public void process(String payload, Map<String, String> headers) {
        try {
            AccountEvent accountEvent = objectMapper.readValue(payload, AccountEvent.class);
            accountRepository.findById(accountEvent.getAggregateId())
                    .ifPresentOrElse(
                            entity -> entity.update(accountEvent.getName(), accountEvent.getEmail()),
                            () -> saveAccount(accountEvent)
                    );
            // TODO: publish event
        } catch (JsonProcessingException | IllegalArgumentException e) {
            log.error("payload={}, headers={}", payload, headers, e);
            throw new MessageProcessingFailedException(e);
        }
    }

    private void saveAccount(AccountEvent accountEvent) {
        Account account = accountEvent.toAccount();
        accountRepository.save(account);
    }

    @Override
    public void saveProcessedMessage(Long messageId, Long aggregateId) {
        eventRepository.save(new ProcessedMessage(messageId, aggregateId));
    }
}
