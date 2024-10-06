package me.pepperjackdev.lecs.repository;

import org.springframework.data.repository.CrudRepository;

import me.pepperjackdev.lecs.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
