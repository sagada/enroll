package com.sugang.app.domain.subject;

import java.util.List;
import java.util.Set;

public interface SubjectService {
    Subject findById(Long id);
    List<Subject> findByIds(Set<Long> ids);
}
