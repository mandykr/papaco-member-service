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

    private void validate(LocalDate joinedDate, LocalDate leavedDate) {
        if (Objects.isNull(joinedDate) ||
                isNotWorking(joinedDate, leavedDate)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNotWorking(LocalDate joinedDate, LocalDate leavedDate) {
        return !Objects.isNull(leavedDate) &&
                !joinedDate.isBefore(leavedDate);
    }
}
