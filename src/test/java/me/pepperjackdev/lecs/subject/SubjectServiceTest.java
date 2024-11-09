package me.pepperjackdev.lecs.subject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.pepperjackdev.lecs.exception.subject.SubjectMismatchException;
import me.pepperjackdev.lecs.exception.subject.SubjectNotFoundException;
import me.pepperjackdev.lecs.exception.subject.SubjectTitleAlreadyTakenException;

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
        verify(subjectRepository).save(subject);
    }

    @Test 
    void shouldThrowAlreadyTakenSubjectTitleExceptionWhenCreatingSubjectWithSameTitleOfAnExistingSubject() {
        // given
        Subject subject = new Subject(1L, "Test Subject");

        // when...then
        when(subjectRepository.getSubjectBySubjectTitle("Test Subject")).thenReturn(Optional.of(subject));
        assertThrows(SubjectTitleAlreadyTakenException.class, () -> {
            subjectService.createSubject(subject);
        });

        verify(subjectRepository).getSubjectBySubjectTitle("Test Subject");
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

    @Test
    void shouldThrowSubjectNotFoundExceptionWhenGettingSubjectWithUnexistingId() {

        // when...then
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrowsExactly(SubjectNotFoundException.class, () -> {
            subjectService.getSubject(1L); // should throw SubjectNotFoundException
        });
    }

    @Test
    void shouldUpdateSubject() {
        // given
        Subject oldSubject = new Subject(1L, "Old Test Subject");
        Subject newSubject = new Subject(1L, "New Test Subject");

        // when
        when(subjectRepository.save(newSubject)).thenReturn(newSubject);
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(oldSubject));
        Subject result = subjectService.updateSubject(1L, newSubject);

        // then
        assertEquals(newSubject, result);
        verify(subjectRepository).findById(1L);
        verify(subjectRepository).save(newSubject);
    }

    @Test
    void shouldThrowSubjectMismatchExceptionWhenSubjectIdsDontMatch() {
        // given
        Subject newSubject = new Subject(1L, "New Test Subject");

        // when...then
        assertThrowsExactly(SubjectMismatchException.class, () -> {
            subjectService.updateSubject(2L, newSubject);
        });
    }

    @Test
    void sholdThrowSubjectNotFoundExceptionWhenUpdatingUnexistingSubject() {
        // given
        Subject newSubject = new Subject(1L, "New Test Subject");

        // when...then
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrowsExactly(SubjectNotFoundException.class, () -> {
            subjectService.updateSubject(1L, newSubject);
        });
    }
}
