import java.util.ArrayList;
import java.util.List;

public class LegendsHeroTeam extends LegendsTeam{


    List<LegendsHero> heroes;
    public LegendsHeroTeam(){
        super();
        heroes = new ArrayList<>();
    }

    public List<LegendsHero> getHeroes() {
        return heroes;
    }

    public void addHero(LegendsHero hero){
        System.out.println(Mark.getGreenString(hero.getName() + " added"));
        heroes.add(hero);
        hero.setId(heroes.size());
    }

    public int getHighestLevel(){
        int level = 1;
        for (LegendsHero hero : heroes){
            if(hero.getLevel() > level){
                level = hero.getLevel();
            }
        }
        return level;
    }

    public String heroesInformation(){
        if(!heroes.isEmpty()){
            return LegendsHeroFactory.table(heroes);
        }
        return null;
    }
}
