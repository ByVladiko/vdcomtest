package exception;

public class InvalidFileContent extends RuntimeException {

    public InvalidFileContent() {

    }

    public InvalidFileContent(String message) {
        super(message);
    }

    public InvalidFileContent(Throwable cause) {
        super(cause);
    }
}
