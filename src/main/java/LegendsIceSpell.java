// an ice spell to be bought, sold or used by player in Legends: Monsters and Heroes
public class LegendsIceSpell extends LegendsSpell implements Buyable{
    // constructors
    public LegendsIceSpell() {
        super();
    }
    public LegendsIceSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(Type.ICE_SPELL, name, cost, requiredLevel, damage, manaCost);
    }
}
