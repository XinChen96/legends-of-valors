import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract public class Config {

    public Config(){

    }

    public static List<List<String>> readTableFromTxtFile(String fileName){
        List<List<String>> table = new ArrayList<>();
        try {
            URL url = Game.class.getResource(fileName + ".txt");
            File file = new File(url.getPath());
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Config file not found");
            e.printStackTrace();
        }

        return table;
    }

    public static void main(String[] args) {
        Config.readTableFromTxtFile("Armory");
    }
}
