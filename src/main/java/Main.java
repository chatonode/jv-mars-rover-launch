import business.movable.explorer.Explorer;
import business.movable.explorer.Rover;
import business.environment.Plateau;
import business.orchestrator.MissionControl;
import common.enums.CompassDirection;
import business.environment.Position;
import input.parser.InstructionParser;

public class Main {
    public static void main(String[] args) {
        // InstructionParser Test
//        InstructionParser instructionParser = new InstructionParser();
//        System.out.println(instructionParser.parse("LLM"));


        // Plateau Test
//        Plateau plateau = new Plateau(1, 1);

        // Rover Test
//        Rover rover = new Rover("Curiosity", new Position(-1, 2, CompassDirection.N), "NASA", 2011);
//        System.out.println(rover.getCurrentPosition().getX());
//        System.out.println(rover.getCurrentPosition().getY());
//        System.out.println(rover.getCurrentPosition().getFacingDirection());

        MissionControl missionControl = new MissionControl("cevdet", 6, 12);

        Position destination = missionControl.createDestinationPosition(2, 3, CompassDirection.N);
        Rover curiosity = new Rover("Curiosity", destination, "NASA", 2011);
        missionControl.addRoverToBeLaunched(curiosity);
//        missionControl.launchRover(rover);

//        missionControl.moveRover(rover.getId());
//        missionControl.rotateRover(rover.getId());
    }
}
