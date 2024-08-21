package view;

import common.enums.CompassDirection;
import controller.environment.Position;
import controller.movable.explorer.Explorer;
import controller.orchestrator.MissionControl;
import exception.business.InvalidClassParameterException;
import exception.business.NoRoversToLaunchException;

import java.util.List;
import java.util.Scanner;

import static utils.OutputUtils.*;


public class OutputManager {
    private final Scanner input;
    boolean isTerminated;
    private MissionControl missionControl;
// ANSI escape code to reset the color

    private void test() {
        missionControl.addRoverToBeLaunched("asd", missionControl.createDestinationPosition(1, 3, CompassDirection.E), "asd", 1999);
        missionControl.addRoverToBeLaunched("bsd", missionControl.createDestinationPosition(3, 5, CompassDirection.E), "asd", 1999);
        missionControl.launchRovers();
    }

    public OutputManager() {
        input = new Scanner(System.in);
        isTerminated = false;
    }


    public void run() {
        initializeMissionControl();
    }


    private void initializeMissionControl() {
        sysOutBlueizedTextWithSplitter.accept("Welcome to Mission Control of NASA!");

        boolean isValidated = false;
        while (!this.isTerminated && !isValidated) {
            System.out.println("Can I get your " + redizeTextColor.apply("name") + ", please?: ");
            String username = this.input.nextLine();
            try {
                missionControl = new MissionControl(username);
                isValidated = true;
                sysOutBlueizedText.accept("Mission Control is initialized!");
                sysOutYellowizedText.accept("Preparing plateau signals...");
                sysOutSplitter.run();
                this.initializePlateau();
            } catch (InvalidClassParameterException e) {
//                sysOutRedizedSplittersAroundText.accept(e.getMessage());
            }
        }
    }

    private void initializePlateau() {
        System.out.println(blueizeTextColor.apply("Welcome on board, captain ") + greenizeTextColor.apply(missionControl.getUsername()) + blueizeTextColor.apply("! Nice to see you around!.."));
        System.out.println("Please grab some coffee to yourself and determine the " + redizeTextColor.apply("size") + " of the " + redizeTextColor.apply("plateau") + " on Mars you want to launch your rovers:");
        boolean isValidated = false;

        while (!isValidated) {
            System.out.println("\nSet the maximum " + redizeTextColor.apply("X") + " coordinate: ");
            String maxX = this.input.nextLine();
            System.out.println("\nNow, set the maximum " + redizeTextColor.apply("Y") + " coordinate: ");
            String maxY = this.input.nextLine();

            try {
                missionControl.initializePlateau(Integer.parseInt(maxX), Integer.parseInt(maxY));
                isValidated = true;
                sysOutYellowizedText.accept("Plateau is initialized!");
                sysOutBlueizedText.accept("Configuring stations on multiple planets...");
                sysOutSplitter.run();

                // TEST
                test();
                System.out.println("DUMMY ROVERS LAUNCHED!");
                // TEST END

                this.initializePlanetMenus();
            } catch (InvalidClassParameterException | NumberFormatException e) {
                if (e instanceof InvalidClassParameterException) {
//                    sysOutRedizedSplittersAroundText.accept(e.getMessage());
                } else if (e instanceof NumberFormatException) {
                    System.err.println("Plateau coordinates must be " + redizeTextColor.apply("numbers above 0"));
                }
            }
        }
    }

    private void initializePlanetMenus() {
        System.out.println(blueizeTextColor.apply("Well done, captain ") + greenizeTextColor.apply(missionControl.getUsername()) + blueizeTextColor.apply("! You managed to create your plateau!"));
        sysOutBlueizedText.accept("At this moment, we need to select the planet (station) to operate on there!");
        boolean isValidated = false;

        while (!isValidated || !this.isTerminated) {
            System.out.println("Which " + redizeTextColor.apply("target planet") + " station do you want to enter?");
            System.out.println(blueizeTextColor.apply("(1)- Earth"));
            System.out.println(yellowizeTextColor.apply("(2)- Mars"));
            printPlateauInfo();
            sysOutGrayizedText.accept("(0)- What Planet?.. Show me how to exit!");
            String menuOption = input.nextLine();

            switch (menuOption) {
                case "1":
                    initializeEarthMenu();
                    isValidated = true;
                    break;
                case "2":
                    initializeMarsMenu();
                    isValidated = true;
                    break;
                case "0":
                    terminateOperation();
                    isValidated = true;
                    break;
                default:
                    printSelectMenuItemsErrorMessage(3);
                    break;
            }

        }
    }

    private void initializeEarthMenu() {
//        boolean isOnEarth = false;
        boolean isValidated = false;
        while (!isValidated) {
            System.out.println("Select one of the " + redizeTextColor.apply("operation") + "s in our Earth station:");
            System.out.println(blueizeTextColor.apply("(1)") + "- Create a rover");
            System.out.println(blueizeTextColor.apply("(2)") + "- Get all the rovers on Earth");
            System.out.println(blueizeTextColor.apply("(3)") + "- Launch the rovers (currently " + blueizeTextColor.apply(String.valueOf(missionControl.getRoversOnEarth().size())) + ")");
            sysOutGrayizedText.accept("(0)- Back to Planet Menu");

            String menuOption = this.input.nextLine();
            switch (menuOption) {
                case "1":
                    earthMenuCreateRover();
                    isValidated = true;
                    break;
                case "2":
                    earthMenuListRovers();
                    isValidated = true;
                    break;
                case "3":
                    earthMenuLaunchRovers();
                    isValidated = true;
                    break;
                case "0":
                    isValidated = true;
//                    isOnEarth = true;
                    break;
                default:
                    printSelectMenuItemsErrorMessage(4);
                    break;
            }
        }
    }

    private void earthMenuCreateRover() {
        System.out.println("Create a" + blueizeTextColor.apply("rover") + "to launch:");
        // TODO: Check Position first with this.missionControl.isPositionFree + in boundaries check??
    }

    private void earthMenuListRovers() {
        // TODO: this.missionControl.getRoversOnEarth
    }

    private void earthMenuLaunchRovers() {

        boolean isValidated = false;
        while (!isValidated) {
            System.out.println("Are you sure you want to " + redizeTextColor.apply("launch ") + greenizeTextColor.apply(String.valueOf(missionControl.getRoversOnEarth().size())) + redizeTextColor.apply(" rover(s)") + "?");
            System.out.print(greenizeTextColor.apply("\ny") + "/" + redizeTextColor.apply("N") + ": ");
            String menuOption = this.input.nextLine();

            switch (menuOption.toLowerCase()) {
                case "y":
                    try {
                        missionControl.launchRovers();
                    } catch (NoRoversToLaunchException e) {
                        System.err.println(e.getMessage());
                        isValidated = true;
                        break;
                    }
                    isValidated = true;
                    sysOutBlueizedTextWithSplitter.accept("Launched rover(s) to the Mars plateau!");
                    break;
                case "n":
                    isValidated = true;
                    sysOutBlueizedText.accept("Launching operation is aborted!");
                    sysOutBlueizedTextWithSplitter.accept("Returning to Earth office...");
                    break;
                default:
                    System.err.println("type " + greenizeTextColor.apply("y") + " or " + redizeTextColor.apply("N") + " to continue");
                    break;
            }
        }
    }


    private void initializeMarsMenu() {
        System.out.println(yellowizeTextColor.apply("Oh, what an instant travel to the Mars, captain " + greenizeTextColor.apply(this.missionControl.getUsername())));
//        System.out.println(yellowizeTextColor.apply("    (currently ") + blueizeTextColor.apply(String.valueOf(missionControl.getRoversOnMars().size())) + "active rovers on a mission)");
        printPlateauInfo();
        System.out.println(yellowizeTextColor.apply("(1)") + "- See rovers on the map");
        System.out.println(yellowizeTextColor.apply("(2)") + "- Move a rover");
        System.out.println(yellowizeTextColor.apply("(3)") + "- Rotate a rover");
        System.out.println(yellowizeTextColor.apply("(4)") + "- List all rovers");
        System.out.println(yellowizeTextColor.apply("(5)") + "- List filtered rovers");
        sysOutGrayizedText.accept("(0)- Back to planet menu");
        boolean isValidated = false;

        while (!isValidated) {
            String menuOption = this.input.nextLine();
            switch (menuOption) {
                case "1":
                    // TODO
                    isValidated = true;
                    marsMenuSeeRoversMap();
                    break;
                case "2":
                    // TODO
                    isValidated = true;
                    break;
                case "3":
                    // TODO
                    isValidated = true;
                    break;
                case "4":
                    // TODO
                    isValidated = true;
                    break;
                case "5":
                    // TODO
                    isValidated = true;
                    break;
                case "0":
                    isValidated = true;
//                    isOnEarth = true;
                    break;
                default:
                    printSelectMenuItemsErrorMessage(6);
                    break;
            }
        }

    }

    private void marsMenuSeeRoversMap() {
//        [0:5] [1:5] ... [4:5]
//        [0:4] [1:4] ... [4:4]
//        [0:3] [1:3] ... [4:3]
//        [0:2] [1:2] ... [4:2]
//        [0:1] [1:1] ... [4:1]
//        [0:0] [1:0] ... [4:0]

//         __ __ __ __
//        |__|__|__|__|
//        |__|__|__|__|
//        |__|__|__|__|
//        |__|__|__|__|
//        |__|__|__|__|

        StringBuilder map = new StringBuilder();

        // Head lines
        for (int i = 0; i < missionControl.getPlateau().getMaxPlateauX() + 1; i++) {
            map.append(" __");
        }
        map.append("\n");

        // Rest of the Map
        for (int y = missionControl.getPlateau().getMaxPlateauY(); y > missionControl.getPlateau().getMinPlateauY() - 1; y--) {
            map.append("|");
            for (int x = missionControl.getPlateau().getMinPlateauX(); x < missionControl.getPlateau().getMaxPlateauX() + 1; x++) {
                map.append("__");
                if (x == missionControl.getPlateau().getMaxPlateauX()) {
                    map.append("|\n");
                } else {
                    if (!missionControl.getPlateau().isPositionEmpty(x, y)) {
                        map.append("x");
                    } else {
                        map.append("|");
                    }
                }
            }
        }

        System.out.println(map);

    }

    private void terminateOperation() {
        input.close();
        this.isTerminated = true;
        printGoodByeMessage();
    }

    void printPlateauInfo() {
        if (missionControl.getPlateau() != null) {
            System.out.printf(yellowizeTextColor.apply("   Plateau Info: from [%s:%s] to [%s:%s]\n"),
                    this.missionControl.getPlateau().getMinPlateauX(),
                    this.missionControl.getPlateau().getMinPlateauY(),
                    this.missionControl.getPlateau().getMaxPlateauX(),
                    this.missionControl.getPlateau().getMaxPlateauY()
            );
        }
    }

    void printGoodByeMessage() {
        System.out.println(SPLITTER);
        System.out.println("Good bye then, Captain " + greenizeTextColor.apply(this.missionControl.getUsername()) + "!");
    }
}
