import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// a market that stores hero and hero-related information in Legends: Monsters and Heroes
public class LegendsHeroFactory extends LegendsFactory {

    private static List<Object> attributes;
    private List<List<Object>> warriors;
    private List<List<Object>> sorcerers;
    private List<List<Object>> paladins;
    private int startingLevel;
    private int startingHP;
    private static int paddingLengthAttributes;

    // constructor
    public LegendsHeroFactory(){
        attributes = new ArrayList<>(Arrays.asList("Name", "Level", "HP", "Mana", "Strength", "Agility", "Dexterity", "Money", "Experience"));
        warriors = new ArrayList<>(Arrays.asList(attributes));
        sorcerers = new ArrayList<>(Arrays.asList(attributes));
        paladins = new ArrayList<>(Arrays.asList(attributes));
        startingLevel = 1;
        startingHP = 100 * startingLevel;
        paddingLengthAttributes = paddingLengthForList(attributes);
    }

    public void addWarrior(String name, int mana, int strength, int agility, int dexterity, int money, int experience){
        warriors.add(heroInfo(name,  startingLevel, startingHP, mana, strength, agility, dexterity, money, experience));
    }
    public void addSorcerer(String name, int mana, int strength, int agility, int dexterity, int money, int experience){
        sorcerers.add(heroInfo(name, startingLevel, startingHP,  mana, strength, agility, dexterity, money, experience));
    }
    public void addPaladin(String name, int mana, int strength, int agility, int dexterity, int money, int experience){
        paladins.add(heroInfo(name, startingLevel, startingHP, mana, strength, agility, dexterity, money, experience));
    }

    public List<Object> heroInfo(String name, int level, int hp, int mana, int strength, int agility, int dexterity, int money, int experience){
        List<Object> info = new ArrayList<>();
        info.add(name);
        info.add(level);
        info.add(hp);
        info.add(mana);
        info.add(strength);
        info.add(agility);
        info.add(dexterity);
        info.add(money);
        info.add(experience);
        return info;
    }

    // total numbers of heroes
    public int numHero(){
        return  (warriors.size() - 1) +
                (sorcerers.size() - 1) +
                (paladins.size() - 1);
    }

    // generate a new hero object
    public LegendsHero getHero(int id){

        if(id > 0 && id <= (warriors.size() - 1)){
            int idOffset = id;
            return new LegendsWarrior((String)warriors.get(idOffset).get(0),
                    (int)warriors.get(idOffset).get(1),
                    (int)warriors.get(idOffset).get(2),
                    (int)warriors.get(idOffset).get(3),
                    (int)warriors.get(idOffset).get(4),
                    (int)warriors.get(idOffset).get(5),
                    (int)warriors.get(idOffset).get(6),
                    (int)warriors.get(idOffset).get(7),
                    (int)warriors.get(idOffset).get(8));
        } else if(id > (warriors.size() - 1) && id <= (warriors.size() - 1) + (sorcerers.size() - 1)) {
            int idOffset = id - (warriors.size() - 1);
            return new LegendsSorcerer((String) sorcerers.get(idOffset).get(0),
                    (int) sorcerers.get(idOffset).get(1),
                    (int) sorcerers.get(idOffset).get(2),
                    (int) sorcerers.get(idOffset).get(3),
                    (int) sorcerers.get(idOffset).get(4),
                    (int) sorcerers.get(idOffset).get(5),
                    (int) sorcerers.get(idOffset).get(6),
                    (int) sorcerers.get(idOffset).get(7),
                    (int) sorcerers.get(idOffset).get(8));
        } else if(id > (warriors.size() - 1) + (sorcerers.size() - 1) && id <= numHero()) {
            int idOffset = id - (warriors.size() - 1) - (sorcerers.size() - 1);
            return new LegendsPaladin((String) paladins.get(idOffset).get(0),
                    (int) paladins.get(idOffset).get(1),
                    (int) paladins.get(idOffset).get(2),
                    (int) paladins.get(idOffset).get(3),
                    (int) paladins.get(idOffset).get(4),
                    (int) paladins.get(idOffset).get(5),
                    (int) paladins.get(idOffset).get(6),
                    (int) paladins.get(idOffset).get(7),
                    (int) paladins.get(idOffset).get(8));
        }
        return null;
    }

    // String representation
    public String tables(){
        int paddingLengthName = paddingLengthForList(getCol(warriors, 0));
        if (paddingLengthName < paddingLengthForList(getCol(sorcerers, 0))){
            paddingLengthName = paddingLengthForList(getCol(sorcerers, 0));
        }
        if (paddingLengthName < paddingLengthForList(getCol(paladins, 0))){
            paddingLengthName = paddingLengthForList(getCol(paladins, 0));
        }

        String tables = "";
        int idOffset = 1;
        tables += table(warriors, Mark.underLine("Available Warriors", "*") + "\n\n",
                paddingLengthName, paddingLengthAttributes, idOffset) + "\n";
        idOffset += warriors.size() - 1;
        tables += table(sorcerers, Mark.underLine("Available Sorcerers", "*") + "\n\n",
                paddingLengthName,paddingLengthAttributes, idOffset) + "\n";
        idOffset += sorcerers.size() - 1;
        tables += table(paladins, Mark.underLine("Available Paladins", "*") + "\n\n",
                paddingLengthName, paddingLengthAttributes, idOffset);
        return tables;
    }

    public static String table(List<LegendsHero> heroes){
        List<List<Object>> table = new ArrayList<>(Arrays.asList(attributes));
        for(LegendsHero hero : heroes){
            List<Object> row = new ArrayList<>();
            row.add(hero.getName());
            row.add(hero.getLevel());
            row.add(hero.getHp());
            row.add(hero.getMana());
            row.add(hero.getStrength());
            row.add(hero.getAgility());
            row.add(hero.getDexterity());
            row.add(hero.getMoney());
            row.add(hero.getExperience());
            table.add(row);
        }

        return table(table, Mark.getGreenString("Your Team Members:\n"),
                paddingLengthForList(getCol(table, 0)), paddingLengthAttributes, 1);
    }



}
