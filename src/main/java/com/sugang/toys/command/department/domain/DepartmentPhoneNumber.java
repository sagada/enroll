package com.sugang.toys.command.department.domain;

import javax.persistence.Embeddable;

@Embeddable
public class DepartmentPhoneNumber {
    private String number;
    private String faxNumber;

    public DepartmentPhoneNumber(String number, String faxNumber) {
        this.number = number;
        this.faxNumber = faxNumber;
    }
}
