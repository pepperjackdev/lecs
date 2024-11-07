package me.pepperjackdev.lecs.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SubjectControllerTest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private SubjectController testSubjectController;

    @Test
    void getRequestShouldReturnEmptyIterable() {
        var result = testSubjectController.getAllSubjects();
        assertThat(result).isEmpty();
    }

}
