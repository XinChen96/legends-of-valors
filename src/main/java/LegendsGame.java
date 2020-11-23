// the game "Legends: Monsters and Heroes"
public class LegendsGame extends Game implements Runnable{

    private enum State {
        STARTING,
        SELECTING_HERO,
        PLAYING
    }

    private final int numHero;
    private final LegendsHeroFactory heroFactory;
    private final LegendsMonsterFactory monsterFactory;
    private final LegendsItemFactory itemFactory;
    private final LegendsHeroTeam heroTeam;
    private final LegendsMonsterTeam monsterTeam;
    private final LegendsMarket market;
    private final LegendsWorld world;
    private State state;
    boolean running;

    public LegendsGame(){
        numHero = 3;
        heroFactory = new LegendsHeroFactory();
        itemFactory = new LegendsItemFactory();
        monsterFactory = new LegendsMonsterFactory();
        heroTeam = new LegendsHeroTeam();
        monsterTeam = new LegendsMonsterTeam(monsterFactory);
        market = new LegendsMarket(itemFactory);
        world = new LegendsWorld(monsterTeam, market);
        running = true;
        state = State.STARTING;
        initializeFactories();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void initializeFactories(){
        initializeHeroFactory();
        initializeItemFactory();
        initializeMonsterFactory();
    }

    public String welcomeMessage(){
        String msg = "Welcome to LEGENDS OF VALOR!\n";
        msg = Mark.horizontalLine(msg.length()) + msg + Mark.horizontalLine(msg.length());
        return msg;
    }

    // run Legends: Monsters and Heroes
    public void run(){
        do{
            switch(state){
                case STARTING:
                    startSelectHero();
                    break;
                case SELECTING_HERO:
                    selectHeroes();
                    break;
                case PLAYING:
                    if(world.isRunning()){
                        world.run();
                    } else {
                        running = false;
                    }
                    break;
            }
        }while(running);
    }

    public void startSelectHero(){
        System.out.print(welcomeMessage());
        String instruction = "Enter " + Mark.getGreenString("s") + " to start game";
        String input = Controller.INSTANCE.getStringInput(instruction, new String[]{"s","q","S","Q"});;
        if(input.equals("s") || input.equals("S")){
            state = State.SELECTING_HERO;
        }else if(input.equals("q") || input.equals("Q")){
            running = false;
        }
    }

    public void startPlaying(){
        String instruction = "Enter " + Mark.getGreenString("s") + " to start playing";
        String input = Controller.INSTANCE.getStringInput(instruction, new String[]{"s","q","S","Q"});
        if(input.equals("s") || input.equals("S")){
            world.setHeroTeam(heroTeam);
            state = State.PLAYING;
        }else if(input.equals("q") || input.equals("Q")){
            running = false;
        }
    }

    public void selectHeroes(){
        boolean selecting = true;

        System.out.println("\n" + Mark.getRedString("* Choose your Team *") + "\n");
        System.out.print(heroFactory.tables() + "\n");

        int heroNumber = 1;
        do {
            if(heroTeam.getHeroes().size() < numHero){
                String instruction = "Enter an " + Mark.getGreenString("ID") + " to add the hero to your team";
                String input = Controller.INSTANCE.getIntStringInput(instruction, null, 1, heroFactory.numHero());
                if(Controller.isInteger(input)){
                    LegendsHero hero = heroFactory.getHero(Integer.parseInt(input));
                    hero.setMark(new LegendsMark("H" + heroNumber));
                    heroTeam.addHero(hero);
                    heroNumber++;
                }else if(input.equals("q") || input.equals("Q")){
                    running = false;
                    break;
                }
            } else {
                startPlaying();
                selecting = false;
            }
        }while(selecting);
        System.out.println();
    }

    public void initializeHeroFactory() {
        // add warriors
        heroFactory.addWarrior("Gaerdal_Ironhand", 100, 700, 500, 600, 1354, 7);
        heroFactory.addWarrior("Sehanine_Monnbow", 600, 700, 800, 500, 2500, 8);
        heroFactory.addWarrior("Muamman_Duathall", 300, 900, 500, 750, 2546, 6);
        heroFactory.addWarrior("Flandal_Steelskin", 200, 750, 650, 700, 2500, 7);
        heroFactory.addWarrior("Undefeated_Yoj", 400, 800, 400, 700, 2500, 7);
        heroFactory.addWarrior("Eunoia_Cyn", 400, 700, 800, 600, 2500, 6);
        // add sorcerers
        heroFactory.addSorcerer("Rillifane_Rallathil", 1300, 750, 450, 500, 2500, 9);
        heroFactory.addSorcerer("Segojan_Earthcaller", 900, 800, 500, 650, 2500, 5);
        heroFactory.addSorcerer("Reign_Havoc", 800, 800, 800, 800, 2500, 8);
        heroFactory.addSorcerer("Reverie_Ashels", 900, 800, 700, 400, 2500, 7);
        heroFactory.addSorcerer("Radiant_Ash", 800, 850, 400, 600, 2500, 6);
        heroFactory.addSorcerer("Skye_Soar", 1000, 700, 400, 500, 2500, 5);
        // add paladins
        heroFactory.addPaladin("Solonor_Thelandira", 300, 750, 650, 700, 2500, 7);
        heroFactory.addPaladin("Sehanine_Moonbow", 300, 750, 700, 700, 2500, 7);
        heroFactory.addPaladin("Skoraeus_Stonebones", 250, 650, 600, 350, 2500, 4);
        heroFactory.addPaladin("Garl_Glittergold", 100, 600, 500, 400, 2500, 5);
        heroFactory.addPaladin("Amaryllis_Astra", 500, 500, 500, 500, 2500, 5);
        heroFactory.addPaladin("Caliber_Heist", 400, 400, 400, 400, 2500, 8);
    }

    public void initializeItemFactory() {
        // add weapons
        itemFactory.addWeapon("Sword", 500, 1, 800, 1);
        itemFactory.addWeapon("Bow", 300, 2, 500, 2);
        itemFactory.addWeapon("Scythe", 1000, 6, 1100, 2);
        itemFactory.addWeapon("Axe", 550, 5, 850, 1);
        itemFactory.addWeapon("TSwords", 1400, 8, 1600, 2);
        itemFactory.addWeapon("Dagger", 200, 1, 250, 1);
        // add armors
        itemFactory.addArmor("Platinum_Shield", 150, 1, 200);
        itemFactory.addArmor("Breastplate", 350, 3, 600);
        itemFactory.addArmor("Full_Body_Armor", 1000, 8, 1100);
        itemFactory.addArmor("Wizard_Shield", 1200, 10, 1500);
        itemFactory.addArmor("Speed_Boots", 550, 4, 600);
        itemFactory.addArmor("Guardian_Angel", 1000, 10, 1000);
        // add potions
        itemFactory.addPotion("Healing_Potion", 250, 1, 100, "Health");
        itemFactory.addPotion("Strength_Potion", 200, 1, 75, "Strength");
        itemFactory.addPotion("Magic_Potion", 350, 2, 100, "Mana");
        itemFactory.addPotion("Luck_Elixir", 500, 4, 65, "Agility");
        itemFactory.addPotion("Mermaid_Tears", 850, 5, 100, "Health/Mana/Strength/Agility");
        itemFactory.addPotion("Ambrosia", 1000, 8, 150, "Health/Mana/Strength/Dexterity/Defense/Agility");
        // add ice spells
        itemFactory.addIceSpell("Snow_Cannon", 500, 2, 650, 250);
        itemFactory.addIceSpell("Ice_Blade", 250, 1, 450, 100);
        itemFactory.addIceSpell("Frost_Blizzard", 750, 5, 850, 350);
        itemFactory.addIceSpell("Arctic_Storm", 700, 6, 800, 300);
        // add fire spells
        itemFactory.addFireSpell("Flame_Tornado", 700, 4, 850, 300);
        itemFactory.addFireSpell("Breath_of_Fire", 350, 1, 450, 100);
        itemFactory.addFireSpell("Heat_Wave", 450, 2, 600, 150);
        itemFactory.addFireSpell("Lava_Comet", 800, 7, 1000, 550);
        itemFactory.addFireSpell("Hell_Storm", 600, 3, 950, 600);
        // add lightning spells
        itemFactory.addLightningSpell("Lightning_Dagger", 400, 1, 500, 150);
        itemFactory.addLightningSpell("Thunder_Blast", 750, 4, 950, 400);
        itemFactory.addLightningSpell("Electric_Arrows", 550, 5, 650, 200);
        itemFactory.addLightningSpell("Spark_Needles", 500, 2, 600, 200);
    }

    public void initializeMonsterFactory() {
        // add dragons
        monsterFactory.addDragons("Desghidorrah", 3, 300, 400, 35);
        monsterFactory.addDragons("Chrysophylax", 2, 200, 500, 20);
        monsterFactory.addDragons("BunsenBurner", 4, 400, 500, 45);
        monsterFactory.addDragons("Natsunomeryu", 1, 100, 200, 10);
        monsterFactory.addDragons("TheScaleless", 7, 700, 600, 75);
        monsterFactory.addDragons("Kas-Ethelinh", 5, 600, 500, 60);
        monsterFactory.addDragons("Alexstraszan", 10, 1000, 9000, 55);
        monsterFactory.addDragons("Phaarthurnax", 6, 600, 700, 60);
        monsterFactory.addDragons("D-Maleficent", 9, 900, 950, 85);
        monsterFactory.addDragons("TheWeatherbe", 8, 800, 900, 80);
        monsterFactory.addDragons("Igneel", 6, 600, 400, 60);
        monsterFactory.addDragons("BlueEyesWhite", 9, 900, 600, 75);
        // add exoskeletons
        monsterFactory.addExoskeletons("Cyrrollalee", 7, 700, 800, 75);
        monsterFactory.addExoskeletons("Brandobaris", 3, 350, 450, 30);
        monsterFactory.addExoskeletons("BigBad-Wolf", 1, 150, 250, 15);
        monsterFactory.addExoskeletons("WickedWitch", 2, 250, 350, 25);
        monsterFactory.addExoskeletons("Aasterinian", 4, 400, 500, 45);
        monsterFactory.addExoskeletons("Chronepsish", 6, 650, 750, 60);
        monsterFactory.addExoskeletons("Kiaransalee", 8, 850, 950, 85);
        monsterFactory.addExoskeletons("St-Shargaas", 5, 550, 650, 55);
        monsterFactory.addExoskeletons("Merrshaullk", 10, 1000, 900, 55);
        monsterFactory.addExoskeletons("St-Yeenoghu", 9, 950, 850, 90);
        monsterFactory.addExoskeletons("DocOck", 6, 600, 600, 55);
        monsterFactory.addExoskeletons("Exodia", 10, 1000, 1000, 50);
        // add spirits
        monsterFactory.addSpirits("Andrealphus", 2, 600, 500, 40);
        monsterFactory.addSpirits("Aim-Haborym", 1, 450, 350, 35);
        monsterFactory.addSpirits("Andromalius", 3, 550, 450, 25);
        monsterFactory.addSpirits("Chiang-shih", 4, 700, 600, 40);
        monsterFactory.addSpirits("FallenAngel", 5, 800, 700, 50);
        monsterFactory.addSpirits("Ereshkigall", 6, 950, 450, 35);
        monsterFactory.addSpirits("Melchiresas", 7, 350, 150, 75);
        monsterFactory.addSpirits("Jormunngand", 8, 600, 900, 20);
        monsterFactory.addSpirits("Rakkshasass", 9, 550, 600, 35);
        monsterFactory.addSpirits("Taltecuhtli", 10, 300, 200, 50);
        monsterFactory.addSpirits("Casper", 1, 100, 100, 50);
    }
}
