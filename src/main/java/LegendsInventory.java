import java.util.ArrayList;
import java.util.List;

// a inventory to store game item for heroes in Legends of valor
public class LegendsInventory extends Inventory{

    private List<LegendsWeapon> weapons;
    private List<LegendsArmor> armors;
    private List<LegendsPotion> potions;
    private List<LegendsIceSpell> iceSpells;
    private List<LegendsFireSpell> fireSpells;
    private List<LegendsLightningSpell> lightningSpells;


    public LegendsInventory(){
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        potions = new ArrayList<>();
        iceSpells = new ArrayList<>();
        fireSpells = new ArrayList<>();
        lightningSpells = new ArrayList<>();
    }

    public List<LegendsWeapon> getWeapons() {
        return weapons;
    }

    public List<LegendsArmor> getArmors() {
        return armors;
    }

    public List<LegendsPotion> getPotions() {
        return potions;
    }

    public List<LegendsIceSpell> getIceSpells() {
        return iceSpells;
    }

    public List<LegendsFireSpell> getFireSpells() {
        return fireSpells;
    }

    public List<LegendsLightningSpell> getLightningSpells() {
        return lightningSpells;
    }

    public void add(LegendsItem item){
        LegendsItem.Type itemType = item.getType();
        switch(itemType){
            case WEAPON:
                weapons.add((LegendsWeapon) item);
                break;
            case ARMOR:
                armors.add((LegendsArmor) item);
                break;
            case POTION:
                potions.add((LegendsPotion) item);
                break;
            case ICE_SPELL:
                iceSpells.add((LegendsIceSpell) item);
                break;
            case FIRE_SPELL:
                fireSpells.add((LegendsFireSpell) item);
                break;
            case LIGHTNING_SPELL:
                lightningSpells.add((LegendsLightningSpell) item);
                break;
        }
    }

    public LegendsItem getItem(int index){
        if(index >= 0 && index < weapons.size()){
            return weapons.get(index);
        } else if (index >= weapons.size() && index < weapons.size() + armors.size()){
            return armors.get(index - weapons.size());
        } else if (index >= weapons.size() + armors.size()){
            return potions.get(index - weapons.size() - armors.size());
        }
        return null;
    }

    public LegendsSpell getSpell(int index){
        if(index >= 0 && index < iceSpells.size()){
            return iceSpells.get(index);
        } else if (index >= iceSpells.size() && index < iceSpells.size() + fireSpells.size()){
            return fireSpells.get(index - iceSpells.size());
        } else if (index >= iceSpells.size() + fireSpells.size()){
            return lightningSpells.get(index - iceSpells.size() - fireSpells.size());
        }
        return null;
    }

    public void removeItem(int index){
        if(index >= 0 && index < weapons.size()){
            weapons.remove(index);
        } else if (index >= weapons.size() && index < weapons.size() + armors.size()){
            armors.remove(index - weapons.size());
        } else if (index >= weapons.size() + armors.size()){
            potions.remove(index - weapons.size() - armors.size());
        }
    }

    public void removeSpell(int index){
        if(index >= 0 && index < iceSpells.size()){
            iceSpells.remove(index);
        } else if (index >= iceSpells.size() && index < iceSpells.size() + fireSpells.size()){
            fireSpells.remove(index - iceSpells.size());
        } else if (index >= iceSpells.size() + fireSpells.size()){
            lightningSpells.remove(index - iceSpells.size() - fireSpells.size());
        }
    }
}
