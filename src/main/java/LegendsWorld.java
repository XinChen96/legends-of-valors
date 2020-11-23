import java.util.*;

// the world of the game "Legends: Monsters and Heroes" represented by a grid of specific dimensions
public class LegendsWorld extends World implements Runnable{

    private double chanceSpecialCell;
    private LegendsHeroTeam heroTeam;
    private LegendsMonsterTeam monsterTeam;
    private LegendsMarket market;
    private LegendsCell[][] grid;
    private boolean running;
    private int round;


    // constructors
    public LegendsWorld(){
        super(8,8);
        chanceSpecialCell = 0.2;
        heroTeam = null;
        monsterTeam = null;
        market = null;
        running = true;
        round = 0;
        initializeGrid();
    }
    public LegendsWorld(LegendsMonsterTeam monsterTeam, LegendsMarket market){
        super(8,8);
        chanceSpecialCell = 0.2;
        heroTeam = null;
        this.monsterTeam = monsterTeam;
        this.market = market;
        running = true;
        initializeGrid();
    }

    public LegendsMarket getMarket() {
        return market;
    }

    public LegendsCell[][] getGrid() {
        return grid;
    }

    public boolean isRunning() {
        return running;
    }

    public void setHeroTeam(LegendsHeroTeam heroTeam) {
        this.heroTeam = heroTeam;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    // initialize the grid and cells for the game world
    public void initializeGrid() {
        grid = new LegendsCell[getNumRow()][getNumCol()];

        // place inaccessible cells
        for (int i = 0; i < getNumRow(); i++) {
            grid[i][2] = new LegendsInaccessibleCell();
            grid[i][2].setAddress(i,2);
        }
        for (int i = 0; i < getNumRow(); i++) {
            grid[i][5] = new LegendsInaccessibleCell();
            grid[i][5].setAddress(i,5);
        }

        // place nexus cells
        for (int i = 0; i < getNumCol(); i++) {
            if (!(i == 2 || i == 5)) {
                grid[0][i] = new LegendsNexusCell();
                grid[0][i].setAddress(0,i);
            }
        }
        for (int i = 0; i < getNumCol(); i++) {
            if(!(i == 2 || i == 5)) {
                grid[getNumCol() - 1][i] = new LegendsNexusCell();
                grid[getNumCol() - 1][i].setAddress(getNumCol() - 1,i);
            }
        }

        // traverse the map and fill the rest of empty cells
        Stack<LegendsCell> cells = getRandomAssignedCells();
        for(int i = 0; i < getNumRow(); i++){
            for(int j = 0; j < getNumCol(); j++){
                if(grid[i][j] == null){
                    grid[i][j] = cells.pop();
                    grid[i][j].setAddress(i,j);
                }
                grid[i][j].setWorld(this);
            }
        }


    }

    // create and shuffle plain and special cells for randomly assigning them into the grid
    public Stack<LegendsCell> getRandomAssignedCells(){
        Stack<LegendsCell> cells = new Stack<>();
        int numEmptyCell = (getNumRow() * getNumCol() - 2 * getNumRow() - 2 * (getNumCol() - 2));
        int numSpecialCell = (int)(chanceSpecialCell * numEmptyCell);
        int numPlainCell = numEmptyCell - 3 * numSpecialCell;

        for(int i = 0; i < numSpecialCell; i++){
            cells.add(new LegendsBushCell());
            cells.add(new LegendsCaveCell());
            cells.add(new LegendsKoulouCell());
        }
        for(int i = 0; i < numPlainCell; i++){
            cells.add(new LegendsPlainCell());
        }
        Collections.shuffle(cells);
        return cells;
    }

    public void run(){
        round = 1;
        spawnHeroTeam();
        do{
            if(round % 8 == 1){
                spawnMonsterTeam();
            }
            for(LegendsHero hero : heroTeam.getHeroes()){
                System.out.println();
                if(running) {
                    hero.run();

                }
            }

            if(running) {
                for (LegendsMonster monster : monsterTeam.getMonsters()) {
                    monster.run();
                }
            }

            round++;
        }while(running);
    }

    public void spawnHeroTeam(){

        Random random = new Random();

        List<LegendsNexusCell> nexuses = new ArrayList<>();
        nexuses.add((LegendsNexusCell) grid[grid.length - 1][random.nextInt(2)]);
        nexuses.add((LegendsNexusCell) grid[grid.length - 1][3 + random.nextInt(2)]);
        nexuses.add((LegendsNexusCell) grid[grid.length - 1][6 + random.nextInt(2)]);
        List<LegendsHero> heroes = heroTeam.getHeroes();

        nexuses.get(0).setHero(heroes.get(0));
        heroes.get(0).setMyNexus(nexuses.get(0));
        nexuses.get(1).setHero(heroes.get(1));
        heroes.get(1).setMyNexus(nexuses.get(1));
        nexuses.get(2).setHero(heroes.get(2));
        heroes.get(2).setMyNexus(nexuses.get(2));

        for(LegendsHero hero : heroes){
            hero.setWorld(this);
        }
    }

    public void spawnMonsterTeam(){

        Random random = new Random();
        monsterTeam.add3MonstersByLevel(heroTeam.getHighestLevel());
        List<LegendsMonster> monsters = monsterTeam.getMonsters();
        grid[0][random.nextInt(2)].setMonster(monsters.get(monsters.size() - 3));
        grid[0][3 + random.nextInt(2)].setMonster(monsters.get(monsters.size() - 2));
        grid[0][6 + random.nextInt(2)].setMonster(monsters.get(monsters.size() - 1));

        for(LegendsMonster monster : monsters){
            monster.setWorld(this);
        }
    }

    public List<LegendsCell> getNeighborCells(int row, int col){
        List<LegendsCell> neighbors = new ArrayList<>();
        // left
        addCellToList(neighbors, row - 1, col - 1);
        addCellToList(neighbors, row, col - 1);
        addCellToList(neighbors, row + 1, col - 1);
        // mid
        addCellToList(neighbors, row - 1, col);
        addCellToList(neighbors, row + 1, col);
        // right
        addCellToList(neighbors, row - 1, col + 1);
        addCellToList(neighbors, row, col + 1);
        addCellToList(neighbors, row + 1, col + 1);
        return neighbors;
    }


    public List<LegendsCell> addCellToList(List<LegendsCell> cells, int row, int col){
        if (row < 0 || row > 7 || col < 0 || col > 7){
            return cells;
        }
        if (grid[row][col].getType() != LegendsCell.Type.INACCESSIBLE){
            cells.add(grid[row][col]);
        }
        return cells;
    }

    public LegendsCell getTeleportDest(int lane, int row, int colInLane){
        int monsterRow = getMonsterRowInLane(lane);
        int neighborColInLane = (colInLane == 0) ? 1 : 0;
        int col = colInLane + lane * 3;
        int neighborCol = neighborColInLane + lane * 3;
        if (monsterRow > row) {
            row = monsterRow;
        }

        for(int i = row; i < getNumRow();i++){
            if (grid[i][col].getHero() == null){
                return grid[i][col];
            } else if (grid[i][neighborCol].getHero() == null){
                return grid[i][neighborCol];
            }
        }
        return null;
    }

    public boolean canBeTeleported(int lane){
        if(!(getMonsterRowInLane(lane) == getNumRow() - 2 &&
                grid[getNumRow() - 1][lane * 3].getHero() != null &&
                grid[getNumRow() - 1][lane * 3 + 1].getHero() != null)){
            return true;
        }
        return false;
    }

    public int getMonsterRowInLane(int lane){
        int row = -1;
        for(int i = 0; i < getNumRow(); i++){
            if(grid[i][lane * 3].getMonster() != null ||
                    grid[i][lane * 3 + 1].getMonster() != null) {
                if(i > row) {
                    row = i;
                }
            }
        }
        return row;
    }

    public String getHeroesInformation(LegendsHero hero){
        return heroTeam.heroesInformation();
    }

    // String representations
    public String toString(){
        String world = Mark.getYellowString("* Round " + round + " *\n") +
                Mark.getGreenString("World Map");
        for(int i = 0; i < getNumRow(); i++){
            world += "\n";
            for(int iCell = 0; iCell < 3; iCell++) {
                for (int j = 0; j < getNumCol(); j++) {
                    world += grid[i][j].getCell()[iCell] + "   ";
                }
                world += "\n";
            }
        }
        return world;
    }
}
