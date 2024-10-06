package me.pepperjackdev.lecs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.pepperjackdev.lecs.model.Subject;
import me.pepperjackdev.lecs.repository.SubjectRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public Iterable<Subject> getMethodName() {
        return subjectRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subject postMethodName(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }
    
}
