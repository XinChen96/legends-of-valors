import java.util.List;

// a cell in the grid world of legends of valor
public class LegendsCell extends Cell {

    enum Type{
        BUSH,
        CAVE,
        INACCESSIBLE,
        KOULOU,
        NEXUS,
        PLAIN
    }
    private Type type;
    private String[] cell;
    private LegendsMark mark;
    private LegendsHero hero;
    private LegendsMonster monster;
    private LegendsWorld world;
    private List<LegendsCell> neighbors;
    private boolean explored;

    // constructors
    public LegendsCell(){
        // default tile is an empty tile
        super();
        type = null;
        mark = null;
        cell = null;
        hero = null;
        monster = null;
        world = null;
        explored = false;
    }

    public LegendsCell(Type type, LegendsMark mark){
        super();
        this.type = type;
        this.mark = mark;
        cell = new String[3];
        hero = null;
        monster = null;
        world = null;
        explored = false;
    }


    public Type getType() {
        return type;
    }

    public boolean isExplored() {
        return explored;
    }

    public String getContent(){
        return " " + ((hero == null) ? "  " : hero.getMark()) + " " +
                ((monster == null) ? "  " : monster.getMark()) + " ";
    }

    public String[] getCell() {
        if(monster != null && monster.getHp() <= 0){
            monster = null;
        }
        if(hero != null && hero.getHp() <= 0){
            hero.setHp(100 * hero.getLevel());
        }
        cell[0] = cell[2] = mark + " - " + mark + " - " + mark;
        cell[1] = "|" + getContent() + "|";
        return cell;
    }

    public LegendsHero getHero() {
        return hero;
    }

    public void setHero(LegendsHero hero) {
        this.hero = hero;
        if (hero != null){
            hero.setAddress(getRow(), getCol());
            updateExplored();
        }
    }

    public LegendsMonster getMonster() {
        return monster;
    }

    public void setMonster(LegendsMonster monster) {
        this.monster = monster;
        if (monster != null){
            monster.setAddress(getRow(), getCol());
        }
    }

    public void setWorld(LegendsWorld world) {
        this.world = world;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public void updateExplored(){
        explored = true;
        for (LegendsCell neighbor : world.getNeighborCells(getRow(),getCol())){
            neighbor.setExplored(true);
        }
    }

    // string representation
    public String toString(){
        String cell = getCell()[0];
        for(int i = 1; i < getCell().length; i++){
            cell += "\n" + getCell()[i];
        }
        return cell;
    }
}
