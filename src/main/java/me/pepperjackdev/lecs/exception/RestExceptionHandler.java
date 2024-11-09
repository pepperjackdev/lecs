package me.pepperjackdev.lecs.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import me.pepperjackdev.lecs.exception.subject.SubjectMismatchException;
import me.pepperjackdev.lecs.exception.subject.SubjectNotFoundException;
import me.pepperjackdev.lecs.exception.subject.SubjectTitleAlreadyTakenException;

@ControllerAdvice
public class RestExceptionHandler 
    extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler({ SubjectNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(Exception e, WebRequest request) {
        return handleExceptionInternal(e, "Book not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ SubjectTitleAlreadyTakenException.class })
    protected ResponseEntity<Object> handleTitleAlreadyTaken(Exception e, WebRequest request) {
        return handleExceptionInternal(e, "Title already taken", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ 
        SubjectMismatchException.class,
        ConstraintViolationException.class,
        DataIntegrityViolationException.class })
    protected ResponseEntity<Object> handleBadRequest(Exception e, WebRequest request) {
        return handleExceptionInternal(e, e.getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
