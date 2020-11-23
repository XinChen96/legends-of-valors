import java.util.List;

// a market that stores game and game-related information
abstract public class Factory {

    public Factory(){
    }

    public static int paddingLengthForList(List<Object> in){
        int length = 0;
        for (int i = 0; i < in.size(); i++) {
            if(in.get(i).toString().length() > length){
                length = in.get(i).toString().length();
            }
        }
        length ++;
        return length;
    }

    public static int paddingLengthForTable(List<List<Object>> table){
        int length = 0;
        for(List<Object> list : table){
            for(Object obj : list){
                if(obj.toString().length() > length){
                    length = obj.toString().length();
                }
            }
        }
        return length;
    }
}
