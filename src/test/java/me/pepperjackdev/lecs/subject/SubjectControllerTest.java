package me.pepperjackdev.lecs.subject;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class SubjectControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;

    @Test
    void shouldCreateSubject() throws Exception {
        // given
        Subject subject = new Subject(1L, "Test Subject");

        // when...then
        when(subjectService.createSubject(any(Subject.class))).thenReturn(subject);
        this.mockMvc.perform(post("/api/subjects")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\": \"Test Subject\"}"))
            .andExpect(status().isCreated());
    }

    @Test
    void shouldGetAllSubject() throws Exception {
        // given
        List<Subject> subjects = List.of(
            new Subject(1L, "Test Subject")
        );

        // when...then
        when(subjectService.getAllSubjects()).thenReturn(subjects);
        mockMvc.perform(get("/api/subjects"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].title").value("Test Subject"));
    }

    @Test
    void shouldGetSubject() throws Exception {
        // given
        Subject subject = new Subject(1L, "Test Subject");

        // when...then
        when(subjectService.getSubject(1L)).thenReturn(subject);
        mockMvc.perform(get("/api/subjects/{id}", 1L))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.title").value("Test Subject"));
    }

    @Test
    void shouldUpdateSubject() throws Exception {
        // given
        Subject subject = new Subject(1L, "Test Subject");

        // when...then
        when(subjectService.updateSubject(anyLong(), any(Subject.class))).thenReturn(subject);
        mockMvc.perform(put("/api/subjects/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\": 1, \"title\": \"Test Subject\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.title").value("Test Subject"));
    }

    @Test 
    void shoudDeleteSubject() throws Exception {
        // when...then
        mockMvc.perform(delete("/api/subjects/{id}", 1L))
            .andExpect(status().isOk());
    }

}
