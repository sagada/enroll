package com.sugang.app.domain.course.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"value"})
public class RoomNumber {

    private String value;

    public RoomNumber(String value)
    {
        this.value = value;
    }
}
