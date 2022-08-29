package engine.exceptions;

public class AnnotationException extends Exception{
    public AnnotationException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public AnnotationException(String errorMessage) {
        super(errorMessage);
    }
}
