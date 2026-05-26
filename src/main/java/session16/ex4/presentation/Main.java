package session16.ex4.presentation;

import session16.ex4.models.Product;
import session16.ex4.models.ProductProcessor;
import session16.ex4.models.ProductProcessorImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Keyboard", 85));
        products.add(new Product("Monitor", 220));
        products.add(new Product("Mouse", 45));
        products.add(new Product("Headphone", 120));

        ProductProcessor productProcessor = new ProductProcessorImpl();

        if (productProcessor.hasExpensiveProduct(products)) {
            System.out.println("San pham co gia lon hon 100:");
            printExpensiveProducts(products);
        } else {
            System.out.println("Không có sản phẩm đắt tiền");
        }

        double totalValue = productProcessor.calculateTotalValue(products);
        System.out.println("Tong gia tri san pham: " + totalValue);

        ProductProcessor.printProductList(products);
    }

    private static void printExpensiveProducts(List<Product> products) {
        Predicate<Product> isExpensive = product -> product.getPrice() > 100;

        products.stream()
                .filter(isExpensive)
                .forEach(System.out::println);
    }
}
