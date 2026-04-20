package session2;

import java.util.Scanner;

public class DetermineArmstrongNumberInRange {
    static void main() {
        System.out.print("Nhap so: " );

        Scanner scanner = new Scanner(System.in);
        var maxRange = scanner.nextInt();
        if (maxRange < 0 ){
            return;
        }

        for (int i = 0; i <= maxRange; i++) {
            if (IsArmNumber(i)){
                System.out.print( i + " "  );
            }
        }
    }

    static boolean IsArmNumber(int number){
        int countNumber = Integer.toString(number).length() ;
        int sum = 0;

        int sample = number;
        while (sample > 0){
            int digit = sample % 10;
            sum += Math.powExact(digit, countNumber);
            sample /= 10;
        }

        return sum == number;
    }
}
