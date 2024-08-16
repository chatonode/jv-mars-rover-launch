package exception.business;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InvalidClassParameterExceptionTest {

    @Test
    public void testMessageConstructor() {
        InvalidClassParameterException exception = new InvalidClassParameterException("Error occurred");
        assertEquals("Error occurred", exception.getMessage(), "Exception message should be set correctly");
    }

    @Test
    public void testSingleParameterLogging() {
        // Set up streams to capture System.err output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(baos));

        // Create and throw the exception
        InvalidClassParameterException exception = new InvalidClassParameterException("Error occurred", "InvalidParam");

        // Restore original System.err
        System.setErr(originalErr);

        // Capture the output
        String output = baos.toString();
        assertTrue(output.contains("Invalid Class Parameter:"), "Output should contain 'Invalid Class Parameter:'");
        assertTrue(output.contains("• InvalidParam"), "Output should contain the invalid parameter 'InvalidParam'");
    }

    @Test
    public void testMultipleParametersLogging() {
        // Set up streams to capture System.err output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalErr = System.err;
        System.setErr(new PrintStream(baos));

        // Create and throw the exception
        InvalidClassParameterException exception = new InvalidClassParameterException(
                "Error occurred",
                Arrays.asList("InvalidParam1", "InvalidParam2")
        );

        // Restore original System.err
        System.setErr(originalErr);

        // Capture the output
        String output = baos.toString();
        assertTrue(output.contains("Invalid Class Parameter List (2):"), "Output should contain 'Invalid Class Parameter List (2):'");
        assertTrue(output.contains("• InvalidParam1"), "Output should contain the invalid parameter 'InvalidParam1'");
        assertTrue(output.contains("• InvalidParam2"), "Output should contain the invalid parameter 'InvalidParam2'");
    }
}
