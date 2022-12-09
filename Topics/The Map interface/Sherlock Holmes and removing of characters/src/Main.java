import java.util.*;

class Main {

    public static Map anagramHash(String word) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = word.toCharArray();
        for (char c : charArray) {
            map.put(c, map.get(c) == null ? 1 : map.get(c) + 1);
        }
        return map;
    }

    public static int reduceToSameHash(String w1, String w2) {
        Map map1 = anagramHash(w1);
        Map map2 = anagramHash(w2);

        Set<Character> k1 = map1.keySet();
        Set<Character> k2 = map2.keySet();

        Set<Character> k3 = new HashSet<>();
        k3.addAll(k1);
        k3.addAll(k2);

        k3.forEach((k) -> {
            int v2 = map1.get(k) == null ? 0 : (int) map1.get(k);
            int v1 = map2.get(k) == null ? 0 : (int) map2.get(k);
            int dif = v1 - v2;
            map1.put(k, Math.abs(dif));
        });

        int n = 0;
        for (var k : k3) {
            int m = (int) map1.get(k);
            n += m;
        }
        return n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word1 = scanner.nextLine().toLowerCase();
        String word2 = scanner.nextLine().toLowerCase();

        System.out.println(reduceToSameHash(word1, word2));
    }
}