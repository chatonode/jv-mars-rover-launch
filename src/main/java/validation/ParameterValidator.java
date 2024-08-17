package validation;

import java.util.List;
import java.util.Map;
import java.util.Comparator;

import exception.business.InvalidClassParameterException;

public class ParameterValidator {
    public static void validateParams(ParamIsValidMap paramIsValidMap) throws InvalidClassParameterException {
        List<String> invalidParams = paramIsValidMap.entrySet().stream()
                .filter(paramIsValidEntry -> !paramIsValidEntry.getValue())
                .map(Map.Entry::getKey)
                .sorted(Comparator.naturalOrder())
                .toList();

        if (!invalidParams.isEmpty()) {
            if (invalidParams.size() == 1) {
                throwExceptionWithParams(invalidParams.getFirst());
            }

            throwExceptionWithParams(invalidParams);
        }

    }

    private static void throwExceptionWithParams(String invalidParam) {
        throw new InvalidClassParameterException("Class parameter is invalid!", invalidParam);
    }

    private static void throwExceptionWithParams(List<String> invalidParams) {
        throw new InvalidClassParameterException("Class parameters are invalid!", invalidParams);
    }
}
