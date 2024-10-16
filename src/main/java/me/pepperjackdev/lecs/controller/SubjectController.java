package me.pepperjackdev.lecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.pepperjackdev.lecs.exception.subject.SubjectMismatchException;
import me.pepperjackdev.lecs.exception.subject.SubjectNotFoundException;
import me.pepperjackdev.lecs.model.Subject;
import me.pepperjackdev.lecs.repository.SubjectRepository;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    
    @Autowired
    SubjectRepository subjectRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subject createSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @GetMapping
    public Iterable<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Long id) {
        return subjectRepository.findById(id)
            .orElseThrow(() -> new SubjectNotFoundException());
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable Long id, @RequestBody Subject subject) {
        // checking for subjects' id matching
        if (id != subject.getId()) {
            throw new SubjectMismatchException();
        }
        
        // ensuring that the provided id belongs to an exsistig exception
        getSubject(id);

        return subjectRepository.save(subject);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectRepository.deleteById(id);
    }
    
}
