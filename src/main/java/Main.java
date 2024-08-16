import business.movable.explorer.Rover;
import business.environment.Plateau;
import common.enums.CompassDirection;
import business.environment.Position;
import input.parser.InstructionParser;

public class Main {
    public static void main(String[] args) {
        // InstructionParser Test
        InstructionParser instructionParser = new InstructionParser();
        System.out.println(instructionParser.parse("LLM"));


        // Plateau Test
        Plateau plateau = new Plateau(1, 1);

        // Rover Test
        Rover rover = new Rover("Curiosity", new Position(-1, 2, CompassDirection.N), "NASA", 2011);
        System.out.println(rover.getCurrentPosition().getX());
        System.out.println(rover.getCurrentPosition().getY());
        System.out.println(rover.getCurrentPosition().getFacingDirection());
    }
}
