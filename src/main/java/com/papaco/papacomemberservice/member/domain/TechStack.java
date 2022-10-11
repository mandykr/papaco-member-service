package com.papaco.papacomemberservice.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class TechStack {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public TechStack(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (Strings.isEmpty(name)) {
            throw new IllegalArgumentException();
        }
    }
}
