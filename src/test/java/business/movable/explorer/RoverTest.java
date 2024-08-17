package business.movable.explorer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import business.environment.Position;
import exception.business.InvalidClassParameterException;
import common.enums.CompassDirection;


public class RoverTest {

    private Rover validRover;

    @BeforeEach
    public void setUp() {
        Position initialPosition = new Position(5, 5, CompassDirection.N);
        validRover = new Rover("Rover1", initialPosition, "NASA", 2020);
    }

    @Test
    @DisplayName("Should initialize Rover correctly with valid parameters")
    public void testValidRoverInitialization() {
        Position initialPosition = validRover.getInitialPosition();
        assertNotNull(initialPosition, "Initial position should not be null");
        assertEquals(5, initialPosition.getX(), "Initial X position should be 5");
        assertEquals(5, initialPosition.getY(), "Initial Y position should be 5");
        assertEquals(CompassDirection.N, initialPosition.getFacingDirection(), "Initial facing direction should be North");

        assertEquals("Rover1", validRover.getName(), "Name should be Rover1");
        assertEquals("NASA", validRover.getProducedBy(), "ProducedBy should be NASA");
        assertEquals(2020, validRover.getProducedYear(), "ProducedYear should be 2020");
    }

    @Nested
    public class TestInvalidParameters {

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid producedBy")
        public void testInvalidProducedBy() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover("Rover1", new Position(5, 5, CompassDirection.N), "", 2020);
            });
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover("Rover1", new Position(5, 5, CompassDirection.N), null, 2020);
            });
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid producedYear")
        public void testInvalidProducedYear() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover("Rover1", new Position(5, 5, CompassDirection.N), "NASA", 1950);
            });
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover("Rover1", new Position(5, 5, CompassDirection.N), "NASA", LocalDate.now().getYear() + 1);
            });
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover("Rover1", new Position(5, 5, CompassDirection.N), "NASA", null);
            });
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid name")
        public void testInvalidName() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover("", new Position(5, 5, CompassDirection.N), "NASA", 2020);
            });
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover(null, new Position(5, 5, CompassDirection.N), "NASA", 2020);
            });
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid initialPosition")
        public void testInvalidInitialPosition() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover("Rover1", null, "NASA", 2020);
            });
            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for multiple invalid inherited parameters")
        public void testMultipleInvalidInheritedParameters() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover("", null, "NASA", 1968);
            });
            assertEquals("Class parameters are invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover(null, null, null, LocalDate.now().getYear() + 1);
            });
            assertEquals("Class parameters are invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for multiple invalid self parameters")
        public void testMultipleInvalidSelfParameters() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover("Rover1", new Position(5, 5, CompassDirection.N), "", 1950);
            });
            assertEquals("Class parameters are invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> {
                new Rover(null, null, null, LocalDate.now().getYear() + 1);
            });
            assertEquals("Class parameters are invalid!", exception.getMessage(), "Exception message should match");
        }
    }

    @Nested
    public class TestMove {
        @Test
        @DisplayName("Should successfully move to a valid nextPosition and update currentPosition")
        public void testValidMove() {
            Position newPosition = new Position(10, 10, CompassDirection.S);
            assertTrue(validRover.moveTo(newPosition), "Move method should return true for valid position");
            assertEquals(newPosition, validRover.getCurrentPosition(), "Current position should be updated to newPosition");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for null nextPosition")
        public void testInvalidMoveNullPosition() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> {
                validRover.moveTo(null);
            });

            assertEquals("Class parameter is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should retain currentPosition unchanged if move fails")
        public void testMoveFailureRetainsCurrentPosition() {
            Position initialPosition = validRover.getCurrentPosition();

            assertThrows(InvalidClassParameterException.class, () -> {
                validRover.moveTo(null);
            });
            assertEquals(initialPosition, validRover.getCurrentPosition(), "Current position should remain unchanged after failed move");
        }
    }
}
