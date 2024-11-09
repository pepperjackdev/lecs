package me.pepperjackdev.lecs.subject;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// FIXME: Fix "SQL Feature Not Supported" error

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findByTitle(String subjectTitle);
}