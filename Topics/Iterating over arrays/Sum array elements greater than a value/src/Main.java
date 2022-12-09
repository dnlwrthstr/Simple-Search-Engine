import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        scanner.nextLine();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.nextLine();
        int n = scanner.nextInt();
        int sum = 0;
        for (int e : array) {
            sum =  e > n  ? (sum + e) : sum;
        }
        System.out.println(sum);
    }
}