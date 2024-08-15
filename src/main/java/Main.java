import input.parser.InstructionParser;
import business.plateau.PlateauSize;

public class Main {
    public static void main(String[] args) {
        // Plateau Test
        PlateauSize plateauSize = new PlateauSize(5, 5);
        System.out.print(plateauSize.getXCoordinates().getStart() + "-");
        System.out.print(plateauSize.getYCoordinates().getStart() + "\n");
        System.out.print(plateauSize.getXCoordinates().getEnd() + "-");
        System.out.print(plateauSize.getYCoordinates().getEnd() + "\n");

        // InstructionParser Test
        InstructionParser instructionParser = new InstructionParser();
        System.out.println(instructionParser.parse("LLM"));
    }
}
