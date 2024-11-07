package me.pepperjackdev.lecs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.pepperjackdev.lecs.model.Subject;
import me.pepperjackdev.lecs.repository.SubjectRepository;
import me.pepperjackdev.lecs.service.impl.SubjectServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService = new SubjectServiceImpl();

    @Test
    void shouldCreateNewSubject() {
        // given
        Subject subject = new Subject(1L, "Test Subject");

        // when
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);
        Subject result = subjectService.createSubject(subject);

        //then
        assertEquals(subject, result);
        assertEquals(subject.getTitle(), "Test Subject");
        verify(subjectRepository).save(subject);
    }

    @Test
    void shouldGetAllSubjects() {
        // given
        List<Subject> subjects = List.of(
            new Subject(1L, "Test Subject 1"),
            new Subject(2L, "Test Subject 2"),
            new Subject(3L, "Test Subject 3")
        );
        
        // when
        when(subjectRepository.findAll()).thenReturn(subjects);
        Iterable<Subject> result = subjectService.getAllSubjects();

        //then
        assertIterableEquals(subjects, result);
        verify(subjectRepository).findAll();
    }

    @Test
    void shouldGetSubject() {
        // given
        Subject subject = new Subject(1L, "Test Subject");

        // when
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        Subject result = subjectService.getSubject(1L);

        // then
        assertNotNull(result);
        verify(subjectRepository).findById(1L);
    }
}
