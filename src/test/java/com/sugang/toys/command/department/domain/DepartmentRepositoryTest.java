package com.sugang.toys.command.department.domain;

import com.sugang.toys.config.JpaRepositoryTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


class DepartmentRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void departmentCreateTest()
    {
        // given
        Department department = Department.create(null, "컴퓨터 공학과", Division.IT, "2222-2222", "2222-2222");

        // when
        Department save = departmentRepository.save(department);

        // then
        assertThat(save).isNotNull();
        assertThat(save.getName()).isEqualTo("컴퓨터 공학과");
        assertThat(save.getDivision()).isEqualTo(Division.IT);
        assertThat(save.getPhoneNumber())
                .isEqualTo(new DepartmentPhoneNumber("2222-2222", "2222-2222"));
    }
}