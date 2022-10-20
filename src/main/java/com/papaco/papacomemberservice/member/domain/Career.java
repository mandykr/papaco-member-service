package com.papaco.papacomemberservice.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Career {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String corporateName;

    @Column(nullable = false)
    private String duty;

    @Embedded
    private TermOfOffice termOfOffice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            foreignKey = @ForeignKey(name = "fk_career_to_member")
    )
    private Member member;

    public Career(String corporateName, String duty, TermOfOffice termOfOffice, Member member) {
        validate(corporateName, duty);
        this.corporateName = corporateName;
        this.duty = duty;
        this.termOfOffice = termOfOffice;
        this.member = member;
    }

    private void validate(String corporateName, String duty) {
        if (Strings.isEmpty(corporateName) || Strings.isEmpty(duty)) {
            throw new IllegalArgumentException();
        }
    }
}
