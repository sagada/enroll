package com.sugang.toys.command.department.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void 생성_테스트()
    {
        // given
        Department department = Department.create(null, "컴퓨터 공학과", Division.IT, "2222-2222", "2222-2222");

        // when
        Department save = departmentRepository.save(department);

        // then
        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getName()).isEqualTo("컴퓨터 공학과");
        Assertions.assertThat(save.getDivision()).isEqualTo(Division.IT);
        Assertions.assertThat(save.getPhoneNumber())
                .isEqualTo(new DepartmentPhoneNumber("2222-2222", "2222-2222"));
    }

}