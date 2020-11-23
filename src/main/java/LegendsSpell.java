// a magical spell to be bought, sold or used by player in Legends: Monsters and Heroes
public class LegendsSpell extends LegendsItem{

    private int damage;
    private int manaCost;

    public LegendsSpell() {
        super();
        damage = 0;
        manaCost = 0;
    }

    public LegendsSpell(Type type, String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(type, true, name, cost, requiredLevel);
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public int getDamage() {
        return damage;
    }

    public int getManaCost() {
        return manaCost;
    }
}
