package session2;

import java.util.Scanner;

public class TriangleValidation {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập cạnh a: ");
        double a = sc.nextDouble();

        System.out.print("Nhập cạnh b: ");
        double b = sc.nextDouble();

        System.out.print("Nhập cạnh c: ");
        double c = sc.nextDouble();

        // Kiểm tra hợp lệ
        if (a + b <= c || a + c <= b || b + c <= a) {
            System.out.println("Ba cạnh không tạo thành tam giác.");
            return;
        }

        // Phân loại tam giác

        // 1. Tam giác đều
        if (a == b && b == c) {
            System.out.println("Tam giác đều");
        }
        // 2. Tam giác cân
        else if (a == b || a == c || b == c) {
            System.out.println("Tam giác cân");
        }
        // 3. Tam giác vuông
        else if (a * a + b * b == c * c ||
                a * a + c * c == b * b ||
                b * b + c * c == a * a) {
            System.out.println("Tam giác vuông");
        }
        // 4. Tam giác thường
        else {
            System.out.println("Tam giác thường");

        }
    }
}

