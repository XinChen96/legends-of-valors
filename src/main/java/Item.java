// a game item
abstract public class Item {
    private String name;

    public Item() {
        name = "";
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
