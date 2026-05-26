package session16.ex6.presentation;

import session16.ex6.models.Order;
import session16.ex6.models.OrderManager;

import java.time.LocalDate;
import java.util.List;

public class Main {
    private static final OrderManager<Order> orderManager = new OrderManager<>();

    public static void main(String[] args) {
        initializeOrders();

        printOrders(orderManager.getAll(), "Danh sach tat ca don hang:");
        printOrders(orderManager.getDeliveredOrders(), "Danh sach don hang da giao:");
        printOrders(orderManager.getUndeliveredOrders(), "Danh sach don hang chua giao:");
        printDeliveredOrderCount();
    }

    private static void initializeOrders() {
        orderManager.add(new Order(1, "Nguyen Van An", LocalDate.of(2025, 3, 15), LocalDate.of(2025, 3, 17)));
        orderManager.add(new Order(2, "Tran Thi Binh", LocalDate.of(2025, 3, 16), null));
        orderManager.add(new Order(3, "Le Minh Cuong", LocalDate.of(2025, 3, 18), LocalDate.of(2025, 3, 20)));
        orderManager.add(new Order(4, "Pham Thu Dung", LocalDate.of(2025, 3, 19), LocalDate.of(2025, 3, 24)));
        orderManager.add(new Order(5, "Hoang Gia Huy", LocalDate.of(2025, 3, 21), null));
    }

    private static void printOrders(List<Order> orders, String title) {
        System.out.println();
        System.out.println(title);

        if (orders.isEmpty()) {
            System.out.println("Khong co don hang nao.");
            return;
        }

        System.out.printf("%-4s | %-20s | %-12s | %-12s%n", "ID", "Ten KH", "Ngay dat", "Ngay giao");
        orders.forEach( o -> System.out.println(o.toDisplayString()));
    }

    private static void printDeliveredOrderCount() {
        LocalDate startDate = LocalDate.of(2025, 3, 17);
        LocalDate endDate = LocalDate.of(2025, 3, 23);
        long count = orderManager.countDeliveredOrdersBetween(startDate, endDate);

        System.out.println();
        System.out.println("So don hang da giao tu 2025-03-17 den 2025-03-23: " + count);
    }
}
