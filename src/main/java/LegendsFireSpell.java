// a fire spell to be bought, sold or used by player in Legends: Monsters and Heroes
public class LegendsFireSpell extends LegendsSpell{
    // constructors
    public LegendsFireSpell() {
    }
    public LegendsFireSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(Type.FIRE_SPELL, name, cost, requiredLevel, damage, manaCost);
    }
}
