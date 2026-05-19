package session13.ex4.presentation;

import session13.ex4.models.Order;
import session13.ex4.models.OrderManager;

import java.util.Locale;
import java.util.Scanner;

public class OrderManagement {
    private static OrderManager orderManager = new OrderManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            printMenu();
            int option = getIntInput("Lựa chọn của bạn:");

            switch (option) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    updateOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    orderManager.display();
                    break;
                case 5:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1 đến 5.");
            }
        } while (true);
    }

    private static void printMenu() {
        System.out.println("*************** MENU QUẢN LÝ ĐƠN HÀNG ***************");
        System.out.println("1. Thêm đơn hàng");
        System.out.println("2. Sửa đơn hàng");
        System.out.println("3. Xóa đơn hàng");
        System.out.println("4. Hiển thị danh sách đơn hàng");
        System.out.println("5. Thoát");
    }

    private static void addOrder() {
        String orderCode = inputUniqueOrderCode("Nhập mã đơn hàng:");
        String customerName = getRequiredInput("Nhập tên khách hàng:");

        orderManager.add(new Order(orderCode, customerName));
        System.out.println("Đơn hàng đã được thêm thành công.");
    }

    private static void updateOrder() {
        if (orderManager.isEmpty()) {
            System.out.println("Danh sách đơn hàng trống!");
            return;
        }

        orderManager.display();
        String orderCode = getRequiredInput("Nhập mã đơn hàng cần sửa:");
        int index = orderManager.findIndexByOrderCode(orderCode);

        if (index == -1) {
            System.out.println("Không tìm thấy đơn hàng nào có mã = " + orderCode);
            return;
        }

        String customerName = getRequiredInput("Nhập tên khách hàng mới:");
        orderManager.update(index, new Order(orderCode, customerName));
        System.out.println("Đơn hàng đã được sửa thành công.");
    }

    private static void deleteOrder() {
        if (orderManager.isEmpty()) {
            System.out.println("Danh sách đơn hàng trống!");
            return;
        }

        orderManager.display();
        String orderCode = getRequiredInput("Nhập mã đơn hàng cần xóa:");
        int index = orderManager.findIndexByOrderCode(orderCode);

        if (index == -1) {
            System.out.println("Không tìm thấy đơn hàng nào có mã = " + orderCode);
            return;
        }

        orderManager.delete(index);
        System.out.println("Đơn hàng đã được xóa thành công.");
    }

    private static String inputUniqueOrderCode(String message) {
        do {
            String orderCode = getRequiredInput(message).toUpperCase();
            if (orderManager.findIndexByOrderCode(orderCode) != -1) {
                System.out.println("Mã đơn hàng đã tồn tại!");
                continue;
            }
            else if (orderCode.matches("^OD\\d{3}$")){
                return orderCode;
            }
            System.out.println("Order code: ODxxx");
        } while (true);
    }

    private static String getRequiredInput(String message) {
        do {
            String value = getNextInput(message);
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Vui lòng ko để trống !");
        } while (true);
    }

    private static String getNextInput(String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String message) {
        do {
            try {
                int value = Integer.parseInt(getNextInput(message));
                if (value <= 0) {
                    System.out.println("Vui lòng nhập số nguyên > 0 !");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ !");
            }
        } while (true);
    }
}
