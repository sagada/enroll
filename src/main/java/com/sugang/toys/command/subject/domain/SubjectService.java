package com.sugang.toys.command.subject.domain;

import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SubjectService {
    Subject findById(Long id);
    List<Subject> findByIds(Set<Long> ids);
}
