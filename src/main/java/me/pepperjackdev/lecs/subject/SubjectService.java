package me.pepperjackdev.lecs.subject;

import org.springframework.stereotype.Service;

@Service
public interface SubjectService {
    public Subject createSubject(Subject subject);
    public Iterable<Subject> getAllSubjects();
    public Subject getSubject(Long id);
    public Subject updateSubject(Long id, Subject subject);
    public void deleteSubject(Long id);
}
