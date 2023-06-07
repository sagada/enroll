package com.sugang.app.domain.department;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
@EqualsAndHashCode(of = {"number", "faxNumber"})
public class DepartmentPhoneNumber {
    private String number;
    private String faxNumber;

    public DepartmentPhoneNumber(String number, String faxNumber)
    {
        this.number = number;
        this.faxNumber = faxNumber;
    }
}
