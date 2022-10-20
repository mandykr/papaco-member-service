package com.papaco.papacomemberservice.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Account {
    @Id
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String name;

    public Account(Long id, String userName, String name) {
        this.id = id;
        this.userName = userName;
        this.name = name;
    }
}
