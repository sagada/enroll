package com.sugang.app.domain.department.infra;

import com.sugang.app.domain.department.domain.Department;
import com.sugang.app.domain.department.domain.DepartmentRepository;
import com.sugang.app.domain.department.domain.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department findById(Long id)
    {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("없는 아이디입니다."));
    }

}
