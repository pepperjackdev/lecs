package me.pepperjackdev.lecs.subject;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// FIXME: Fix "SQL Feature Not Supported" error

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "SELECT (ID, TITLE) FROM SUBJECTS WHERE TITLE = ?1")
    Optional<Subject> getSubjectBySubjectTitle(String subjectTitle);
}