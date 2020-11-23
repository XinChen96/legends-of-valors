// a class to launch a game as the entry point of this application
// it separates the main method from the actual program specific logic
public class GameLauncher {
    public static void main(String[] args){
        Game legend = new LegendsGame();
        legend.run();
    }
}
