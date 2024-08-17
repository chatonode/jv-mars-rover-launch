import business.movable.explorer.Rover;
import business.orchestrator.MissionControl;
import common.enums.CompassDirection;
import business.orchestrator.Position;

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
        Position destination2 = missionControl.createDestinationPosition(3, 2, CompassDirection.N);


        missionControl.addRoverToBeLaunched("Curiosity", destination1, "NASA", 2011);// PROBLEM
        System.out.println(missionControl.getRoversOnEarth());
        missionControl.launchRovers();
        missionControl.addRoverToBeLaunched("Opportunity", destination2, "NASA", 2003);
//        missionControl.addRovers
        System.out.println("Rovers on Earth before Launch: " + missionControl.getRoversOnEarth());
        System.out.println("Rovers on Mars  before Launch: " + missionControl.getRoversOnMars());
        missionControl.launchRovers();
        System.out.println("Rovers on Earth after Launch: " + missionControl.getRoversOnEarth());
        System.out.println("Rovers on Mars  after Launch: " + missionControl.getRoversOnMars());


        missionControl.getRoversOnMars().forEach(rover -> System.out.println(rover.getInitialPosition()));
        missionControl.getRoversOnMars().forEach(rover -> System.out.println(rover.getCurrentPosition()));


//        missionControl.moveRover(rover.getId());
//        missionControl.rotateRover(rover.getId());
    }
}
