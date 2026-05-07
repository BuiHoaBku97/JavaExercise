package session8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainSession {
    static void main() {
//        testCurrencyConverter();
//        testRectangle();
    }

    static void testCurrencyConverter(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ti gia VND/USD: ");
        double rate = scanner.nextDouble();
        CurrencyConverter.setRate(rate);
        while (true){
            System.out.print("So tien VND: ");
            double soTien = scanner.nextDouble();
            if ( soTien == -1 ){
                break;
            }
            System.out.print("--->USD: " + CurrencyConverter.toUSD(soTien) + "\n");
        }
    }

    static void testRectangle(){
        Rectangle[] listRect = new Rectangle[3];
        listRect[0] = new Rectangle(3, 4);
        listRect[1] = new Rectangle(5, 2);
        listRect[2] = new Rectangle(4.5, 3.5);

        for (int i = 0; i < listRect.length; i++){
            System.out.println("R" + ( i + 1) + ": " + listRect[i]);
        }

        Rectangle biggestRect = listRect[0];
        for (Rectangle rect : listRect) {
            if (rect.getArea() > biggestRect.getArea()) {
                biggestRect = rect;
            }
        }

        for (Rectangle rect : listRect) {
            if (rect.getArea() == biggestRect.getArea()) {
                System.out.println("Biggest area rectangle: " + rect);
            }
        }
    }
}
