// a game market in Legends of valor
public class LegendsMarket extends Market{

    enum State {
        BUYING_ITEMS,
        BUYING_SPELLS,
        SELLING_ITEMS,
        SELLING_SPELLS
    }

    private LegendsItemFactory itemInventory;
    private LegendsHero hero;
    private boolean running;
    private boolean quitingGame;

    private State state;

    public LegendsMarket(){
        itemInventory = null;
        hero = null;
        running = false;
        quitingGame = false;
        state = null;
    }



    public LegendsMarket(LegendsItemFactory itemInventory){
        this.itemInventory = itemInventory;
        hero = null;
        quitingGame = false;
        running = true;
        state = State.BUYING_ITEMS;
    }

    public boolean isQuitingGame() {
        return quitingGame;
    }

    public LegendsItemFactory getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(LegendsItemFactory itemInventory) {
        this.itemInventory = itemInventory;
    }

    public LegendsHero getHero() {
        return hero;
    }

    public void setHero(LegendsHero hero) {
        this.hero = hero;
    }

    public void run(){
        String msg = "Welcome to the Market!";
        System.out.println(Mark.horizontalLine(msg.length()) + msg + "\n" + Mark.horizontalLine(msg.length()));
        running = true;
        state = State.BUYING_ITEMS;
        do{
            switch(state){
                case BUYING_ITEMS:
                    runBuyingItem();
                    break;
                case BUYING_SPELLS:
                    runBuyingSpells();
                    break;
                case SELLING_ITEMS:
                    runSellingItems();
                    break;
                case SELLING_SPELLS:
                    runSellingSpells();
                    break;
                default:
            }
        }while(running);
    }

    public void runBuyingItem(){
        boolean buying;
        System.out.print(itemInventory.itemTables());
        System.out.println();
        do {
            buying = false;
            String instruction = "Enter an " + Mark.getGreenString("ID") + " to select the item to buy, " +
                    Mark.getGreenString("spell") + " to buy spells, " +
                    Mark.getGreenString("sell") + " to sell items, " +
                    Mark.getGreenString("b") + " to back to the game";
            String input = Controller.INSTANCE.getIntStringInput(instruction, new String[]{"spell", "sell", "b", "B"}, 1, itemInventory.numItem());
            if (Controller.isInteger(input)) {
                hero.buyItem(itemInventory.getItem(Integer.parseInt(input)));
                buying = true;
            } else if (input.equals("spell")) {
                state = State.BUYING_SPELLS;
            } else if (input.equals("sell")) {
                state = State.SELLING_ITEMS;
            } else if (input.equals("b") || input.equals("B")) {
                running = false;
            } else if (input.equals("q") || input.equals("Q")) {
                running = false;
                quitingGame = true;
            }
        }while(buying);
        System.out.println();
    }

    public void runBuyingSpells(){
        boolean buying;
        System.out.print(itemInventory.spellTables());
        System.out.println();
        do {
            buying = false;
            String instruction = "Enter an " + Mark.getGreenString("ID") + " to select the spell to buy, " +
                    Mark.getGreenString("item") + " to buy items, " +
                    Mark.getGreenString("sell") + " to sell spells, " +
                    Mark.getGreenString("b") + " to back to the game";
            String input = Controller.INSTANCE.getIntStringInput(instruction, new String[]{"item", "sell", "b", "B"}, 1, itemInventory.numSpell());
            if (Controller.isInteger(input)) {
                hero.buyItem(itemInventory.getSpell(Integer.parseInt(input)));
                buying = true;
            } else if (input.equals("item")) {
                state = State.BUYING_ITEMS;
            } else if (input.equals("sell")) {
                state = State.SELLING_SPELLS;
            } else if (input.equals("b") || input.equals("B")) {
                running = false;
            } else if (input.equals("q") || input.equals("Q")) {
                running = false;
                quitingGame = true;
            }
        }while(buying);
        System.out.println();
    }

    public void runSellingItems(){
            System.out.print(itemInventory.itemTables(hero.getInventory().getWeapons(),
                    hero.getInventory().getArmors(),
                    hero.getInventory().getPotions()));
            System.out.println();

            String instruction = "Enter an " + Mark.getGreenString("ID") + " to select an item to sell, " +
                    Mark.getGreenString("spell") + " to sell spells, " +
                    Mark.getGreenString("buy") + " to buy items, " +
                    Mark.getGreenString("b") + " to back to the game";
            String input = Controller.INSTANCE.getIntStringInput(instruction, new String[]{"spell", "buy", "b", "B"}, 1, itemInventory.numSpell());
            if(Controller.isInteger(input)){
                hero.sellItem(Integer.parseInt(input) - 1);
            }else if(input.equals("spell")){
                state = State.SELLING_SPELLS;
            }else if(input.equals("buy")){
                state = State.BUYING_ITEMS;
            }else if(input.equals("b") || input.equals("B")){
                running = false;
            }else if(input.equals("q") || input.equals("Q")){
                running = false;
                quitingGame = true;
            }
            System.out.println();
    }

    public void runSellingSpells(){
        System.out.print(itemInventory.spellTables(hero.getInventory().getIceSpells(),
                hero.getInventory().getFireSpells(),
                hero.getInventory().getLightningSpells()));
        System.out.println();

        String instruction = "Enter an " + Mark.getGreenString("ID") + " to select a spell to sell, " +
                Mark.getGreenString("item") + " to sell items, " +
                Mark.getGreenString("buy") + " to buy spell, " +
                Mark.getGreenString("b") + " to back to the game";
        String input = Controller.INSTANCE.getIntStringInput(instruction, new String[]{"item", "buy", "b", "B"}, 1, itemInventory.numSpell());
        if(Controller.isInteger(input)){
            hero.sellSpell(Integer.parseInt(input) - 1);
        }else if(input.equals("item")){
            state = State.SELLING_ITEMS;
        }else if(input.equals("buy")){
            state = State.BUYING_SPELLS;
        }else if(input.equals("b") || input.equals("B")){
            running = false;
        }else if(input.equals("q") || input.equals("Q")){
            running = false;
            quitingGame = true;
        }
        System.out.println();
    }
}
