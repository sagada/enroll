package com.sugang.toys.command.course.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"value"})
public class RoomNumber {

    @Column(name = "room_number")
    private String value;

    public RoomNumber(String value)
    {
        this.value = value;
    }
}
