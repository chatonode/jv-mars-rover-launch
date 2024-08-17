package utils;

import business.environment.Position;
import business.movable.explorer.Rover;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class ValidationUtils {

    public static final Predicate<String> checkStringValidity = str -> str != null && !str.isBlank();

    public static final Predicate<Position> checkPositionValidity = position -> position != null;

    public static final Predicate<Integer> checkRoverYearValidity = year -> (year != null && year > 1957 && year < LocalDate.now().getYear());

    public static final Predicate<Integer> checkCoordinateValidity = coordinate -> coordinate != null && coordinate > 0;

}
