package me.pepperjackdev.lecs.subject;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void shouldFindSubjectByTitle() {
        // given 
        Subject subject = new Subject(1L, "Test Subject");
        subjectRepository.save(subject);

        // when
        Optional<Subject> result = subjectRepository.findByTitle("Test Subject");

        // then
        assertThat(result).isPresent();
    }
}
