// a cell in a grid game world grid
abstract public class Cell {

    private int row;
    private int col;

    // constructors
    public Cell(){
        row = 0;
        col = 0;
    }

    // getter
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    public void setAddress(int row, int col){
        this.row = row;
        this.col = col;
    }
}
