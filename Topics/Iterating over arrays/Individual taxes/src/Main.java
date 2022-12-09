import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int companies = scanner.nextInt();
        scanner.nextLine();
        int[] yearlyIncome = new int[companies];
        for (int i = 0; i < companies; i++) {
            yearlyIncome[i] = scanner.nextInt();
        }
        scanner.nextLine();
        int[] tax = new int[companies];
        for (int i = 0; i < companies; i++) {
            tax[i] = scanner.nextInt();
        }
        double taxSize = 0.0;
        int n = 0;
        for (int i = 0; i < companies; i++) {
            double size = yearlyIncome[i] * tax[i] / 100.0;
            if (size > taxSize) {
                taxSize = size;
                n = i;
            }
        }
        System.out.println(companies == 0? 0 : n + 1);
    }
}