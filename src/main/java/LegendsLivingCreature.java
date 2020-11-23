// a character in Legends of valor
public class LegendsLivingCreature extends LivingCreature {

    enum Type {
        HERO,
        MONSTER
    }

    private Type type;
    private int level;
    private int hp;
    private LegendsMark mark;
    private LegendsWorld world;

    // constructor
    public LegendsLivingCreature() {
        super();
        type = null;
        level = 0;
        hp = 0;
        mark = null;
        world = null;
    }
    public LegendsLivingCreature(Type type, String name, int level, int hp) {
        super(name);
        this.type = type;
        this.level = level;
        this.hp = hp;
        mark = null;
        world = null;
    }

    // getter
    public Type getType() { return type; }
    public int getLevel() {
        return level;
    }
    public int getHp() {
        return hp;
    }
    public LegendsMark getMark() { return mark; }
    public LegendsWorld getWorld() { return world; }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMark(LegendsMark mark) {
        this.mark = mark;
    }

    public void setWorld(LegendsWorld world) {
        this.world = world;
    }

    // hero checks if a direction is inaccessible and the make a movement
    public boolean moveUp(){
        if(getRow() == 0){
            return false;
        }
        LegendsCell dest = world.getGrid()[getRow() - 1][getCol()];

        if(dest.getType() == LegendsCell.Type.INACCESSIBLE){
            Controller.printWarning("Inaccessible. You cannot move up \n");
            return false;
        } else if (dest.getHero() != null ){
            Controller.printWarning("Cell occupied by another hero. You cannot move up \n");
            return false;
        } else if (type == Type.HERO && dest.getRow() < world.getMonsterRowInLane(getCurrentLane())){
            Controller.printWarning("Do not move behind monsters. You cannot move up \n");
            return false;
        }

        moveTo(dest);
        return true;
    }

    public boolean moveDown(){
        if(getRow() == world.getNumRow() - 1){
            return false;
        }
        LegendsCell dest = world.getGrid()[getRow() + 1][getCol()];
        if(dest.getType() == LegendsCell.Type.INACCESSIBLE) {
            Controller.printWarning("Inaccessible. You cannot move down \n");
            return false;
        } else if (dest.getHero() != null) {
            Controller.printWarning("Cell occupied by another hero. You cannot move down \n");
            return false;
        }
        moveTo(dest);
        return true;
    }

    public boolean moveLeft(){
        if(getCol() == 0){
            return false;
        }
        LegendsCell dest = world.getGrid()[getRow()][getCol() - 1];
        if(dest.getType() == LegendsCell.Type.INACCESSIBLE) {
            Controller.printWarning("Inaccessible. You cannot move left \n");
            return false;
        } else if (dest.getHero() != null){
            Controller.printWarning("Cell occupied by another hero. You cannot move left \n");
            return false;
        }
        moveTo(dest);
        return true;
    }

    public boolean moveRight(){
        if(getCol() == world.getNumCol() - 1 ){
            return false;
        }
        LegendsCell dest = world.getGrid()[getRow()][getCol() + 1];
        if(dest.getType() == LegendsCell.Type.INACCESSIBLE){
            Controller.printWarning("Inaccessible. You cannot move right \n");
            return false;
        } else if (dest.getHero() != null) {
            Controller.printWarning("Cell occupied by another hero. You cannot move right \n");
            return false;
        }
        moveTo(dest);
        return true;
    }

    public void moveTo(LegendsCell dest){
        if (type == Type.HERO){
            world.getGrid()[getRow()][getCol()].setHero(null);
            dest.setHero((LegendsHero) this);
        }else if (type == Type.MONSTER){
            world.getGrid()[getRow()][getCol()].setMonster(null);
            dest.setMonster((LegendsMonster) this);
        }
    }


    public int getCurrentLane(){
        if(getCol() >= 0 && getCol() < 2){
            return 0;
        } else if (getCol() > 2 && getCol() < 5 ){
            return 1;
        } else if (getCol() > 5 && getCol() <= 7){
            return 2;
        }
        return -1;
    }
}
