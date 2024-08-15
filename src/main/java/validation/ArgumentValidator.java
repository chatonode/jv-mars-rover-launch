package validation;

import java.util.List;
import java.util.Map;

import common.exception.InvalidAttributeException;

public class ArgumentValidator {
    public static void validateArgs(IsValidArgumentNameMap isValidArgumentNameMap) throws InvalidAttributeException {
        System.out.println("Initial Arg Map: " + isValidArgumentNameMap);

        List<String> argumentsWithError = isValidArgumentNameMap.entrySet().stream()
                .filter(isValidArgumentNameEntry -> !isValidArgumentNameEntry.getKey())
                .map(Map.Entry::getValue)
                .toList();

        if (!argumentsWithError.isEmpty()) {
            throwValidationException(argumentsWithError);
        }
    }

    private static void throwValidationException(List<String> argumentsWithError) {
        throw new InvalidAttributeException("Invalid argument(s)", argumentsWithError);
    }
}
