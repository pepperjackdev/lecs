package me.pepperjackdev.lecs.exception.subject;

public class SubjectMismatchException extends RuntimeException {

    public SubjectMismatchException() {
        /* empty constructor */
        super();
    }

    public SubjectMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
