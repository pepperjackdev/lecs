package me.pepperjackdev.lecs.service;

import me.pepperjackdev.lecs.model.Subject;

public interface SubjectService {
    public Subject createSubject(Subject subject);
    public Iterable<Subject> getAllSubjects();
    public Subject getSubject(Long id);
    public Subject updateSubject(Long id, Subject subject);
    public void deleteSubject(Long id);
}
