package session16.ex6.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderManager<T extends Order> {
    private final ArrayList<T> orders;

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    public void add(T order) {
        orders.add(order);
    }

    public List<T> getAll() {
        return orders;
    }

    public List<T> getDeliveredOrders() {
        return orders.stream()
                .filter(order -> order.getDeliveryDate().isPresent())
                .collect(Collectors.toList());
    }

    public List<T> getUndeliveredOrders() {
        return orders.stream()
                .filter(order -> order.getDeliveryDate().isEmpty())
                .collect(Collectors.toList());
    }

    public long countDeliveredOrdersBetween(LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(order -> order.getDeliveryDate().isPresent())
                .filter(order -> {
                    LocalDate deliveryDate = order.getDeliveryDate().get();
                    return !deliveryDate.isBefore(startDate) && !deliveryDate.isAfter(endDate);
                })
                .count();
    }
}
