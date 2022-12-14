import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class MapUtils {

    public static void mapShare(Map<String, String> map) {
        String valueA = map.get("a");
        if (valueA != null) {
            map.put("b", valueA);
        }
        map.remove("c");
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] pair = s.split(":");
            map.put(pair[0], pair[1]);
        }
        MapUtils.mapShare(map);
        map.forEach((key, value) -> System.out.println(key + ":" + value));
    }
}