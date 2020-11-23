// an inaccessible cell in a game world of Legends of valor
public class LegendsInaccessibleCell extends LegendsCell{
    // constructor
    public LegendsInaccessibleCell(){
        super(Type.INACCESSIBLE, new LegendsMark("I"));
    }

    public String getContent(){
        return " X X X ";
    }
}
