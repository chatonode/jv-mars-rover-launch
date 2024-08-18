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
    private static Consumer<String> sysOutBlueizedTextWithSplitter = str -> System.out.println(blueizeTextColor.apply(str.concat("\n" + SPLITTER)));
    private static Consumer<String> sysOutRedizedSplittersAroundText = str -> System.out.println(redizeTextColor.apply("\n" + SPLITTER + "\n" + str + "\n" + SPLITTER));

    public void run() {
        initializeMissionControl();
    }


    private void initializeMissionControl() {
        sysOutBlueizedTextWithSplitter.accept("Welcome to Mission Control!");

        boolean isValidated = false;
        while (!this.isTerminated && !isValidated) {
            System.out.println("Can I get your " + redizeTextColor.apply("name") + ", please?: ");
            String username = this.input.nextLine();
            try {
                missionControl = new MissionControl(username);
                isValidated = true;
                sysOutBlueizedTextWithSplitter.accept("Mission Control is initialized!");
                sysOutBlueizedText.accept("Preparing plateau...");
                this.initializePlateau();
            } catch (InvalidClassParameterException e) {
//                sysOutRedizedSplittersAroundText.accept(e.getMessage());
            }
        }
    }

    private void initializePlateau() {
        System.out.println(blueizeTextColor.apply("Welcome on board, captain ") + greenizeTextColor.apply(missionControl.getUsername()) + blueizeTextColor.apply("! Nice to see you around!.."));
        System.out.println("Now, determine the " + redizeTextColor.apply("size") + " of the " + redizeTextColor.apply("plateau") + " you want to launch your rovers:");
        boolean isValidated = false;

        while (!isValidated) {
            System.out.println("\nSet the maximum " + redizeTextColor.apply("X") + " coordinate: ");
            String maxX = this.input.nextLine();
            System.out.println("\nNow, set the maximum " + redizeTextColor.apply("Y") + " coordinate: ");
            String maxY = this.input.nextLine();

            try {
                missionControl.initializePlateau(Integer.parseInt(maxX), Integer.parseInt(maxY));
                isValidated = true;
                sysOutBlueizedTextWithSplitter.accept("Plateau is initialized!");
                sysOutBlueizedText.accept("Preparing rover garage...");
                this.initializeEarthMenu();
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

    private void initializeEarthMenu() {
        System.out.println("Well done, captain " + greenizeTextColor.apply(missionControl.getUsername()) + "! You managed to create your plateau!");
        System.out.println("Now, check all the listed " + blueizeTextColor.apply("rover features") + " on our Earth office:");
        System.out.println("1- Create a rover");
        System.out.println("2- Get all the rovers on Earth");
        System.out.println("3- Launch the rovers (currently " + blueizeTextColor.apply(String.valueOf(missionControl.getRoversOnEarth().size())) + ")");

        boolean isValidated = false;
        while (!isValidated) {
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
                    System.out.println("coming back here?");
                    isValidated = true;
                    break;
                default:
                    System.err.println("Select between " + redizeTextColor.apply("3") + " menu items");
                    break;
            }
        }
    }

    private void earthMenuCreateRover() {
        System.out.println("Create a" + blueizeTextColor.apply("rover") + "to launch:");
    }

    private void earthMenuListRovers() {

    }

    private void earthMenuLaunchRovers() {
        System.out.println();
        System.out.println("Are you sure you want to " + redizeTextColor.apply("launch ") + greenizeTextColor.apply(String.valueOf(missionControl.getRoversOnEarth().size())) + redizeTextColor.apply(" rover(s)") + "?");


        boolean isValidated = false;
        while (!isValidated) {
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
                    sysOutBlueizedTextWithSplitter.accept("Launched rover(s) to the plateau!");
                    break;
                case "n":
                    isValidated = true;
                    sysOutBlueizedText.accept("Launching operation is aborted!");
                    sysOutBlueizedTextWithSplitter.accept("Returning to Earth office...");
                    break;
                default:
                    System.err.println("Select between " + redizeTextColor.apply("2") + " menu items");
                    break;
            }
        }
    }
}
