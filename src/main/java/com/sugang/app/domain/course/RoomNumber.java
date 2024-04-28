package com.sugang.app.domain.course;


import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
