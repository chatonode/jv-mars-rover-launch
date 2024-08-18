package business.orchestrator;

import static org.junit.jupiter.api.Assertions.*;

import exception.business.PlateauAlreadyInitializedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import exception.business.InvalidClassParameterException;

class MissionControlTest {

    MissionControl validMissionControl;

    @Nested
    public class TestMissionControlInitialization {
        @Test
        @DisplayName("Should initialize MissionControl correctly with valid username")
        public void testValidUsernameInitialization() {
            String validUsername = "test";

            MissionControl missionControl = new MissionControl(validUsername);

            assertNotNull(missionControl.getUsername(), "Username should not be null");
            assertEquals(validUsername, missionControl.getUsername(), "Username should match the input value");
            assertNull(missionControl.getPlateau(), "Plateau should be null");
            assertNotNull(missionControl.getRoversOnEarth(), "Rovers on Earth should not be null");
            assertEquals(0, missionControl.getRoversOnEarth().size(), "Rovers on Earth should not contain any items");
//        assertNotNull(missionControl.getPlateau(), "Plateau should not be null");
//        assertEquals(maxX, missionControl.getPlateau().getMaxPlateauX(), "Plateau maximum X should match the input value");
//        assertEquals(maxY, missionControl.getPlateau().getMaxPlateauY(), "Plateau maximum Y should match the input value");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for null username")
        public void testNullUsername() {
            assertThrows(InvalidClassParameterException.class, () -> new MissionControl(null),
                    "Should throw InvalidClassParameterException for null username");
        }


        @Test
        @DisplayName("Should throw InvalidClassParameterException for empty username")
        public void testEmptyUsername() {
            assertThrows(InvalidClassParameterException.class, () -> new MissionControl(""),
                    "Should throw InvalidClassParameterException for empty username");
        }

        @Test
        @DisplayName("Should throw InvalidClassParameterException for blank username")
        public void testBlankUsername() {
            assertThrows(InvalidClassParameterException.class, () -> new MissionControl("    "),
                    "Should throw InvalidClassParameterException for blank username");
        }

        @Test
        @DisplayName("Should initialize MissionControl correctly with valid username having spaces")
        public void testValidUsernameWithSpaces() {
            String validUsername = "Commander Shepard";
//            int maxX = 20;
//            int maxY = 20;
            MissionControl missionControl = new MissionControl(validUsername);

            assertNotNull(missionControl.getUsername(), "Username should not be null");
            assertEquals(validUsername, missionControl.getUsername(), "Username should match the input value");

//            assertEquals(maxX, missionControl.getPlateau().getMaxPlateauX(), "Plateau maximum X should match the input value");
//            assertEquals(maxY, missionControl.getPlateau().getMaxPlateauY(), "Plateau maximum Y should match the input value");
        }

        @Test
        @DisplayName("Should initialize MissionControl correctly with wrapping spaces trimmed")
        public void testValidUsernameWithWrappingSpaces() {
            String validUsername = "    Commander Shepard      ";
//            int maxX = 20;
//            int maxY = 20;
            MissionControl missionControl = new MissionControl(validUsername);

            assertNotNull(missionControl.getUsername(), "Username should not be null");
            assertNotEquals(validUsername, missionControl.getUsername(), "Username should not match the input value");
            assertEquals(validUsername.trim(), missionControl.getUsername(), "Username should match the trimmed input value");
//            assertEquals(maxX, missionControl.getPlateau().getMaxPlateauX(), "Plateau maximum X should match the input value");
//            assertEquals(maxY, missionControl.getPlateau().getMaxPlateauY(), "Plateau maximum Y should match the input value");
        }
    }

    @Nested
    public class TestRoversOnEarth {
        @BeforeEach
        public void setUp() {
            validMissionControl = new MissionControl("test");
        }

        @Test
        @DisplayName("Should not have null list of Rovers on Earth, after MissionControl initialization")
        public void testRoversOnEarthAreNotNullInitially() {
            assertNotNull(validMissionControl.getRoversOnEarth());
        }

        @Test
        @DisplayName("Should not include any rovers in the list of Rovers on Earth, after MissionControl initialization")
        public void testRoversOnEarthListDoNotContainRovers() {
            assertEquals(0, validMissionControl.getRoversOnEarth().size());
        }


    }

    @Nested
    public class TestPlateauInitialization {
        @BeforeEach
        public void setUp() {
            validMissionControl = new MissionControl("test");
        }

        @Test
        @DisplayName("Should have null Plateau after MissionControl initialization")
        public void testPlateauIsNullInitially() {
            assertNull(validMissionControl.getPlateau());
        }


        @Test
        @DisplayName("Should initialize Plateau correctly")
        public void testPlateauInitialization() {
            int maxX = 10;
            int maxY = 12;

            validMissionControl.initializePlateau(maxX, maxY);

            assertNotNull(validMissionControl.getPlateau());
            assertEquals(0, validMissionControl.getPlateau().getMinPlateauX());
            assertEquals(10, validMissionControl.getPlateau().getMaxPlateauX());
            assertEquals(0, validMissionControl.getPlateau().getMinPlateauY());
            assertEquals(12, validMissionControl.getPlateau().getMaxPlateauY());
        }

        @Test
        @DisplayName("Should throw PlateauAlreadyInitializedException after first initialization")
        public void testBlankUsername() {
            int maxX = 15;
            int maxY = 8;
            validMissionControl.initializePlateau(maxX, maxY);

            assertThrows(PlateauAlreadyInitializedException.class, () -> validMissionControl.initializePlateau(maxX, maxY),
                    "Should throw PlateauAlreadyInitializedException with same boundaries");
            assertThrows(PlateauAlreadyInitializedException.class, () -> validMissionControl.initializePlateau(7, 13),
                    "Should throw PlateauAlreadyInitializedException with different boundaries");
        }
    }

//    @Test
//    @DisplayName()
}