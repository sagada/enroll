package com.sugang.app.domain.department.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
@EqualsAndHashCode(of = {"number", "faxNumber"})
public class DepartmentPhoneNumber {

    @Column(name = "number")
    private String number;

    @Column(name = "fax_number")
    private String faxNumber;

    public DepartmentPhoneNumber(String number, String faxNumber)
    {
        this.number = number;
        this.faxNumber = faxNumber;
    }
}
