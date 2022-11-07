package com.papaco.papacomemberservice.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountId;
    private boolean registeredReviewer;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTechStack> memberTechStacks = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Career> careers = new ArrayList<>();

    public Member(Long accountId) {
        this.accountId = accountId;
        this.registeredReviewer = false;
    }

    public Member(Long accountId, boolean registeredReviewer) {
        this.accountId = accountId;
        this.registeredReviewer = registeredReviewer;
    }

    public void registerTechStacks(List<TechStack> techStacks) {
        List<MemberTechStack> memberTechStacks = new ArrayList<>();
        techStacks.forEach(s -> memberTechStacks.add(new MemberTechStack(this, s)));
        this.memberTechStacks.clear();
        this.memberTechStacks.addAll(memberTechStacks);
    }

    public void registerCareers(List<Career> careers) {
        this.careers.clear();
        this.careers.addAll(careers);
    }

    public void registerReviewer() {
        this.registeredReviewer = true;
    }

    public void deregisterReviewer() {
        this.registeredReviewer = false;
    }
}
