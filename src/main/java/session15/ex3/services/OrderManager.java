package session15.ex3.services;

import session15.ex3.exceptions.OrderNotFoundException;
import session15.ex3.models.Order;
import session15.ex3.models.Product;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class OrderManager {
    private final Map<String, Order> orders;

    public OrderManager() {
        this.orders = new LinkedHashMap<>();
    }

    public void createOrder(String orderId) {
        orders.put(orderId, new Order(Integer.parseInt(orderId)));
    }

    public void addProductToOrder(String orderId, Product product) throws OrderNotFoundException {
        Order order = findById(orderId);
        order.addProduct(product);
    }

    public Order findById(String orderId) throws OrderNotFoundException {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Không tìm thấy đơn hàng.");
        }
        return order;
    }

    public void display() {
        System.out.println("Danh sách đơn hàng:");

        if (orders.isEmpty()) {
            System.out.println("Danh sách đơn hàng trống.");
            return;
        }

        System.out.println("Duyệt LinkedHashMap:");
        orders.forEach((id, order) -> System.out.println("Mã đơn " + id + ": " + order));
    }

    public Map<String, Order> getAll() {
        return orders;
    }
}
