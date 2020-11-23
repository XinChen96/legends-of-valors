// a armor to be bought, sold or used by player in Legends: Monsters and Heroes
public class LegendsArmor extends LegendsItem implements Buyable{

    private int damageReduction;

    public LegendsArmor() {
        super();
        this.damageReduction = 0;
    }

    public LegendsArmor(String name, int cost, int requiredLevel, int damageReduction) {
        super(Type.ARMOR, false, name, cost, requiredLevel);
        this.damageReduction = damageReduction;
    }

    public int getDamageReduction() {
        return damageReduction;
    }
}
