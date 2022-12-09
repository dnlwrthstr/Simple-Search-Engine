import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static String w = "";

    public static String getW() {
        return w;
    }

    static void changeList(List<String> list) {
        // write your code here

        for (String s : list) {
            w = w.length() < s.length() ? s : w;
        }
        list.replaceAll(e -> getW());
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        List<String> lst = Arrays.asList(s.split(" "));
        changeList(lst);
        lst.forEach(e -> System.out.print(e + " "));
    }
}