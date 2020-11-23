import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// a factory that stores item and item-related information to create items in Legends of valor
public class LegendsItemFactory extends LegendsFactory {
    private static List<Object> weaponAttributes;
    private static List<Object> armorAttributes;
    private static List<Object> potionAttributes;
    private static List<Object> spellAttributes;
    private List<List<Object>> weapons;
    private List<List<Object>> armors;
    private List<List<Object>> potions;
    private List<List<Object>> iceSpells;
    private List<List<Object>> fireSpells;
    private List<List<Object>> lightningSpells;

    // constructor
    public LegendsItemFactory(){
        weaponAttributes = new ArrayList<>(Arrays.asList("Name", "Cost", "Required Level", "Damage", "Required Hands"));
        armorAttributes = new ArrayList<>(Arrays.asList("Name", "Cost", "Required Level", "Damage Reduction"));
        potionAttributes = new ArrayList<>(Arrays.asList("Name", "Cost", "Required Level", "Attribute Increase", "Attribute Affected"));
        spellAttributes = new ArrayList<>(Arrays.asList("Name", "Cost", "Required level", "Damage", "Mana Cost"));

        weapons = new ArrayList<>(Arrays.asList(weaponAttributes));
        armors = new ArrayList<>(Arrays.asList(armorAttributes));
        potions = new ArrayList<>(Arrays.asList(potionAttributes));
        iceSpells = new ArrayList<>(Arrays.asList(spellAttributes));
        fireSpells = new ArrayList<>(Arrays.asList(spellAttributes));
        lightningSpells = new ArrayList<>(Arrays.asList(spellAttributes));
    }

    public int numItem(){
        return (weapons.size() - 1) + (armors.size() - 1) + (potions.size() - 1);
    }

    public int numSpell(){
        return (iceSpells.size() - 1) + (fireSpells.size() - 1) + (lightningSpells.size() - 1);
    }

    public void addWeapon(String name, int cost, int requiredLevel, int damage, int requiredHands){
        List<Object> info = new ArrayList<>();
        info.add(name);
        info.add(cost);
        info.add(requiredLevel);
        info.add(damage);
        info.add(requiredHands);
        weapons.add(info);
    }
    public void addArmor(String name, int cost, int requiredLevel, int damageReduction){
        List<Object> info = new ArrayList<>();
        info.add(name);
        info.add(cost);
        info.add(requiredLevel);
        info.add(damageReduction);
        armors.add(info);
    }
    public void addPotion(String name, int cost, int requiredLevel, int attributeIncrease, String attributeAffected){
        List<Object> info = new ArrayList<>();
        info.add(name);
        info.add(cost);
        info.add(requiredLevel);
        info.add(attributeIncrease);
        info.add(attributeAffected);
        potions.add(info);
    }
    public void addIceSpell(String name, int cost, int requiredLevel, int damage, int manaCost){
        iceSpells.add(spellInfo(name, cost, requiredLevel, damage, manaCost));
    }
    public void addFireSpell(String name, int cost, int requiredLevel, int damage, int manaCost){
        fireSpells.add(spellInfo(name, cost, requiredLevel, damage, manaCost));
    }
    public void addLightningSpell(String name, int cost, int requiredLevel, int damage, int manaCost){
        lightningSpells.add(spellInfo(name, cost, requiredLevel, damage, manaCost));
    }
    public List<Object> spellInfo(String name, int cost, int requiredLevel, int damage, int manaCost){
        List<Object> info = new ArrayList<>();
        info.add(name);
        info.add(cost);
        info.add(requiredLevel);
        info.add(damage);
        info.add(manaCost);
        return info;
    }

    public LegendsItem getItem(int id){
        if(id > 0 && id <= (weapons.size() - 1)){
            int idOffset = id;
            return new LegendsWeapon((String)weapons.get(idOffset).get(0),
                    (int)weapons.get(idOffset).get(1),
                    (int)weapons.get(idOffset).get(2),
                    (int)weapons.get(idOffset).get(3),
                    (int)weapons.get(idOffset).get(4));
        } else if(id > (weapons.size() - 1) && id <= (weapons.size() - 1) + (armors.size() - 1)) {
            int idOffset = id - (weapons.size() - 1);
            return new LegendsArmor((String) armors.get(idOffset).get(0),
                    (int) armors.get(idOffset).get(1),
                    (int) armors.get(idOffset).get(2),
                    (int) armors.get(idOffset).get(3));
        } else if(id > (weapons.size() - 1) + (armors.size() - 1) && id <= numItem()) {
            int idOffset = id - (weapons.size() - 1) - (armors.size() - 1);
            return new LegendsPotion((String) potions.get(idOffset).get(0),
                    (int) potions.get(idOffset).get(1),
                    (int) potions.get(idOffset).get(2),
                    (int) potions.get(idOffset).get(3),
                    (String) potions.get(idOffset).get(4));
        }
        return null;
    }

    public LegendsItem getSpell(int id){

        if(id > 0 && id <= (iceSpells.size() - 1)){
            int idOffset = id;
            return new LegendsIceSpell((String)iceSpells.get(idOffset).get(0),
                    (int)iceSpells.get(idOffset).get(1),
                    (int)iceSpells.get(idOffset).get(2),
                    (int)iceSpells.get(idOffset).get(3),
                    (int)iceSpells.get(idOffset).get(4));
        } else if(id > (iceSpells.size() - 1) && id <= (iceSpells.size() - 1) + (fireSpells.size() - 1)) {
            int idOffset = id - (iceSpells.size() - 1);
            return new LegendsFireSpell((String) fireSpells.get(idOffset).get(0),
                    (int) fireSpells.get(idOffset).get(1),
                    (int) fireSpells.get(idOffset).get(2),
                    (int) fireSpells.get(idOffset).get(3),
                    (int) fireSpells.get(idOffset).get(4));
        } else if(id > (iceSpells.size() - 1) + (fireSpells.size() - 1) && id <= numItem()) {
            int idOffset = id - (iceSpells.size() - 1) - (fireSpells.size() - 1);
            return new LegendsLightningSpell((String) lightningSpells.get(idOffset).get(0),
                    (int) lightningSpells.get(idOffset).get(1),
                    (int) lightningSpells.get(idOffset).get(2),
                    (int) lightningSpells.get(idOffset).get(3),
                    (int) lightningSpells.get(idOffset).get(4));
        }
        return null;
    }

    // String representation
    public String itemTables(){
        String tables = Mark.getGreenString("Item Market: \n");
        int idOffset = 1;
        tables += table(weapons, Mark.underLine("Avaiable Weapons", "*") + "\n", idOffset) + "\n";
        idOffset += weapons.size() - 1;
        tables += table(armors, Mark.underLine("Avaiable Armors", "*") + "\n",idOffset) + "\n";
        idOffset += armors.size() - 1;
        tables += getPotionTable(idOffset);
        return tables;
    }


    public static String itemTables(List<LegendsWeapon> weapons, List<LegendsArmor> armors, List<LegendsPotion> potions){

        String tables = Mark.getGreenString("My Items: \n");
        int idOffset = 1;

        if(weapons != null) {
            List<List<Object>> weaponTable = new ArrayList<>(Arrays.asList(weaponAttributes));
            for(LegendsWeapon weapon : weapons){
                List<Object> row = new ArrayList<>();
                row.add(weapon.getName());
                row.add(weapon.getCost());
                row.add(weapon.getRequiredLevel());
                row.add(weapon.getDamage());
                row.add(weapon.getRequiredHands());
                weaponTable.add(row);
            }
            tables += table(weaponTable, Mark.underLine("My Weapons", "*") + "\n", idOffset) + "\n";
            idOffset += weaponTable.size() - 1;
        }

        if(armors != null){
            List<List<Object>> armorTable = new ArrayList<>(Arrays.asList(armorAttributes));
            for(LegendsArmor armor : armors){
                List<Object> row = new ArrayList<>();
                row.add(armor.getName());
                row.add(armor.getCost());
                row.add(armor.getRequiredLevel());
                row.add(armor.getDamageReduction());
                armorTable.add(row);
            }
            tables += table(armorTable, Mark.underLine("My Armors", "*") + "\n",idOffset) + "\n";
            idOffset += armorTable.size() - 1;
        }

        if(potions != null){
            List<List<Object>> potionTable = new ArrayList<>(Arrays.asList(potionAttributes));
            for(LegendsPotion potion : potions){
                List<Object> row = new ArrayList<>();
                row.add(potion.getName());
                row.add(potion.getCost());
                row.add(potion.getRequiredLevel());
                row.add(potion.getAttributeIncrease());
                row.add(potion.getAttributedAffected());
                potionTable.add(row);
            }
            tables += Mark.underLine("My Potions", "*") + "\n" + getPotionTable(potionTable, idOffset);
        }

        return tables;
    }

    public String spellTables(){
        String tables = Mark.getGreenString("Spell Market\n");
        int idOffset = 1;
        tables += table(iceSpells, Mark.underLine("Avaiable Ice Spells", "*") + "\n", idOffset) + "\n";
        idOffset += iceSpells.size() - 1;
        tables += table(fireSpells, Mark.underLine("Avaiable Fire Spells", "*") + "\n", idOffset) + "\n";
        idOffset += fireSpells.size() - 1;
        tables += table(lightningSpells, Mark.underLine("Avaiable Lightning Spells", "*") + "\n", idOffset);
        return tables;
    }

    public String spellTables(List<LegendsIceSpell> iceSpells, List<LegendsFireSpell> fireSpells, List<LegendsLightningSpell> lightningSpells){

        List<List<Object>> iceSpellTable = getSpellTableFromList(iceSpells);
        List<List<Object>> fireSpellTable = getSpellTableFromList(fireSpells);
        List<List<Object>> lightningSpellTable = getSpellTableFromList(lightningSpells);

        String tables = Mark.getGreenString("My Spells: \n");
        int idOffset = 1;
        tables += table(iceSpellTable, Mark.underLine("My Ice Spells", "*") + "\n", idOffset) + "\n";
        idOffset += iceSpellTable.size() - 1;
        tables += table(fireSpellTable, Mark.underLine("My Fire Spells", "*") + "\n",idOffset) + "\n";
        idOffset += fireSpellTable.size() - 1;
        tables += table(lightningSpellTable, Mark.underLine("My Lightning Spells", "*") + "\n", idOffset);
        return tables;
    }

    public List<List<Object>> getSpellTableFromList(List<? extends LegendsSpell> spells){
        List<List<Object>> spellTable = new ArrayList<>(Arrays.asList(spellAttributes));
        for(LegendsSpell spell : spells){
            List<Object> row = new ArrayList<>();
            row.add(spell.getName());
            row.add(spell.getCost());
            row.add(spell.getRequiredLevel());
            row.add(spell.getDamage());
            row.add(spell.getManaCost());
            spellTable.add(row);
        }
        return spellTable;
    }


    public static String table(List<List<Object>> table, String title, int idOffset){
        int paddingLengthName = paddingLengthForList(getCol(table, 0));
        int paddingLengthAttributes = paddingLengthForList(table.get(0));
        return LegendsFactory.table(table, title, paddingLengthName, paddingLengthAttributes, idOffset);
    }

    public String table(List<List<Object>> table, String title, int paddingAttributeLength, int idOffset){
        int paddingLengthName = paddingLengthForList(getCol(table, 0));
        return super.table(table, title, paddingLengthName, paddingAttributeLength, idOffset);
    }

    public String getPotionTable(int idOffset){
        return Mark.underLine("Avaiable Potions", "*") + "\n" + getPotionTable(potions, idOffset);
    }

    public static String getPotionTable(List<List<Object>> potions, int idOffset){
        String tableContent = "";
        int paddingLengthName = paddingLengthForList(getCol(potions,0));
        int paddingLengthAttributes = paddingLengthForList(potions.get(0));
        int paddingLengthAttributeAffected = paddingLengthForList(getCol(potions,potionAttributes.size() - 1));
        for(int i = 0; i < potions.size(); i++){
            String row = "";
            if(i == 0){
                row += Mark.rightPadding("ID",5);
                row += Mark.rightPadding((String) potions.get(i).get(0), paddingLengthName);
                for (int j = 1; j < potions.get(i).size(); j++) {
                    if(j == potions.get(i).size() - 1){
                        row += Mark.centerPadding(potions.get(i).get(j).toString(), paddingLengthAttributeAffected);
                    }else {
                        row += Mark.centerPadding(potions.get(i).get(j).toString(), paddingLengthAttributes);
                    }
                }
                row = Mark.underLine(row,"-");
            }else{
                row += Mark.rightPadding(Integer.toString(idOffset),5);
                row += Mark.rightPadding((String) potions.get(i).get(0), paddingLengthName);
                for (int j = 1; j < potions.get(i).size(); j++) {
                    if(j == potions.get(i).size() - 1){
                        row += Mark.centerPadding(potions.get(i).get(j).toString(), paddingLengthAttributeAffected);
                    }else {
                        row += Mark.centerPadding(potions.get(i).get(j).toString(), paddingLengthAttributes);
                    }
                }
                idOffset++;
            }
            tableContent += row + "\n";
        }
        return tableContent;
    }



}
