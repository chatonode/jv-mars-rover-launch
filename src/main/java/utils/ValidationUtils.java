package utils;

import common.Position;

import java.time.LocalDate;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ValidationUtils {

    public static Predicate<String> checkStringValidity = str -> str != null && !str.isBlank();

    public static Predicate<Position> checkPositionValidity = position -> position != null;

    public static Predicate<Integer> checkRoverYearValidity = year -> year != null && year < 1957 && year > LocalDate.now().getYear();

    public static Predicate<Integer> checkCoordinateValidity = coordinateToSet -> coordinateToSet > 0;
}
