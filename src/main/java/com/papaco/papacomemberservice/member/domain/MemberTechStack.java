package com.papaco.papacomemberservice.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MemberTechStack {
    @Column(name = "seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long seq;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "member_id",
            foreignKey = @ForeignKey(name = "fk_member_tech_stack_to_member")
    )
    private Member member;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "techStack_id",
            foreignKey = @ForeignKey(name = "fk_member_tech_stack_to_tech_stack")
    )
    private TechStack techStack;

    public MemberTechStack(Member member, TechStack techStack) {
        this.member = member;
        this.techStack = techStack;
    }
}
