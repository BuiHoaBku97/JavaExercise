package session5;

import java.util.Scanner;
import java.util.regex.Pattern;

public class PasswordValidation {

    static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap mat khau: ");
        String password = scanner.nextLine();

        if (isValidPassword(password)) {
            System.out.println("Mat khau hop le");
        } else {
            System.out.println("Mat khau khong hop le");
        }
    }

    private static boolean isValidPassword(String password) {
        Pattern passworkPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$!%]).{8,}$");
        return passworkPattern.matcher(password).matches();
    }
}
