package session13.ex4.models;

import session13.ex2.models.Manage;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements Manage<Order> {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void add(Order item) {
        orders.add(item);
    }

    @Override
    public void update(int index, Order item) {
        orders.set(index, item);
    }

    @Override
    public void delete(int index) {
        orders.remove(index);
    }

    @Override
    public void display() {
        if (orders.isEmpty()) {
            System.out.println("Danh sách đơn hàng trống!");
            return;
        }

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i + 1) + ". Mã đơn hàng: " + order.getOrderCode()
                    + ", Tên khách hàng: " + order.getCustomerName());
        }
    }

    public int findIndexByOrderCode(String orderCode) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderCode().equalsIgnoreCase(orderCode)) {
                return i;
            }
        }

        return -1;
    }

    public boolean isEmpty() {
        return orders.isEmpty();
    }
}
