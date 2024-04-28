package com.sugang.app.domain.department;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
@EqualsAndHashCode(of = {"number", "faxNumber"})
class DepartmentPhoneNumber {
    private String number;
    private String faxNumber;

    public DepartmentPhoneNumber(String number, String faxNumber)
    {
        this.number = number;
        this.faxNumber = faxNumber;
    }
}
