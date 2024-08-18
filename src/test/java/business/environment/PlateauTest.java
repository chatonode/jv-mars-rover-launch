package business.environment;

import static org.junit.jupiter.api.Assertions.*;

import business.orchestrator.MissionControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import exception.business.InvalidClassParameterException;

public class PlateauTest {

    private Plateau validPlateau;

    @BeforeEach
    public void setUp() {
        validPlateau = new Plateau(10, 15);
    }

    @Nested
    public class TestPlateauInitialization {
        @Test
        @DisplayName("Should initialize Plateau correctly with valid coordinates")
        public void testValidPlateauInitialization() {
            PlateauSize size = new PlateauSize(validPlateau.getMaxPlateauX(), validPlateau.getMaxPlateauY());
            assertNotNull(size, "PlateauSize should not be null");
            assertEquals(10, size.getMaximumX(), "Maximum X coordinate should be 10");
            assertEquals(15, size.getMaximumY(), "Maximum Y coordinate should be 15");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid maximumX coordinate")
        public void testInvalidMaximumXCoordinate() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Plateau(-1, 15));
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid maximumY coordinate")
        public void testInvalidMaximumYCoordinate() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Plateau(10, -1));
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for both invalid coordinates")
        public void testInvalidBothCoordinates() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Plateau(-1, -1));
            assertEquals("Class parameters are invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for maximumX or maximumY as zero")
        public void testZeroCoordinates() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Plateau(0, 15));
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> new Plateau(10, 0));
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> new Plateau(0, 0));
            assertEquals("Class parameters are invalid!", exception.getMessage(), "Exception message should match");
        }

    }

    @Nested
    public class TestRoversOnPlateau {
        @Test
        @DisplayName("Should not have null list of Rovers on the Plateau, after Plateau initialization")
        public void testRoversOnEarthAreNotNullInitially() {
            assertNotNull(validPlateau.getRovers());
        }

        @Test
        @DisplayName("Should not include any rovers in the list of Rovers on Earth, after MissionControl initialization")
        public void testRoversOnEarthListDoNotContainRovers() {
            assertEquals(0, validPlateau.getRovers().size());
        }


    }
}
