package session2;

import java.util.Scanner;

public class NumberToString {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập số (100-999): ");
        int n = sc.nextInt();

        // Kiểm tra hợp lệ
        if (n < 100 || n > 999) {
            System.out.println("Số nhập vào không hợp lệ");
            return;
        }

        int hundreds = n / 100;
        int tens = (n % 100) / 10;
        int units = n % 10;

        String result = readNumber(hundreds) + " tram";

        if (tens == 0) {
            if (units != 0) {
                result += " le";
            }
        } else if (tens == 1) {
            result += " mười";
        } else {
            result += " " + readNumber(tens) + " mươi";
        }

        if (units != 0) {
            if (tens != 0 && units == 1) {
                result += " mốt";
            } else if (tens >= 1 && units == 5) {
                result += " lăm";
            } else {
                result += " " + readNumber(units);
            }
        }

        System.out.println(result);
    }


    static String readNumber(int n) {
        switch (n) {
            case 0: return "không";
            case 1: return "một";
            case 2: return "hai";
            case 3: return "ba";
            case 4: return "bốn";
            case 5: return "năm";
            case 6: return "sáu";
            case 7: return "bảy";
            case 8: return "tám";
            case 9: return "chín";
            default: return "";
        }
    }
}
