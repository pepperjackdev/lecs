package me.pepperjackdev.lecs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.pepperjackdev.lecs.exception.subject.SubjectMismatchException;
import me.pepperjackdev.lecs.exception.subject.SubjectNotFoundException;
import me.pepperjackdev.lecs.model.Subject;
import me.pepperjackdev.lecs.repository.SubjectRepository;
import me.pepperjackdev.lecs.service.SubjectService;

@Service
public class SubjectServiceImpl
    implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Iterable<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubject(Long id) {
        return subjectRepository.findById(id)
            .orElseThrow(() -> new SubjectNotFoundException());
    }

    @Override
    public Subject updateSubject(Long id, Subject subject) {
        // checking for subjects' id matching
        if (id != subject.getId()) {
            throw new SubjectMismatchException();
        }
        
        // ensuring that the provided id belongs to an exsistig exception
        getSubject(id);

        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
    
}
