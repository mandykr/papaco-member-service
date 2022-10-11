package com.papaco.papacomemberservice.member.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Embeddable
public class TermOfOffice {
    @Column(nullable = false)
    private final LocalDate joinedDate;
    private final LocalDate leavedDate;

    protected TermOfOffice() {
        this.joinedDate = null;
        this.leavedDate = null;
    }

    public TermOfOffice(LocalDate joinedDate, LocalDate leavedDate) {
        validate(joinedDate, leavedDate);
        this.joinedDate = joinedDate;
        this.leavedDate = leavedDate;
    }

    public static TermOfOffice createWorking(LocalDate joinedDate) {
        return new TermOfOffice(joinedDate, LocalDate.MAX);
    }

    private void validate(LocalDate joinedDate, LocalDate leavedDate) {
        if (Objects.isNull(joinedDate) ||
                !joinedDate.isBefore(leavedDate)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TermOfOffice that = (TermOfOffice) o;
        return joinedDate.equals(that.joinedDate) && leavedDate.equals(that.leavedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(joinedDate, leavedDate);
    }
}
