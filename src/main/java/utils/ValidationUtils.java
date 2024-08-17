package utils;

import business.orchestrator.Position;

import java.time.LocalDate;
import java.util.function.Predicate;

public class ValidationUtils {

    public static final Predicate<String> checkStringValidity = str -> str != null && !str.isBlank();

    public static final Predicate<Integer> checkRoverYearValidity = year -> (year != null && year > 1957 && year < LocalDate.now().getYear());

    public static final Predicate<Integer> checkCoordinateValidity = coordinate -> coordinate != null && coordinate > 0;

}
