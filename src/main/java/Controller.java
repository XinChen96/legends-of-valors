import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// a game controller that receives and validate user input from the console and can be accessed globally
// according to item 3 in "Effective Java 3rd Edition", public enum is the best way to implement a global singleton
public enum Controller {

    INSTANCE;

    private Scanner scanner = new Scanner(System.in);

    public String getStringInput(){
        return scanner.next();
    }

    public String getStringInput(String instruction) {
        System.out.print(instruction);
        return getStringInput();
    }

    // return input integer that is within the predefined range (lower, upper)
    public int getIntInput (int lower, int upper, String warning){
        String input = scanner.next();
        while (!isInteger(input) || Integer.parseInt(input) < lower || Integer.parseInt(input) > upper) {
            printWarning(warning);
            input = scanner.next();
        }
        System.out.println();
        return Integer.parseInt(input);
    }

    public String getStringInput(String instruction, String[] options) {
        instruction += ", " + Mark.getGreenString("q") + " to quit game: ";
        String input = getStringInput(instruction);
        // validate
        while (!isValidatedString(input, optionsWithQ(options))) {
            printWarning("Invalid input: " + input + "\n");
            input = getStringInput(instruction);;
        }
        return input;
    }

    public String getIntStringInput(String instruction, String[] options, int lower, int upper) {
        instruction += ", " + Mark.getGreenString("q") + " to quit game: ";
        String input = getStringInput(instruction);
        // validate
        while (!isValidatedInt(input, lower, upper) && !isValidatedString(input, optionsWithQ(options))) {
            if (isInteger(input)) {
                printWarning("Invalid input (out of range): " + input + "\n");
            } else {
                printWarning("Invalid input: " + input + "\n");
            }
            input = getStringInput(instruction);
        }
        return input;
    }

    public static List<String> optionsWithQ(String[] options){
        List<String> optionList = new ArrayList<>();
        if(options != null) {
            for (String option : options) {
                optionList.add(option);
            }
        }
        optionList.add("q");
        optionList.add("Q");
        return optionList;
    }

    public static boolean isValidatedString(String input, List<String> validOptions){
        if(validOptions == null){
            return true;
        }
        if(!isInteger(input)) {
            for (int i = 0; i < validOptions.size(); i++) {
                if (input.equals(validOptions.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidatedInt(String input, int lower, int upper){
        if (isInteger(input) && (Integer.parseInt(input) >= lower && Integer.parseInt(input) <= upper)) {
            return true;
        }
        return false;
    }

    // check if the user input is a integer
    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // print red warning message
    public static void printWarning(String warning){
        System.out.print(Mark.getRedString(warning));
    }

}