package session5;

import java.util.Random;
import java.util.Scanner;

public class GeneratePseudoRandom {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int length = inputLength(scanner);
        String randomString = generateRandomString(length, random);

        System.out.println("Chuoi ngau nhien: " + randomString);
    }

    private static int inputLength(Scanner scanner) {
        int length;

        do {
            System.out.print("Nhap do dai chuoi (1-1000): ");
            length = scanner.nextInt();

            if (length < 1 || length > 1000) {
                System.out.println("Do dai khong hop le. Vui long nhap trong khoang 1 den 1000.");
            }
        } while (length < 1 || length > 1000);

        return length;
    }

    private static String generateRandomString(int length, Random random) {
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int group = random.nextInt(3);

            if (group == 0) {
                builder.append((char) ('A' + random.nextInt(26)));
            } else if (group == 1) {
                builder.append((char) ('a' + random.nextInt(26)));
            } else {
                builder.append((char) ('0' + random.nextInt(10)));
            }
        }

        return builder.toString();
    }
}
