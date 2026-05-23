package session15.ex3.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Order {
    private int orderId;
    private List<Product> products;

    public Order(int orderId) {
        this.orderId = orderId;
        this.products = new LinkedList<>();
    }

    public Order(int orderId, List<Product> products) {
        this.orderId = orderId;
        this.products = products;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalAmount() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", products=" + new ArrayList<>(products) +
                ", totalAmount=" + String.format("%,.0f", getTotalAmount()) +
                '}';
    }
}
