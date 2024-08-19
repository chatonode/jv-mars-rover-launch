package view;

import controller.orchestrator.MissionControl;
import exception.business.InvalidClassParameterException;
import exception.business.NoRoversToLaunchException;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class OutputManager {
    private final Scanner input;
    boolean isTerminated;
    private MissionControl missionControl;
    private static final String SPLITTER = "-----------------------";
    private static final String GREEN_COLOR = "\u001B[32m"; // ANSI escape code for green text
    private static final String BLUE_COLOR = "\u001B[34m"; // ANSI escape code for blue text
    private static final String YELLOW_COLOR = "\u001B[33m";
    private static final String RED_COLOR = "\u001B[31m";
    private static final String RESET_COLOR = "\u001B[0m"; // ANSI escape code to reset the color

    public OutputManager() {
        input = new Scanner(System.in);
        isTerminated = false;
    }

    private static Function<String, String> greenizeTextColor = str -> GREEN_COLOR.concat(str).concat(RESET_COLOR);
    private static Function<String, String> blueizeTextColor = str -> BLUE_COLOR.concat(str).concat(RESET_COLOR);
    private static Function<String, String> yellowizeTextColor = str -> YELLOW_COLOR.concat(str).concat(RESET_COLOR);
    private static Function<String, String> redizeTextColor = str -> RED_COLOR.concat(str).concat(RESET_COLOR);

    private static Consumer<String> sysOutBlueizedText = str -> System.out.println(blueizeTextColor.apply(str));
    private static Consumer<String> sysOutYellowizedText = str -> System.out.println(yellowizeTextColor.apply(str));
    private static Consumer<String> sysOutRedizedText = str -> System.out.println(redizeTextColor.apply(str));
    private static Consumer<String> sysOutBlueizedTextWithSplitter = str -> System.out.println(blueizeTextColor.apply(str.concat("\n" + SPLITTER)));
    private static Consumer<String> sysOutYellowizedTextWithSplitter = str -> System.out.println(yellowizeTextColor.apply(str.concat("\n" + SPLITTER)));
    private static Consumer<String> sysOutRedizedSplittersAroundText = str -> System.out.println(redizeTextColor.apply("\n" + SPLITTER + "\n" + str + "\n" + SPLITTER));

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
                sysOutBlueizedTextWithSplitter.accept("Mission Control is initialized!");
                sysOutYellowizedText.accept("Preparing plateau signals...");
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
                sysOutYellowizedTextWithSplitter.accept("Plateau is initialized!");
                sysOutBlueizedTextWithSplitter.accept("Configuring stations on multi planets...");
//                this.initializeEarthMenu();
                this.initializePlanetMenus();
            } catch (InvalidClassParameterException | NumberFormatException e) {
                if (e instanceof InvalidClassParameterException) {

                }
//                    sysOutRedizedSplittersAroundText.accept(e.getMessage());
                else if (e instanceof NumberFormatException) {
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
            System.out.println("(0)- Exit");
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
            System.out.println("(0)- Back to Planet Menu");

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
        System.out.println("1- Move a rover with set of instructions");
        System.out.println("2- List all rovers");
        System.out.println("3- List filtered rovers");
        System.out.println("4- See rovers on the map");
    }

    private void terminateOperation() {
        input.close();
        this.isTerminated = true;
        printGoodByeMessage();
    }

    private static void printSelectMenuItemsErrorMessage(int numberOfMenuItems) {
        System.err.println("Select between " + redizeTextColor.apply(String.valueOf(numberOfMenuItems)) + " menu items");
    }

    private void printPlateauInfo() {
        if (missionControl.getPlateau() != null) {
            System.out.printf(yellowizeTextColor.apply("   Plateau Info: from [%s:%s] to [%s:%s]\n"),
                    this.missionControl.getPlateau().getMinPlateauX(),
                    this.missionControl.getPlateau().getMinPlateauY(),
                    this.missionControl.getPlateau().getMaxPlateauX(),
                    this.missionControl.getPlateau().getMaxPlateauY()
            );
        }
    }

    private void printGoodByeMessage() {
        System.out.println(SPLITTER);
        System.out.println("Good bye then, Captain " + greenizeTextColor.apply(this.missionControl.getUsername()) + "!");
    }
}
