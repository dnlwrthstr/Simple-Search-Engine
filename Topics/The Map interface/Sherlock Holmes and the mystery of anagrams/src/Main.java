import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class Main {

    public static Map anagramHash(String word) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = word.toCharArray();
        for (char c : charArray) {
            map.put(c, map.get(c) == null ? 0 : map.get(c) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word1 = scanner.nextLine().toLowerCase();
        String word2 = scanner.nextLine().toLowerCase();
        if (anagramHash(word1).equals(anagramHash(word2))) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}