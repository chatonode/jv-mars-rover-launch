package exception.business;

import java.util.List;

public class InvalidClassParameterException extends RuntimeException {

    public InvalidClassParameterException(String message) {
        super(message);
    }

    public InvalidClassParameterException(String message, String invalidParam) {
        super(message);

        if (!invalidParam.isBlank()) {
            System.err.println("Invalid Class Parameter:");
            System.err.println("• " + invalidParam);
        }
    }

    public InvalidClassParameterException(String message, List<String> invalidParams) {
        super(message);

        if (invalidParams.size() > 1) {
            System.err.printf("Invalid Class Parameter List (%d):\n", invalidParams.size());
            invalidParams.forEach(arg -> System.err.println("• " + arg));
        }
    }
}
