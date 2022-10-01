package com.sugang.toys.command.common.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
public class PhoneNumber {

    private String phoneNumber;

    public PhoneNumber(String phoneNumber)
    {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
        Matcher matcher = pattern.matcher(phoneNumber);

        if (!matcher.matches())
        {
            throw new RuntimeException("폰 넘버 형식에러");
        }

        this.phoneNumber = phoneNumber;
    }
}
