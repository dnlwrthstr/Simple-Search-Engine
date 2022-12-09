import java.util.*;

class MapUtils {
    public static Map<Integer, String> getSubMap(TreeMap<Integer, String> map) {
        // Write your code here
        boolean even = map.firstKey() % 2 == 0;
        Map n;
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();
        Map.Entry<Integer, String>[] entryArray = entrySet.toArray(new Map.Entry[entrySet.size()]);
        if (!even) {
            n = map.headMap(entryArray[0].getKey() + 5);
        } else {
            n = map.tailMap(entryArray[entryArray.length - 1].getKey() - 4);
        }
        TreeMap<Integer, String> m = new TreeMap<>(Collections.reverseOrder());
        m.putAll(n);
        return m;
    }
}

/* Do not modify code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<Integer, String> map = new TreeMap<>();
        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
            String[] pair = s.split(":");
            map.put(Integer.parseInt(pair[0]), pair[1]);
        });

        Map<Integer, String> res = MapUtils.getSubMap(map);
        res.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}