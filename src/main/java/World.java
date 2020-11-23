// the world of the game
abstract public class World {

    private int numRow;
    private int numCol;

    // constructor
    public World(){
        numRow = 8;
        numCol = 8;
    }

    // constructor
    public World(int numRow, int numCol){
        this.numRow = numRow;
        this.numCol = numCol;
    }

    // getters
    public int getNumRow() {
        return numRow;
    }
    public int getNumCol() {
        return numCol;
    }
}
