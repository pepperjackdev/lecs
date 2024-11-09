package me.pepperjackdev.lecs.exception.subject;

public class SubjectTitleAlreadyTakenException 
    extends RuntimeException {
    
    public SubjectTitleAlreadyTakenException() {
        /* empty constructor */
        super();
    }

    public SubjectTitleAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
