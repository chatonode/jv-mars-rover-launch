import business.movable.explorer.Rover;
import business.orchestrator.MissionControl;
import common.enums.CompassDirection;
import business.environment.Position;

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

        Position destination1 = missionControl.createDestinationPosition(2, 3, CompassDirection.N);
        Rover curiosity = new Rover("Curiosity", destination1, "NASA", 2011);
        missionControl.addRoverToBeLaunched(curiosity);
        Position destination2 = missionControl.createDestinationPosition(3, 2, CompassDirection.N);
        Rover opportunity = new Rover("Opportunity", destination2, "NASA", 2003);
        missionControl.addRoverToBeLaunched(opportunity);
//        missionControl.addRovers
        System.out.println("Rovers on Earth before Launch: " + missionControl.getRoversOnEarth());
        System.out.println("Rovers on Mars  before Launch: " + missionControl.getRoversOnMars());
        missionControl.launchRovers();
        System.out.println("Rovers on Earth after Launch: " + missionControl.getRoversOnEarth());
        System.out.println("Rovers on Mars  after Launch: " + missionControl.getRoversOnMars());


//        missionControl.moveRover(rover.getId());
//        missionControl.rotateRover(rover.getId());
    }
}
