package validation;

import java.util.List;
import java.util.Map;

import common.exception.InvalidAttributeException;

public class ArgumentValidator {
    public static void validateArgs(IsValidArgumentNameMap isValidArgumentNameMap) throws InvalidAttributeException {
        List<String> argumentsWithError = isValidArgumentNameMap.entrySet().stream()
                .filter(Map.Entry::getKey)
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
