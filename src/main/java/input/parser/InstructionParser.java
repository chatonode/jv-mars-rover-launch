package input.parser;

import common.enums.Instruction;
import exception.input.ParsingException;

import java.util.Arrays;
import java.util.List;

public class InstructionParser extends InputParser<String, List<Instruction>> {

    @Override
    public List<Instruction> parse(String values) {
        if (values == null || values.isBlank()) throw new ParsingException("Invalid instructions!");

        List<Instruction> instructions = Arrays.stream(values.split("")).map(individualInstructionStr -> {
            return switch (individualInstructionStr) {
                case "L" -> Instruction.L;
                case "R" -> Instruction.R;
                case "M" -> Instruction.M;
                default -> throw new ParsingException("Invalid instruction: " + individualInstructionStr);
            };
        }).toList();

        return instructions;
    }
}
