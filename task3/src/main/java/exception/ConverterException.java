package exception;

public class ConverterException extends RuntimeException {
    public ConverterException() {
    }

    public ConverterException(String message) {
        super(message);
    }

    public ConverterException(Throwable cause) {
        super(cause);
    }
}
