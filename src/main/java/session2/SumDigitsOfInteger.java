package session2;

import java.util.Scanner;

public class SumDigitsOfInteger {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Integer number: ");
        int n = sc.nextInt();

        if (n < 0) {
            n = -n;
        }

        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            sum += digit;
            n = n / 10;
        }

        System.out.println("Sum = " + sum);
    }
}
