package com.sugang.toys.command.subject.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepository subjectRepository;

    @Override
    public Subject findById(Long id)
    {
        return subjectRepository.findById(id).orElseThrow();
    }
}
