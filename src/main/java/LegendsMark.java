// a mark that represents object on world map in Legends
public class LegendsMark extends Mark{

    // constructors
    public LegendsMark(){
        super(" ");
    }
    public LegendsMark(String mark){
        super(mark);
    }
    public LegendsMark(String mark, String color){
        super(mark, color);
    }

    // get length of mark string
    public int getLength(){
        return getMark().length();
    }

    // string representation with color
    public String toString(){
        return getColor() + getMark() + ANSI_RESET;
    }

    public String info(){
        return toString() + ": ";
    }
}
