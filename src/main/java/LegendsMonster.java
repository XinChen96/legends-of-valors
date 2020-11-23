import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// a monster character in Legends of valor
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
            if(getHp() > 0) {
                Random random = new Random();
                attack(heroes.get(random.nextInt(heroes.size())));
            }
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

        int dodgeChance = (int) (hero.getAgility() * 0.001 * 100);
        Random random = new Random();
        if (random.nextInt(100) > dodgeChance - 1) {
            inflictedDamage = damage;
        } else {
            hero.setNotice(hero.getNotice() + "\n"+ Mark.getRedString(getName() + " attacked you (" + hero.getName() + "). Attack dodged. No damage inflicted\n\n"));
            return;
        }

        if(hero.getArmor() != null){
            inflictedDamage -= hero.getArmor().getDamageReduction();
        }

        if(inflictedDamage <= 0){
            hero.setNotice(hero.getNotice() + "\n"+ Mark.getRedString(getName() + " attacked you (" + hero.getName() + "). No damage inflicted\n\n"));
        } else {
            hero.setHp(hero.getHp() - inflictedDamage);
            hero.setNotice(hero.getNotice() + "\n"+ Mark.getRedString(getName() + " attacked you (" + hero.getName() + "). Inflicted damage: " + inflictedDamage + "\n\n"));
        }

        if(hero.getHp() <= 0){
            hero.backToMyNexus();
        }
    }
}
