package com.sugang.toys.command.student.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class StudentRegistrationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fee;
    private String semester;
    private String personalAccount;
    private String bankName;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "registered")
    private boolean registered;

    public StudentRegistrationInfo(boolean registered, LocalDateTime registerDate)
    {
        this.registered = registered;
        this.registerDate = registerDate;
    }

    public void completeRegister()
    {
        this.registered = true;
    }
}
