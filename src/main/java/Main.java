import business.invader.Rover;
import business.plateau.Plateau;
import common.CompassDirection;
import common.Position;
import input.parser.InstructionParser;
import business.plateau.PlateauSize;

public class Main {
    public static void main(String[] args) {
        // InstructionParser Test
        InstructionParser instructionParser = new InstructionParser();
        System.out.println(instructionParser.parse("LLM"));


        // Plateau Test
        Plateau plateau = new Plateau(-1, -1);

        // Rover Test
        Rover rover = new Rover("Curiosity", new Position(2, 2, CompassDirection.N), "NASA", 2011);
        System.out.println(rover.getCurrentPosition().getX());
        System.out.println(rover.getCurrentPosition().getY());
        System.out.println(rover.getCurrentPosition().getFacingDirection());
    }
}
