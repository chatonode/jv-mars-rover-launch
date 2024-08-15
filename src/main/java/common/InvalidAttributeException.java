package common;

import java.util.List;
public class InvalidAttributeException extends RuntimeException {

    public InvalidAttributeException(String message) {
        super(message);
    }

    public InvalidAttributeException(String message, List<String> args) {
        super(message);

        if (!args.isEmpty()) {
            System.err.println("Error Arguments:");
            args.forEach(arg -> System.err.println("- " + arg));
        }
    }
}
