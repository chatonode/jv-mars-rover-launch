package controller.movable.explorer;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import controller.orchestrator.MissionControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import controller.environment.Position;
import exception.business.InvalidClassParameterException;
import common.enums.CompassDirection;

public class RoverTest {

    private MissionControl missionControl;
    private Rover validRover;
    private Position validInitialPosition;

    @BeforeEach
    public void setUp() {
        missionControl = new MissionControl("test");
        missionControl.initializePlateau(11, 12);
        Position initialPosition = missionControl.createDestinationPosition(5, 5, CompassDirection.N);
        validRover = new Rover("Rover1", initialPosition, "NASA", 2020);
        validInitialPosition = initialPosition;
    }


    @Nested
    public class TestRoverInitialization {
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

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid producedBy")
        public void testInvalidProducedBy() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Rover("Rover1", validInitialPosition, "", 2020));
            assertEquals("Input is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> new Rover("Rover1", validInitialPosition, null, 2020));
            assertEquals("Input is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid producedYear")
        public void testInvalidProducedYear() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Rover("Rover1", validInitialPosition, "NASA", 1950));
            assertEquals("Input is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> new Rover("Rover1", validInitialPosition, "NASA", LocalDate.now().getYear() + 1));
            assertEquals("Input is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> new Rover("Rover1", validInitialPosition, "NASA", null));
            assertEquals("Input is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid name")
        public void testInvalidName() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Rover("", validInitialPosition, "NASA", 2020));
            assertEquals("Input is invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> new Rover(null, validInitialPosition, "NASA", 2020));
            assertEquals("Input is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for invalid initialPosition")
        public void testInvalidInitialPosition() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Rover("Rover1", null, "NASA", 2020));
            assertEquals("Input is invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for multiple invalid inherited parameters")
        public void testMultipleInvalidInheritedParameters() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Rover("", null, "NASA", 1968));
            assertEquals("Inputs are invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> new Rover(null, null, null, LocalDate.now().getYear() + 1));
            assertEquals("Inputs are invalid!", exception.getMessage(), "Exception message should match");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for multiple invalid self parameters")
        public void testMultipleInvalidSelfParameters() {
            Exception exception = assertThrows(InvalidClassParameterException.class, () -> new Rover("Rover1", validInitialPosition, "", 1950));
            assertEquals("Inputs are invalid!", exception.getMessage(), "Exception message should match");

            exception = assertThrows(InvalidClassParameterException.class, () -> new Rover(null, null, null, LocalDate.now().getYear() + 1));
            assertEquals("Inputs are invalid!", exception.getMessage(), "Exception message should match");
        }
    }

    @Nested
    public class TestRoverMovement {
        @Test
        @DisplayName("Should move North and update currentPosition correctly from various initial positions")
        public void testMoveNorth() {
            List<Position> positions = List.of(
                    missionControl.createDestinationPosition(0, 0, CompassDirection.N),
                    missionControl.createDestinationPosition(5, 5, CompassDirection.N),
                    missionControl.createDestinationPosition(10, 10, CompassDirection.N)
            );


            for (Position position : positions) {
                Position prevPosition = missionControl.createDestinationPosition(position.getX(), position.getY(), position.getFacingDirection());
                Rover rover = new Rover("Rover1", position, "NASA", 2020);
                rover.move();

                Position updatedPosition = rover.getCurrentPosition();
                assertEquals(prevPosition.getX(), updatedPosition.getX(), "X position should remain unchanged");
                assertEquals(prevPosition.getY() + 1, updatedPosition.getY(), "Y position should increase by 1");
                assertEquals(prevPosition.getFacingDirection(), updatedPosition.getFacingDirection(), "Facing direction should remain North");
            }
        }

        @Test
        @DisplayName("Should move East and update currentPosition correctly from various initial positions")
        public void testMoveEast() {
            List<Position> positions = List.of(
                    missionControl.createDestinationPosition(0, 0, CompassDirection.E),
                    missionControl.createDestinationPosition(5, 5, CompassDirection.E),
                    missionControl.createDestinationPosition(10, 10, CompassDirection.E)
            );

            for (Position position : positions) {
                Position prevPosition = missionControl.createDestinationPosition(position.getX(), position.getY(), position.getFacingDirection());
                Rover rover = new Rover("Rover1", position, "NASA", 2020);
                rover.move();

                Position updatedPosition = rover.getCurrentPosition();
                assertEquals(prevPosition.getX() + 1, updatedPosition.getX(), "X position should increase by 1");
                assertEquals(prevPosition.getY(), updatedPosition.getY(), "Y position should remain unchanged");
                assertEquals(prevPosition.getFacingDirection(), updatedPosition.getFacingDirection(), "Facing direction should remain East");
            }
        }

        @Test
        @DisplayName("Should move South and update currentPosition correctly from various initial positions")
        public void testMoveSouth() {
            List<Position> positions = List.of(
                    missionControl.createDestinationPosition(0, 0, CompassDirection.S),
                    missionControl.createDestinationPosition(5, 5, CompassDirection.S),
                    missionControl.createDestinationPosition(10, 10, CompassDirection.S)
            );

            for (Position position : positions) {
                Position prevPosition = missionControl.createDestinationPosition(position.getX(), position.getY(), position.getFacingDirection());
                Rover rover = new Rover("Rover1", position, "NASA", 2020);
                rover.move();

                Position updatedPosition = rover.getCurrentPosition();
                assertEquals(prevPosition.getX(), updatedPosition.getX(), "X position should remain unchanged");
                assertEquals(prevPosition.getY() - 1, updatedPosition.getY(), "Y position should decrease by 1");
                assertEquals(prevPosition.getFacingDirection(), updatedPosition.getFacingDirection(), "Facing direction should remain South");
            }
        }

        @Test
        @DisplayName("Should move West and update currentPosition correctly from various initial positions")
        public void testMoveWest() {
            List<Position> positions = List.of(
                    missionControl.createDestinationPosition(0, 0, CompassDirection.W),
                    missionControl.createDestinationPosition(5, 5, CompassDirection.W),
                    missionControl.createDestinationPosition(10, 10, CompassDirection.W)
            );

            for (Position position : positions) {
                Position prevPosition = missionControl.createDestinationPosition(position.getX(), position.getY(), position.getFacingDirection());
                Rover rover = new Rover("Rover1", position, "NASA", 2020);
                rover.move();

                Position updatedPosition = rover.getCurrentPosition();
                assertEquals(prevPosition.getX() - 1, updatedPosition.getX(), "X position should decrease by 1");
                assertEquals(prevPosition.getY(), updatedPosition.getY(), "Y position should remain unchanged");
                assertEquals(prevPosition.getFacingDirection(), updatedPosition.getFacingDirection(), "Facing direction should remain West");
            }
        }


    }
}
