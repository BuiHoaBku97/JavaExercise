package session5;

import java.util.Scanner;
import java.util.regex.Pattern;

public class EmailValidation {

    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap dia chi email: ");
        String email = scanner.nextLine().trim();

        if (IsValidEmail(email)) {
            System.out.println("Email hop le");
        } else {
            System.out.println("Email khong hop le");
        }
    }

    private static boolean IsValidEmail(String email) {
        Pattern emailPattern =
                Pattern.compile("^[A-Za-z0-9._]+@[A-Za-z0-9.]+\\.[A-Za-z]{2,6}$");
        return emailPattern.matcher(email).matches();
    }
}
