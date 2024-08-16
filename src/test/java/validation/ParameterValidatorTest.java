package validation;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import exception.business.InvalidClassParameterException;

public class ParameterValidatorTest {

    @Test
    public void testSingleInvalidParameter() {
        // Arrange
        ParamIsValidMap invalidParams = new ParamIsValidMap();
        invalidParams.put("param1", false);

        // Capture System.err output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Act
        InvalidClassParameterException thrownException = assertThrows(InvalidClassParameterException.class, () -> {
            ParameterValidator.validateParams(invalidParams);
        });

        // Verify exception message
        assertEquals("Class parameter is invalid!", thrownException.getMessage());

        // Verify System.err output
        String expectedErrOutput = "Invalid Class Parameter:\n• param1\n";
        assertEquals(expectedErrOutput, errContent.toString());
    }

    @Test
    public void testMultipleInvalidParameters() {
        // Arrange
        ParamIsValidMap invalidParams = new ParamIsValidMap();
        invalidParams.put("param1", false);
        invalidParams.put("param2", false);

        // Capture System.err output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Act
        InvalidClassParameterException thrownException = assertThrows(InvalidClassParameterException.class, () -> {
            ParameterValidator.validateParams(invalidParams);
        });

        // Verify exception message
        assertEquals("Class parameters are invalid!", thrownException.getMessage());

        // Verify System.err output
        String expectedErrOutput = "Invalid Class Parameter List (2):\n" +
                "• param1\n" +
                "• param2\n";
        assertEquals(expectedErrOutput, errContent.toString());
    }

    @Test
    public void testNoInvalidParameters() {
        ParamIsValidMap validParams = new ParamIsValidMap();
        validParams.put("param1", true);
        validParams.put("param2", true);

        assertDoesNotThrow(() -> ParameterValidator.validateParams(validParams));
    }

    @AfterEach
    public void tearDown() {
        // Reset System.err
        System.setErr(System.err);
    }
}
