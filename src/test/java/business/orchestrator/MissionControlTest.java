package business.orchestrator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exception.business.InvalidClassParameterException;

class MissionControlTest {

    @Test
    @DisplayName("Should initialize MissionControl correctly with valid username")
    public void testValidUsernameInitialization() {
        String validUsername = "cevdet123";
        int maxX = 10;
        int maxY = 10;

        MissionControl missionControl = new MissionControl(validUsername, maxX, maxY);

        assertNotNull(missionControl.getUsername(), "Username should not be null");
        assertEquals(validUsername, missionControl.getUsername(), "Username should match the input value");
        assertNotNull(missionControl.getPlateau(), "Plateau should not be null");
        assertEquals(maxX, missionControl.getPlateau().getMaxPlateauX(), "Plateau maximum X should match the input value");
        assertEquals(maxY, missionControl.getPlateau().getMaxPlateauY(), "Plateau maximum Y should match the input value");
    }

    @Test
    @DisplayName("Should throw InvalidClassParameterException for null username")
    public void testNullUsername() {
        assertThrows(InvalidClassParameterException.class, () -> new MissionControl(null, 10, 10),
                "Should throw InvalidClassParameterException for null username");
    }


    @Test
    @DisplayName("Should throw InvalidClassParameterException for empty username")
    public void testEmptyUsername() {
        assertThrows(InvalidClassParameterException.class, () -> new MissionControl("", 10, 10),
                "Should throw InvalidClassParameterException for empty username");
    }

    @Test
    @DisplayName("Should throw InvalidClassParameterException for blank username")
    public void testBlankUsername() {
        assertThrows(InvalidClassParameterException.class, () -> new MissionControl("    ", 10, 10),
                "Should throw InvalidClassParameterException for blank username");
    }

    @Test
    @DisplayName("Should initialize MissionControl correctly with valid username having spaces")
    public void testValidUsernameWithSpaces() {
        String validUsername = "Commander Shepard";
        int maxX = 20;
        int maxY = 20;
        MissionControl missionControl = new MissionControl(validUsername, maxX, maxY);

        assertNotNull(missionControl.getUsername(), "Username should not be null");
        assertEquals(validUsername, missionControl.getUsername(), "Username should match the input value");
        assertNotNull(missionControl.getPlateau(), "Plateau should not be null");
        assertEquals(maxX, missionControl.getPlateau().getMaxPlateauX(), "Plateau maximum X should match the input value");
        assertEquals(maxY, missionControl.getPlateau().getMaxPlateauY(), "Plateau maximum Y should match the input value");
    }

//    @Test
//    @DisplayName()
}