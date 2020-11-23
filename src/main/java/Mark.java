// a mark that represents object on world map
abstract public class Mark {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private String mark;
    private String color;

    public Mark(){
        mark = null;
        color = "";
    }

    public Mark(String mark){
        this.mark = mark;
        this.color = "";
    }

    public Mark(String mark, String color){
        this.mark = mark;
        this.color = color;
    }

    // getters
    public String getMark() {
        return mark;
    }
    public String getColor() {
        return color;
    }

    // setters
    public void setMark(String mark) {
        this.mark = mark;
    }
    public void setColor(String color) {
        this.color = color;
    }

    // get a customized mark
    public static String getYellowString(String mark){
        return ANSI_YELLOW + mark + ANSI_RESET;
    }
    public static String getGreenString(String mark){
        return ANSI_GREEN + mark + ANSI_RESET;
    }
    public static String getRedString(String mark){
        return ANSI_RED + mark + ANSI_RESET;
    }
    public static String underLine(String mark, String line){
        int length = mark.length();
        mark += "\n";
        for (int i = 0; i < length; i++) {
            mark += line;
        }
        return mark;
    }
    // helper functions for padding
    public static String centerPadding(String mark, int length){
        while(mark.length() < length - 1){
            mark = " " + mark + " ";
        }
        if(mark.length() == length - 1){
            mark = " " + mark;
        }
        return mark;
    }
    public static String rightPadding(String mark, int length){
        while(mark.length() < length){
            mark += " ";
        }
        return mark;
    }

    public static String horizontalLine(int length){
        String line = "";
        for(int i = 0; i < length; i++){
            line += "-";
        }
        line += "\n";
        return line;
    }
}
