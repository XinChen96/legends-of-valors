// a mark of inaccessible cell that represents object on world map in Legends
public class LegendsInaccessibleMark extends LegendsMark{
    // constructor
    public LegendsInaccessibleMark(){
        super("&");
    }

    // mark info
    public String info(){
        return super.info() + "NonAccessible";
    }
}
