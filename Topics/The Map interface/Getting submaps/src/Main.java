import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int from = scanner.nextInt();
        int to = scanner.nextInt();
        scanner.nextLine();
        int n = scanner.nextInt();
        SortedMap<Integer, String> map = new TreeMap<>();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String[] pair = s.split(" ");
            map.put(Integer.parseInt(pair[0]), pair[1]);
        }

        Map subMap = map.subMap(from, ++to);

        subMap.forEach((k, v) -> System.out.println(k + " " + v));
    }
}