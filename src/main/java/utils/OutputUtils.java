package utils;

import java.util.function.Consumer;
import java.util.function.Function;

public class OutputUtils {
    public static final String SPLITTER = "-----------------------";
    public static final String GREEN_COLOR = "\u001B[32m"; // ANSI escape code for green text
    public static final String BLUE_COLOR = "\u001B[34m"; // ANSI escape code for blue text
//    public static final String YELLOW_COLOR = "\u001B[33m";
    public static final String YELLOW_COLOR = "\u001B[38;5;94m";
    public static final String RED_COLOR = "\u001B[31m";
    public static final String DARK_GRAY_COLOR = "\u001B[90m"; // ANSI escape code for dark gray text
    public static final String DARK_ORANGE_COLOR = "\u001B[38;5;94m"; // ANSI escape code for dark orange-like text
    public static final String RESET_COLOR = "\u001B[0m";

    public static final Function<String, String> greenizeTextColor = str -> GREEN_COLOR.concat(str).concat(RESET_COLOR);
    public static final Function<String, String> blueizeTextColor = str -> BLUE_COLOR.concat(str).concat(RESET_COLOR);
    public static final Function<String, String> yellowizeTextColor = str -> YELLOW_COLOR.concat(str).concat(RESET_COLOR);
    public static final Function<String, String> redizeTextColor = str -> RED_COLOR.concat(str).concat(RESET_COLOR);
    public static final Function<String, String> grayizeTextColor = str -> DARK_GRAY_COLOR.concat(str).concat(RESET_COLOR);

    public static final Runnable sysOutSplitter = () -> System.out.println(SPLITTER);
    public static final Consumer<String> sysOutBlueizedText = str -> System.out.println(blueizeTextColor.apply(str));
    public static final Consumer<String> sysOutYellowizedText = str -> System.out.println(yellowizeTextColor.apply(str));
    public static final Consumer<String> sysOutRedizedText = str -> System.out.println(redizeTextColor.apply(str));
    public static final Consumer<String> sysOutGrayizedText = str -> System.out.println(grayizeTextColor.apply(str));
    public static final Consumer<String> sysOutBlueizedTextWithSplitter = str -> System.out.println(blueizeTextColor.apply(str).concat(("\n" + SPLITTER)));
    public static final Consumer<String> sysOutYellowizedTextWithSplitter = str -> System.out.println(yellowizeTextColor.apply(str).concat(("\n" + SPLITTER)));
    public static final Consumer<String> sysOutRedizedSplittersAroundText = str -> System.out.println(redizeTextColor.apply("\n" + SPLITTER + "\n" + str + "\n" + SPLITTER));

    public static void printSelectMenuItemsErrorMessage(int numberOfMenuItems) {
        System.err.println("Select between " + redizeTextColor.apply(String.valueOf(numberOfMenuItems)) + " menu items");
    }
}
