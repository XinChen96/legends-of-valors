import java.util.ArrayList;
import java.util.List;

public class LegendsMonsterTeam extends LegendsTeam{

    private LegendsMonsterFactory monsterFactory;
    private List<LegendsMonster> monsters;

    public LegendsMonsterTeam(){
        super();
        monsterFactory = null;
        monsters = new ArrayList<>();
    }

    public LegendsMonsterTeam(LegendsMonsterFactory monsterFactory){
        super();
        this.monsterFactory = monsterFactory;
        monsters = new ArrayList<>();
    }

    public List<LegendsMonster> getMonsters() {
        return monsters;
    }

    public void add3MonstersByLevel(int level){
        monsters.addAll(monsterFactory.getMonstersByLevel(3,level));
    }

    public void addMonster(LegendsMonster monster){
        System.out.println(Mark.getGreenString(monster.getName() + " spawned"));
        monsters.add(monster);
    }
}
