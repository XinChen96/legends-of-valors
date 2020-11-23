import java.util.ArrayList;
import java.util.List;
// a market that stores game and game-related information to create objects for Legends of valor
public class LegendsFactory extends Factory {
    public LegendsFactory(){
        super();
    }

    public static String table(List<List<Object>> table, String title, int paddingLengthName, int paddingLengthAttributes, int idOffset){
        String tableContent = "";
        for(int i = 0; i < table.size(); i++){
            String row = "";
            if(i == 0){
                row += Mark.rightPadding("ID",5);
                row += Mark.rightPadding((String) table.get(i).get(0), paddingLengthName);
                for (int j = 1; j < table.get(i).size(); j++) {
                    row += Mark.centerPadding((String) table.get(i).get(j), paddingLengthAttributes);
                }
                row = Mark.underLine(row,"-");
            }else {
                row += Mark.rightPadding(Integer.toString(idOffset),5);
                row += Mark.rightPadding((String) table.get(i).get(0), paddingLengthName);
                for (int j = 1; j < table.get(i).size(); j++) {
                    row += Mark.centerPadding(table.get(i).get(j).toString(), paddingLengthAttributes);
                }
                idOffset++;
            }
            tableContent += row + "\n";
        }
        return title + tableContent;
    }

    public static List<Object> getCol(List<List<Object>> table, int attribute) {
        List<Object> col = new ArrayList<>();
        for(List<Object> row : table){
            col.add(row.get(attribute));
        }
        return col;
    }

}
