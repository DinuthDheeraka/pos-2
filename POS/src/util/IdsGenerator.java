package util;

public class IdsGenerator {
    public static String generateId(String prefix,String lastId){
        if(lastId!=null){
            int newId = Integer.parseInt(lastId.replace(prefix, "")) + 1;
            return String.format(prefix+"%03d", newId);
        }
        else {
            return prefix+000;
        }
    }
}
