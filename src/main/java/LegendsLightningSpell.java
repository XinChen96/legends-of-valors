// a lightning spell to be bought, sold or used by player in Legends: Monsters and Heroes
public class LegendsLightningSpell extends LegendsSpell implements Buyable{
    // constructors
    public LegendsLightningSpell() {
    }
    public LegendsLightningSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(Type.LIGHTNING_SPELL, name, cost, requiredLevel, damage, manaCost);
    }
}
