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
    private String email;

    public Account(Long id, String userName, String name, String email) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
    }

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
