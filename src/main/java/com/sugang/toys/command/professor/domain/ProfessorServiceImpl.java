package com.sugang.toys.command.professor.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService{

    private final ProfessorRepository professorRepository;

    @Override
    public Professor findById(Long id)
    {
        return professorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("없는 교수 ID"));
    }
}
