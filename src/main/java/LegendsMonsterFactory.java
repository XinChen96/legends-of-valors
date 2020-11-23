import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// a factory that stores monster and monster-related information in Legends of valor
public class LegendsMonsterFactory extends LegendsFactory {

    private static List<Object> attributes;
    private List<List<Object>> monsterTable;
    private static int paddingLengthAttributes;

    public LegendsMonsterFactory(){
        attributes = new ArrayList<>(Arrays.asList("Type", "Name", "Level", "HP", "Damage", "Defense", "Dodge Chance"));
        monsterTable = new ArrayList<>(Arrays.asList(attributes));
        paddingLengthAttributes = paddingLengthForList(attributes);
    }

    public void addDragons(String name, int level, int damage, int defense, int dodgeChance){
        monsterTable.add(monsterInfo("Dragon", name, level, damage, defense, dodgeChance));
    }

    public void addExoskeletons(String name, int level, int damage, int defense, int dodgeChance){
        monsterTable.add(monsterInfo("Exoskeleton", name, level, damage, defense, dodgeChance));
    }

    public void addSpirits(String name, int level, int damage, int defense, int dodgeChance){
        monsterTable.add(monsterInfo("Spirit", name, level, damage, defense, dodgeChance));
    }

    public List<Object> monsterInfo(String type, String name, int level, int damage, int defense, int dodgeChance){
        List<Object> info = new ArrayList<>();
        info.add(type);
        info.add(name);
        info.add(level);
        info.add(level * 100);
        info.add(damage);
        info.add(defense);
        info.add(dodgeChance);
        return info;
    }

    public List<List<Object>> getMonsterInfoByLevel(int level){

        List<List<Object>> monsterTableSameLevel = new ArrayList<>();
        int colLevel = attributes.indexOf("Level");
        for(int i = 1; i < monsterTable.size(); i++){
            if((int)monsterTable.get(i).get(colLevel) == level) {
                monsterTableSameLevel.add(monsterTable.get(i));
            }
        }
        return monsterTableSameLevel;
    }

    public List<LegendsMonster> getMonstersByLevel(int num, int level){
        Random random = new Random();
        List<LegendsMonster> monsters = new ArrayList<>();
        List<List<Object>> monsterTableSameLevel = getMonsterInfoByLevel(level);
        for(int i = 0; i < num; i++){
            LegendsMonster monster = getMonster(monsterTableSameLevel.get(random.nextInt(monsterTableSameLevel.size())));
            monster.setMark(new LegendsMark("M" + (i + 1)));
            monsters.add(monster);
        }
        return monsters;
    }

    // generate a new monster object
    public LegendsMonster getMonster(List<Object> monsterInfo){
        if(monsterInfo.get(attributes.indexOf("Type")).equals("Dragon")){
            return new LegendsDragon((String)monsterInfo.get(1),
                    (int)monsterInfo.get(2),
                    (int)monsterInfo.get(3),
                    (int)monsterInfo.get(4),
                    (int)monsterInfo.get(5),
                    (int)monsterInfo.get(6));
        }else if(monsterInfo.get(attributes.indexOf("Type")).equals("Exoskeleton")){
            return new LegendsExoskeleton((String)monsterInfo.get(1),
                    (int)monsterInfo.get(2),
                    (int)monsterInfo.get(3),
                    (int)monsterInfo.get(4),
                    (int)monsterInfo.get(5),
                    (int)monsterInfo.get(6));
        }else if(monsterInfo.get(attributes.indexOf("Type")).equals("Spirit")){
            return new LegendsSpirit((String)monsterInfo.get(1),
                    (int)monsterInfo.get(2),
                    (int)monsterInfo.get(3),
                    (int)monsterInfo.get(4),
                    (int)monsterInfo.get(5),
                    (int)monsterInfo.get(6));
        }
        return null;
    }

    public static String table(String title, List<LegendsMonster> monsters) {
        List<List<Object>> table = new ArrayList<>();
        table.add(Arrays.asList("Name", "Level", "HP", "Damage", "Defense", "Dodge Chance"));
        for (LegendsMonster monster : monsters) {
            List<Object> row = new ArrayList<>();
            row.add(monster.getName());
            row.add(monster.getLevel());
            row.add(monster.getHp());
            row.add(monster.getDamage());
            row.add(monster.getDefense());
            row.add(monster.getDodgeChance());
            table.add(row);
        }

        return table(table, Mark.getGreenString(title + "\n"),
                paddingLengthForList(getCol(table, 0)), paddingLengthAttributes, 1);
    }
}