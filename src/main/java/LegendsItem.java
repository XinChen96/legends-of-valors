// a game item to be bought, sold or used by player in Legends of valor
public class LegendsItem extends Item {

    enum Type {
        WEAPON("Weapon"),
        ARMOR("Armor"),
        POTION("Potion"),
        ICE_SPELL("Ice spell"),
        FIRE_SPELL("Fire spell"),
        LIGHTNING_SPELL("Lightning spell");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public boolean equalsName(String otherName) {
            return name.equals(otherName);
        }

        public String toString() {
            return this.name;
        }
    }

    private Type type;
    private boolean isSpell;
    private int cost;
    private int requiredLevel;

    public LegendsItem() {
        super();
        cost = 0;
        requiredLevel = 0;
        isSpell = false;
        type = null;
    }

    public LegendsItem(Type type, boolean isSpell, String name, int cost, int requiredLevel) {
        super(name);
        this.type = type;
        this.isSpell = isSpell;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
    }


    public int getCost() {
        return cost;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public Type getType() {
        return type;
    }
}
