package me.pepperjackdev.lecs.exception.subject;

public class SubjectNotFoundException extends RuntimeException {

    public SubjectNotFoundException() {
        /* empty constructor */
        super();
    }

    public SubjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
