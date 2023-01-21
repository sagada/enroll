package com.sugang.toys.command.professor.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "professor")
@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private Long departmentId;

    @Column(name = "professor_status")
    @Enumerated(EnumType.STRING)
    private ProfessorStatus professorStatus;

    public Professor(Long id,  String name, ProfessorStatus professorStatus)
    {
        this.id = id;
        this.name = name;
        this.professorStatus = professorStatus;
    }

    public static Professor create(
            Long id
            , String name
            , ProfessorStatus professorStatus
    )
    {
        return new Professor(id, name, professorStatus);
    }

    public boolean working()
    {
        return this.getProfessorStatus().equals(ProfessorStatus.WORK);
    }
}
