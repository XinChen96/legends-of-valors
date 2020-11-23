import java.util.ArrayList;
import java.util.List;

// a character of a game
abstract public class LivingCreature {

    private String name;
    private int row;
    private int col;

    public LivingCreature(){
        name = "";
        row = -1;
        col = -1;
    }

    public LivingCreature(String name){
        this.name = name;
        row = -1;
        col = -1;
    }

    // getter
    public String getName() {
        return name;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    // setter

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setAddress(int row, int col){
        this.row = row;
        this.col = col;
    }
}
