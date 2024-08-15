package input.parser;

import common.Instruction;

import java.util.Arrays;
import java.util.List;

public class InstructionParser extends InputParser<String, List<Instruction>> {

    @Override
    public List<Instruction> parse(String values) {
        List<Instruction> instructions = Arrays.stream(values.split("")).map(individualInstructionStr -> {
            return switch (individualInstructionStr) {
                case "L" -> Instruction.L;
                case "R" -> Instruction.R;
                case "M" -> Instruction.M;
                default -> throw new ParsingError("Invalid instruction: " + individualInstructionStr);
            };
        }).toList();

        return instructions;
    }
}
