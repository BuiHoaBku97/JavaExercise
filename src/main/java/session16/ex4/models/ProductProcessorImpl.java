package session16.ex4.models;

import java.util.List;

public class ProductProcessorImpl implements ProductProcessor {
    @Override
    public double calculateTotalValue(List<Product> products) {
        return products
               .stream()
               .map(Product::getPrice)
               .reduce( 0.0, Double::sum);

    }
}
