// a weapon to be bought, sold or used by player in Legends: Monsters and Heroes
public class LegendsWeapon extends LegendsItem implements Buyable{

    private int damage;
    private int requiredHands;

    // constructors
    public LegendsWeapon(){
        super();
        damage = 0;
        requiredHands = 0;
    }
    public LegendsWeapon(String name, int cost, int requiredLevel, int damage, int requiredHands) {
        super(Type.WEAPON, false, name, cost, requiredLevel);
        this.damage = damage;
        this.requiredHands = requiredHands;
    }

    // getters
    public int getDamage() {
        return damage;
    }

    public int getRequiredHands() {
        return requiredHands;
    }
}
