import com.sun.javafx.charts.Legend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// a hero character of the game "Legends: Monsters and Heroes"
public class LegendsHero extends LegendsLivingCreature {

    private enum State {
        IDLING,
        FIGHTING,
        TRADING,
    }

    private enum Lane {
        IDLING,
        FIGHTING,
        TRADING,
    }

    private int id;
    private int mana;
    private int strength;
    private int agility;
    private int dexterity;
    private int money;
    private int experience;
    private LegendsInventory inventory;
    private LegendsNexusCell myNexus;
    private LegendsWeapon weapon;
    private LegendsArmor armor;
    private LegendsMonster monster;
    private State state;
    private boolean running;
    private String notice;
    private boolean displayingInfo;

    // constructors
    public LegendsHero(){
        super();
        id = 0;
        mana = 0;
        strength = 0;
        agility = 0;
        dexterity = 0;
        money = 0;
        experience = 0;
        myNexus = null;
        inventory = null;
        state = State.IDLING;
        running = true;
        notice = null;
        displayingInfo = false;
    }

    public LegendsHero(String name, int level, int hp, int mana, int strength, int agility, int dexterity, int money, int experience){
        super(Type.HERO, name, level, hp);
        id = 0;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.money = money;
        this.experience = experience;
        inventory = new LegendsInventory();
        state = State.IDLING;
        running = true;
        notice = "";
        displayingInfo = false;
    }

    // getter
    public int getMana() {
        return mana;
    }
    public int getStrength() {
        return strength;
    }
    public int getAgility() {
        return agility;
    }
    public int getDexterity() {
        return dexterity;
    }
    public int getMoney() {
        return money;
    }
    public int getExperience() {
        return experience;
    }
    public LegendsInventory getInventory() {
        return inventory;
    }

    public LegendsArmor getArmor() {
        return armor;
    }

    public boolean isRunning() {
        return running;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public void setMyNexus(LegendsNexusCell myNexus) {
        this.myNexus = myNexus;
    }


    public void run(){
        running = true;
        displayingInfo = false;
        do{
            switch(state){
                case IDLING:
                    idle();
                    break;
                case FIGHTING:
                    fight();
                    break;
                case TRADING:
                    getWorld().getMarket().setHero(this);
                    getWorld().getMarket().run();
                    if(getWorld().getMarket().isQuitingGame()){
                        running = false;
                        getWorld().setRunning(false);
                    } else {
                        state = State.IDLING;
                    }
            }
        }while(running);
    }

    // make actions when a hero is on map idling (not in fight or market)
    public void idle(){
        boolean idling;

        List<LegendsMonster> monsters = getMonstersInRange();
        System.out.println(getWorld());
        if(!monsters.isEmpty()){
            System.out.println(getMonstersInformation(
                    Mark.getRedString("* Monsters encountered within attack range! *")));
        }

        System.out.print(Mark.getRedString(notice));
        if(displayingInfo){
            System.out.println(getWorld().getHeroesInformation(this));
        }

        String[] options = new String[]{"1", "2", "3", "4",
                "w", "a", "s", "d", "t", "b", "m", "i",
                "W", "A", "S", "D", "T", "B", "M", "I"};

        String instruction = "For " + Mark.getGreenString("Hero " + id + " " + getName()) + ", \n";
        instruction += "Enter " + Mark.getGreenString("1") + " to change weapon/armor, " +
                Mark.getGreenString("2") + " to use a potion, " +
                Mark.getGreenString("3") + " to fight a monster, \n" +
                Mark.getGreenString("w") + " to move up, " +
                Mark.getGreenString("a") + " to move left, " +
                Mark.getGreenString("s") + " to move down, " +
                Mark.getGreenString("d") + " to move right, " +
                Mark.getGreenString("t") + " to teleport, " +
                Mark.getGreenString("b") + " to back to my nexus, \n" +
                Mark.getGreenString("m") + " to enter market, " +
                Mark.getGreenString("i") + " to display heroes' info";

        do {
            idling = false;
            String input = Controller.INSTANCE.getIntStringInput(instruction,options, 1,4);
            switch (input) {
                case "1":
                    selectWeaponArmor();
                    running = false;
                    break;
                case "2":
                    selectPotion();
                    running = false;
                    break;
                case "3":
                    if(!monsters.isEmpty()){
                        monster = monsters.get(0);
                        state = State.FIGHTING;
                    } else {
                        Controller.printWarning("No monsters are in attack range. You cannot fight now\n");
                        idling = true;
                    }
                    break;
                case "W":
                case "w":
                    if (moveUp()) {
                        running = false;
                    } else {
                        idling = true;
                    }
                    break;
                case "A":
                case "a":
                    if (moveLeft()) {
                        running = false;
                    } else {
                        idling = true;
                    }
                    break;
                case "S":
                case "s":
                    if (moveDown()) {
                        running = false;
                    } else {
                        idling = true;
                    }
                    break;
                case "D":
                case "d":
                    if (moveRight()) {
                        running = false;
                    } else {
                        idling = true;
                    }
                    break;
                case "T":
                case "t":
                    if (teleport()) {
                        running = false;
                    }else{
                        Controller.printWarning("Teleport cancelled \n");
                        idling = true;
                    }
                    break;
                case "B":
                case "b":
                    if (backToMyNexus()) {
                        running = false;
                    } else {
                        Controller.printWarning(getName() + "\'s nexus is occupied\n");
                        idling = true;
                    }
                    break;
                case "M":
                case "m":
                    if(getRow() == getWorld().getNumRow() - 1){
                        state = State.TRADING;
                    } else {
                        Controller.printWarning("Market is at nexus cells of your side. You are not there\n");
                        idling = true;
                    }
                    break;
                case "I":
                case "i":
                    displayingInfo = !displayingInfo;
                    break;
                case "Q":
                case "q":
                    running = false;
                    getWorld().setRunning(false);
                    break;
            }
        }while(idling);

    }

    // make actions when hero is in fight
    public void fight(){
        // actions during when a hero is on map idling (not in fight or market)
            boolean fighting;

            System.out.println(getWorld());
            System.out.print(Mark.getRedString(notice));

            if(displayingInfo){
                System.out.println(getWorld().getHeroesInformation(this));
                System.out.println(getMonstersInformation("The monster you are fighting:"));
            }

            String[] options = new String[]{"1", "2", "3", "4", "i", "I"};

            String instruction = "For " + Mark.getGreenString("Hero " + id + " " + getName()) + ", \n";
            instruction += "Enter " + Mark.getGreenString("1") + " to change weapon/armor, " +
                    Mark.getGreenString("2") + " to use a potion, " +
                    Mark.getGreenString("3") + " to attack, " +
                    Mark.getGreenString("4") + " to cast a spell, \n" +
                    Mark.getGreenString("i") + " to display heroes & monsters info";

            do {
                fighting = false;
                String input = Controller.INSTANCE.getIntStringInput(instruction,options, 1,4);
                switch (input) {
                    case "1":
                        selectWeaponArmor();
                        running = false;
                        break;
                    case "2":
                        selectPotion();
                        running = false;
                        break;
                    case "3":

                        break;
                    case "4":
                        break;
                    case "I":
                    case "i":
                        displayingInfo = !displayingInfo;
                        break;
                    case "Q":
                    case "q":
                        running = false;
                        getWorld().setRunning(false);
                        break;
                }
            }while(fighting);
    }

    // let player choose a lane and teleport to that lane
    public boolean teleport(){
        boolean teleporting;
        do {
            String instruction =  Mark.getGreenString("Teleporting to ...") + "\nEnter " +
                    Mark.getGreenString("1") + " for Top Lane, " +
                    Mark.getGreenString("2") + " for Middle Lane, " +
                    Mark.getGreenString("3") + " for Bottom Lane, " +
                    Mark.getGreenString("c") + " to cancel teleport";

            String input = Controller.INSTANCE.getIntStringInput(instruction, new String[]{"c", "C"}, 1, 3);

            teleporting = false;
            if (Controller.isInteger(input)) {
                if (Integer.parseInt(input) - 1 == getCurrentLane()) {
                    Controller.printWarning("You cannot teleport to your current lane. Select another lane\n");
                    teleporting = true;
                } else if (!getWorld().canBeTeleported(Integer.parseInt(input) - 1)) {
                    Controller.printWarning("Not enough space for teleport in the destination lane\n");
                    teleporting = true;
                } else {
                    LegendsCell dest = getWorld().getTeleportDest(Integer.parseInt(input) - 1, getRow(), getColInLane());
                    moveTo(dest);
                }
            } else if (input.equals("c") || input.equals("C")) {
                return false;
            } else if (input.equals("q") || input.equals("Q")) {
                running = false;
                getWorld().setRunning(false);
            }
        }while(teleporting);

        return true;
    }


    // get this hero's col position of a lane, either 0 or 1
    public int getColInLane(){
        if(getCol() == 0 || getCol()==3 || getCol() == 6) {
            return 0;
        } else if(getCol() == 1 || getCol()==4 || getCol() == 7) {
            return 1;
        }
        return -1;
    }

    // move hero back to its own nexus
    public boolean backToMyNexus(){
        if(myNexus.getHero() != null) {
            return false;
        }
        moveTo(myNexus);
        return true;
    }

    // buy an item and update money
    public void buyItem(LegendsItem item){
        money -= item.getCost();

        System.out.println(Mark.getGreenString("Bought " + item.getType() + " " + item.getName() +
                ", spent $" + item.getCost() + ", current balance: $" + money));
        inventory.add(item);
    }

    // sell an item and update money
    public void sellItem(int index){
        LegendsItem item = inventory.getItem(index);
        money += item.getCost();
        System.out.println(Mark.getGreenString("Sold " + item.getType() + " " + item.getName() +
                ", received $" + item.getCost() + ", current balance: $" + money));
        inventory.removeItem(index);
    }

    // buy a spell and update money
    public void sellSpell(int index){
        LegendsSpell spell = inventory.getSpell(index);
        money += spell.getCost();
        System.out.println(Mark.getGreenString("Sold " + spell.getType() + " " + spell.getName() +
                ", received $" + spell.getCost() + ", current balance: $" + money));
        inventory.removeSpell(index);
    }

    // select a weapon or armor
    public boolean selectWeaponArmor(){

        if(inventory.getArmors().isEmpty() && inventory.getWeapons().isEmpty()){
            System.out.print(Mark.getRedString("You inventory is empty"));
        }
        System.out.print(LegendsItemFactory.itemTables(getInventory().getWeapons(),
                getInventory().getArmors(), null));
        boolean selecting;
        do {
            String instruction = "Enter an " + Mark.getGreenString("ID") + " to equip a weapon/armor, " +
                    Mark.getGreenString("f") + " to finish";

            String input = Controller.INSTANCE.getIntStringInput(instruction, new String[]{"f", "F"},
                    1, getInventory().getWeapons().size() + getInventory().getArmors().size());

            selecting = false;
            if (Controller.isInteger(input)) {
                if(Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= inventory.getWeapons().size()){
                    weapon = (LegendsWeapon) inventory.getItem(Integer.parseInt(input) - 1);
                    System.out.println(Mark.getGreenString("Equipped " + weapon.getType() + " " +weapon.getName()));
                } else if (Integer.parseInt(input) > inventory.getWeapons().size()){
                    armor = (LegendsArmor) inventory.getItem(Integer.parseInt(input) - 1);
                    System.out.println(Mark.getGreenString("Equipped " + armor.getType() + " " +armor.getName()));
                }
                selecting = true;
            } else if (input.equals("q") || input.equals("Q")) {
                running = false;
                getWorld().setRunning(false);
            }
        }while(selecting);
        return true;
    }

    public boolean selectPotion(){
        System.out.print(LegendsItemFactory.itemTables(null,null,
                getInventory().getPotions()));
        boolean selecting;
        do {
            String instruction = "Enter an " + Mark.getGreenString("ID") + " to use a potion, " +
                    Mark.getGreenString("f") + " to finish";

            String input = Controller.INSTANCE.getIntStringInput(instruction, new String[]{"f", "F"},
                    1, getInventory().getPotions().size());

            selecting = false;
            if (Controller.isInteger(input)) {

                LegendsPotion potion = (LegendsPotion) inventory.getItem(Integer.parseInt(input) + inventory.getWeapons().size() +
                        inventory.getArmors().size() - 1);
                System.out.println(Mark.getGreenString("Used " + potion.getType() + " " + potion.getName()) + "not implemented");
            } else if (input.equals("q") || input.equals("Q")) {
                running = false;
                getWorld().setRunning(false);
            }
        }while(selecting);
        return true;
    }

    // get information of all monsters within attack range
    public String getMonstersInformation(String title){
        List<LegendsMonster> monsters = getMonstersInRange();
        if(!monsters.isEmpty()){
            return LegendsMonsterFactory.table(title,monsters);
        }
        return null;
    }

    // get all monsters within attack range of this hero
    public List<LegendsMonster> getMonstersInRange(){
        List<LegendsMonster> monsters = new ArrayList<>();
        if(getWorld().getGrid()[getRow()][getCol()].getMonster() != null){
            monsters.add(getWorld().getGrid()[getRow()][getCol()].getMonster());
        }
        for(LegendsCell cell : getWorld().getNeighborCells(getRow(),getCol())){
            //System.out.println(cell.getType() +  " " +cell.getRow() + " " + cell.getCol() );
            if(cell.getMonster() != null){
                //System.out.println(cell.getMonster() );
                monsters.add(cell.getMonster());
            }
        }
        return monsters;
    }

}
