import java.time.Year;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int year = scanner.nextInt();
        if (year % 4 == 0) {
            if (year % 400 == 0) {
                System.out.println("Leap");
            } else if (year % 100 == 0) {
                System.out.println("Regular");
            } else {
                System.out.println("Leap");
            }
        } else {
            System.out.println("Regular");
        }
    }
}