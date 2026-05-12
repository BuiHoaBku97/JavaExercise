package session10.ex3;

public class Computer {
    public double calculatePrice(double basePrice) {
        System.out.println("\nFormula: base price");
        return basePrice;
    }

    public double calculatePrice(double basePrice, double tax) {
        System.out.println("\nFormula: base price + VAT");
        return basePrice + (basePrice * tax / 100);
    }

    public double calculatePrice(double basePrice, double tax, double discount) {
        System.out.println("\nFormula: base price + VAT - discount");
        return basePrice + (basePrice * tax / 100) - discount;
    }
}
