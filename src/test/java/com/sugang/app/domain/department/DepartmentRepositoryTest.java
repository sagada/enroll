package com.sugang.app.domain.department;

import com.sugang.app.JpaRepositoryTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


class DepartmentRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    DepartmentRepository departmentRepository;

    @DisplayName("학과를 생성할 수 있다.")
    @Test
    void departmentCreateTest()
    {
        // given
        Department department = Department.create("컴퓨터 공학과", Division.IT, "2222-2222");

        // when
        Department save = departmentRepository.save(department);

        // then
        assertThat(save).isNotNull();
        assertThat(save.getName()).isEqualTo("컴퓨터 공학과");
        assertThat(save.getDivision()).isEqualTo(Division.IT);
    }
}