package com.sugang.toys.command.professor.infra;

import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import com.sugang.toys.command.professor.domain.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;

    @Override
    public Professor findById(Long id)
    {
        return professorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("없는 교수 ID"));
    }
}
