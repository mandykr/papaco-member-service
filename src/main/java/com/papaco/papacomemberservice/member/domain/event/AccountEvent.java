package com.papaco.papacomemberservice.member.domain.event;

import com.papaco.papacomemberservice.member.domain.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountEvent {
    private EventType eventType;
    private Long aggregateId;
    private String userName;
    private String name;
    private String email;
    private String roleKey;

    public Account toAccount() {
        return new Account(aggregateId, userName, name, email);
    }
}
