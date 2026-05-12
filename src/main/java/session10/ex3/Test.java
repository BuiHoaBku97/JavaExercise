package session10.ex3;

public class Test {
    public static void main(String[] args) {
        Computer computer = new Computer();

        double basePrice = 15000000;
        double tax = 10;
        double discount = 1000000;

        double price1 = computer.calculatePrice(basePrice);
        System.out.println("Price with base price only: " + price1);

        double price2 = computer.calculatePrice(basePrice, tax);
        System.out.println("Price with base price and VAT: " + price2);

        double price3 = computer.calculatePrice(basePrice, tax, discount);
        System.out.println("Price with base price, VAT and discount: " + price3);
    }
}
