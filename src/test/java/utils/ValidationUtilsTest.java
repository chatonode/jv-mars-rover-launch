package utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilsTest {

    @Test
    public void testCheckStringValidity() {
        assertTrue(ValidationUtils.checkStringValidity.test("Valid String"), "String should be valid");
        assertFalse(ValidationUtils.checkStringValidity.test(null), "Null string should be invalid");
        assertFalse(ValidationUtils.checkStringValidity.test(" "), "Blank string should be invalid");
    }

    @Test
    public void testCheckRoverYearValidity() {
        int currentYear = LocalDate.now().getYear();

        assertTrue(ValidationUtils.checkRoverYearValidity.test(1958), "Year 1958 should be valid");
        assertTrue(ValidationUtils.checkRoverYearValidity.test(currentYear - 1), "Current year minus one should be valid");
        assertFalse(ValidationUtils.checkRoverYearValidity.test(1957), "Year 1957 should be invalid");
        assertFalse(ValidationUtils.checkRoverYearValidity.test(currentYear + 1), "Future year should be invalid");
        assertFalse(ValidationUtils.checkRoverYearValidity.test(null), "Null year should be invalid");
    }

    @Test
    public void testCheckCoordinateValidity() {
        assertTrue(ValidationUtils.checkCoordinateValidity.test(1), "Coordinate 1 should be valid");
        assertFalse(ValidationUtils.checkCoordinateValidity.test(0), "Coordinate 0 should be invalid");
        assertFalse(ValidationUtils.checkCoordinateValidity.test(-1), "Negative coordinate should be invalid");
        assertFalse(ValidationUtils.checkCoordinateValidity.test(null), "Null coordinate should be invalid");
    }
}
