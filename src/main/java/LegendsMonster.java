import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// a monster character in Legends: Monsters and Heroes
public class LegendsMonster extends LegendsLivingCreature {

    private int damage;
    private int defense;
    private int dodgeChance;

    // constructor
    public LegendsMonster() {
        super();
        damage = 0;
        defense = 0;
        dodgeChance = 0;
    }
    public LegendsMonster(String name, int level, int hp, int damage, int defense, int dodgeChance) {
        super(Type.MONSTER ,name, level, hp);
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    // getters
    public int getDamage() {
        return damage;
    }
    public int getDefense() {
        return defense;
    }
    public int getDodgeChance() {
        return dodgeChance;
    }

    public void run(){
        List<LegendsHero> heroes = findHeroes();
        if (heroes.isEmpty()){
            moveDown();
        } else {
            Random random = new Random();
            attack(heroes.get(random.nextInt(heroes.size())));
        }

    }

    public List<LegendsHero> findHeroes(){
        List<LegendsHero> heroes = new ArrayList<>();
        for(LegendsCell cell : getWorld().getNeighborCells(getRow(), getCol())){
            if(cell.getHero() != null){
                heroes.add(cell.getHero());
            }
        }
        if (getWorld().getGrid()[getRow()][getCol()].getHero() != null){
            heroes.add(getWorld().getGrid()[getRow()][getCol()].getHero());
        }
        return heroes;
    }

    public void attack(LegendsHero hero) {
        int inflictedDamage = 0;

        int dodgeChange = (int) (hero.getAgility() * 0.001 * 100);
        Random random = new Random();
        if (random.nextInt(100) > dodgeChance - 1) {
            inflictedDamage = damage;
        }
        hero.setHp(hero.getHp() - inflictedDamage);

        if(hero.getArmor() != null){
            inflictedDamage -= hero.getArmor().getDamageReduction();
        }

        if(inflictedDamage <= 0){
            hero.setNotice(getName() + " attacked you (" + hero.getName() + "). Attack dodged, no damage inflicted\n");
        } else {
            hero.setNotice(getName() + " attacked you (" + hero.getName() + "). Inflicted damage: " + inflictedDamage + "\n");
        }
    }
}
