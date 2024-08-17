package input.parser;

import common.enums.Instruction;
import exception.input.ParsingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructionParserTest {

    InstructionParser instructionParser;

    @BeforeEach
    void setUp() {
        instructionParser = new InstructionParser();
    }

    @Test
    @DisplayName("throws ParsingException with given null instructions")
    void testNullInstructions() {
        String nullInstructions = null;
        assertThrows(ParsingException.class, () -> instructionParser.parse(nullInstructions));
    }

    @Test
    @DisplayName("throws ParsingException with given empty instructions")
    void testEmptyInstructions() {
        String emptyInstructions = "";
        assertThrows(ParsingException.class, () -> instructionParser.parse(emptyInstructions));
    }

    @Test
    @DisplayName("throws ParsingException with given blank instructions")
    void testBlankInstructions() {
        String blankInstructions = "       ";
        assertThrows(ParsingException.class, () -> instructionParser.parse(blankInstructions));
    }

    @Test
    @DisplayName("throws ParsingException with given one invalid instruction")
    void testOneInvalidInstruction() {
        String nonExistingOne = "Y";
        String nonExistingTwo = "Ğ";
        String punct = ".";
        String specialChar = "\n";
        String digit = "1";
        String lowercase = "x";
        String lowercaseOfValid = "l";

        assertThrows(ParsingException.class, () -> instructionParser.parse(nonExistingOne));
        assertThrows(ParsingException.class, () -> instructionParser.parse(nonExistingTwo));
        assertThrows(ParsingException.class, () -> instructionParser.parse(punct));
        assertThrows(ParsingException.class, () -> instructionParser.parse(specialChar));
        assertThrows(ParsingException.class, () -> instructionParser.parse(digit));
        assertThrows(ParsingException.class, () -> instructionParser.parse(lowercase));
        assertThrows(ParsingException.class, () -> instructionParser.parse(lowercaseOfValid));
    }

    @Test
    @DisplayName("throws ParsingException with given instructions that include one invalid")
    void testOneInvalidWithMultipleInstruction() {
        String startingWithNonExisting = "YLLM";
        String nonExistingInBetween = "LRMMMĞM";
        String endingWithNonExisting = "LLMO";

        String startingWithPunct = ":LLM";
        String punctInBetween = "MRRRR*L";
        String endingWithPunct = "LLRRMMRRL!";

        String startingWithSpecialChar = "\sRMLRML";
        String specialCharInBetween = "RM\nLRML";
        String endingWithSpecialChar = "RMLRML\\";

        String startingWithDigit = "1RMLRMMM";
        String digitInBetween = "RML1RMMM";
        String endingWithDigit = "MMRMLRMMM1";

        String startingWithNonExistingLowercase = "xMMMM";
        String nonExistingLowercaseInBetween = "RRRLMwRLLL";
        String endingWithNonExistingLowercase = "RMLRMLLLu";

        String startingWithLowercaseOfExisting = "lLLLL";
        String lowercaseOfExistingInBetween = "MMMRrRLRL";
        String endingWithLowercaseOfExisting = "RRRMMRm";

        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithNonExisting));
        assertThrows(ParsingException.class, () -> instructionParser.parse(nonExistingInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithNonExisting));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithPunct));
        assertThrows(ParsingException.class, () -> instructionParser.parse(punctInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithPunct));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithSpecialChar));
        assertThrows(ParsingException.class, () -> instructionParser.parse(specialCharInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithSpecialChar));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithDigit));
        assertThrows(ParsingException.class, () -> instructionParser.parse(digitInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithDigit));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithNonExistingLowercase));
        assertThrows(ParsingException.class, () -> instructionParser.parse(nonExistingLowercaseInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithNonExistingLowercase));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithLowercaseOfExisting));
        assertThrows(ParsingException.class, () -> instructionParser.parse(lowercaseOfExistingInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithLowercaseOfExisting));
    }

    @Test
    @DisplayName("throws ParsingException with given instructions that include multiple invalids")
    void testMultipleInvalidsWithMultipleInstruction() {
        String startingWithNonExistings = "YWLLM";
        String nonExistingsInBetween = "LRMJMMĞM";
        String endingWithNonExistings = "LLMPO";
        String nonExistingsMixed = "LTMRLHHHTMLZRMV";

        String startingWithPuncts = "?:RLM";
        String punctsInBetween = "MRRRR+&*L";
        String endingWithPuncts = "LLRRMMRRL!";
        String punctsMixed = "MRR+&RR*L";

        String startingWithSpecialChars = "\n\sRMLRML";
        String specialCharsInBetween = "RMLRM\s\n\\L";
        String endingWithSpecialChars = "RMLRML\\\s";
        String specialCharsMixed = "RL\nLRM\s\\L";

        String startingWithDigits = "18RMLRMMM";
        String digitsInBetween = "RML16RMMM";
        String endingWithDigits = "MMRMLRMMM1";
        String digitsMixed = "RML16RM93MM";

        String startingWithNonExistingLowercases = "kcMMMM";
        String nonExistingLowercasesInBetween = "RRpqtRLMRLLL";
        String endingWithNonExistingLowercases = "RMLRMLLLügx";
        String nonExistingLowercasesMixed = "RRqRLMpıRLnoLL";

        String startingWithLowercasesOfExisting = "lmLRL";
        String lowercasesOfExistingInBetween = "LRMRrmmlRLRL";
        String endingWithLowercasesOfExisting = "LRRRRMMmrmm";
        String lowercasesOfExistingMixed = "LRmMRrmmlRLmRLl";

        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithNonExistings));
        assertThrows(ParsingException.class, () -> instructionParser.parse(nonExistingsInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithNonExistings));
        assertThrows(ParsingException.class, () -> instructionParser.parse(nonExistingsMixed));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithPuncts));
        assertThrows(ParsingException.class, () -> instructionParser.parse(punctsInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithPuncts));
        assertThrows(ParsingException.class, () -> instructionParser.parse(punctsMixed));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithSpecialChars));
        assertThrows(ParsingException.class, () -> instructionParser.parse(specialCharsInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithSpecialChars));
        assertThrows(ParsingException.class, () -> instructionParser.parse(specialCharsMixed));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithDigits));
        assertThrows(ParsingException.class, () -> instructionParser.parse(digitsInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithDigits));
        assertThrows(ParsingException.class, () -> instructionParser.parse(digitsMixed));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithNonExistingLowercases));
        assertThrows(ParsingException.class, () -> instructionParser.parse(nonExistingLowercasesInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithNonExistingLowercases));
        assertThrows(ParsingException.class, () -> instructionParser.parse(nonExistingLowercasesMixed));
        assertThrows(ParsingException.class, () -> instructionParser.parse(startingWithLowercasesOfExisting));
        assertThrows(ParsingException.class, () -> instructionParser.parse(lowercasesOfExistingInBetween));
        assertThrows(ParsingException.class, () -> instructionParser.parse(endingWithLowercasesOfExisting));
        assertThrows(ParsingException.class, () -> instructionParser.parse(lowercasesOfExistingMixed));
    }

    @Test
    @DisplayName("returns list of instructions with one item, when one valid instruction is given")
    void testOneValid() {
        String validLeft = "L";
        String validRight = "R";
        String validMove = "M";

        List<Instruction> expectedLeft = instructionParser.parse(validLeft);
        List<Instruction> expectedRight = instructionParser.parse(validRight);
        List<Instruction> expectedMove = instructionParser.parse(validMove);


        assertNotEquals(null, expectedLeft);
        assertNotEquals(0, expectedLeft.size());
        assertEquals(1, expectedLeft.size());
        assertEquals(Instruction.L, expectedLeft.getFirst());

        assertNotEquals(null, expectedRight);
        assertNotEquals(0, expectedRight.size());
        assertEquals(1, expectedRight.size());
        assertEquals(Instruction.R, expectedRight.getFirst());

        assertNotEquals(null, expectedMove);
        assertNotEquals(0, expectedMove.size());
        assertEquals(1, expectedMove.size());
        assertEquals(Instruction.M, expectedMove.getFirst());
    }

    @Test
    @DisplayName("returns list of instructions with multiple items, when multiple valid instructions are given")
    void testMultipleValids() {
        String leftRight = "LR";
        String rightLeft = "RL";
        String rightRight = "RR";
        String rightMove = "RM";
        String moveLeft = "ML";

        String allRight = "RRRRRRRRRRRRRR";
        String allMove = "MMMMMMMMMMMM";

        String mixedOne = "LRMLMRLMRL";
        String mixedTwo = "MRLRMRMRMMM";
        String mixedThree = "MMMMMRRRRLLLL";

        List<Instruction> expectedLeftRight = instructionParser.parse(leftRight);
        List<Instruction> expectedRightLeft = instructionParser.parse(rightLeft);
        List<Instruction> expectedRightRight = instructionParser.parse(rightRight);
        List<Instruction> expectedRightMove = instructionParser.parse(rightMove);
        List<Instruction> expectedMoveLeft = instructionParser.parse(moveLeft);
        List<Instruction> expectedAllRight = instructionParser.parse(allRight);
        List<Instruction> expectedAllMove = instructionParser.parse(allMove);
        List<Instruction> expectedMixedOne = instructionParser.parse(mixedOne);
        List<Instruction> expectedMixedTwo = instructionParser.parse(mixedTwo);
        List<Instruction> expectedMixedThree = instructionParser.parse(mixedThree);

        assertNotEquals(null, expectedLeftRight);
        assertEquals(leftRight.length(), expectedLeftRight.size());

        assertNotEquals(null, expectedRightLeft);
        assertEquals(rightLeft.length(), expectedRightLeft.size());

        assertNotEquals(null, expectedRightRight);
        assertEquals(rightRight.length(), expectedRightRight.size());

        assertNotEquals(null, expectedRightMove);
        assertEquals(rightMove.length(), expectedRightMove.size());

        assertNotEquals(null, expectedMoveLeft);
        assertEquals(moveLeft.length(), expectedMoveLeft.size());

        assertNotEquals(null, expectedAllRight);
        assertEquals(allRight.length(), expectedAllRight.size());

        assertNotEquals(null, expectedAllMove);
        assertEquals(allMove.length(), expectedAllMove.size());

        assertNotEquals(null, expectedMixedOne);
        assertEquals(mixedOne.length(), expectedMixedOne.size());

        assertNotEquals(null, expectedMixedTwo);
        assertEquals(mixedTwo.length(), expectedMixedTwo.size());

        assertNotEquals(null, expectedMixedThree);
        assertEquals(mixedThree.length(), expectedMixedThree.size());
    }
}