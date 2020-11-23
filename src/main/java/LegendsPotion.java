// a potion to be bought, sold or used by player in Legends: Monsters and Heroes
public class LegendsPotion extends LegendsItem{
    private int attributeIncrease;
    private String attributeAffected;

    public LegendsPotion() {
        super();
        attributeIncrease = 0;
        attributeAffected = null;
    }

    public LegendsPotion(String name, int cost, int requiredLevel, int attributeIncrease, String attributeAffected) {
        super(Type.POTION, false, name, cost, requiredLevel);
        this.attributeIncrease = attributeIncrease;
        this.attributeAffected = attributeAffected;
    }

    // getters
    public int getAttributeIncrease() {
        return attributeIncrease;
    }
    public String getAttributedAffected() {
        return attributeAffected;
    }
}
