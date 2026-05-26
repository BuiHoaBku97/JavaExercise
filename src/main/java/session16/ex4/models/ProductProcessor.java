package session16.ex4.models;

import java.util.List;
import java.util.function.Predicate;

public interface ProductProcessor {
    double calculateTotalValue(List<Product> products);

    static void printProductList(List<Product> products) {
        System.out.println("Danh sach san pham:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    default boolean hasExpensiveProduct(List<Product> products) {
        Predicate<Product> priceGreater100 = p -> p.getPrice() > 100;
        return products.stream().anyMatch( priceGreater100 );
    }
}
