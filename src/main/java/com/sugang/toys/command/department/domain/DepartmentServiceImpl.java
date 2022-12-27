package com.sugang.toys.command.department.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    @Override
    public Department findById(Long id)
    {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("없는 아이디입니다."));
    }

}
