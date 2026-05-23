package session15.ex3.presentation;

import session15.ex3.exceptions.InvalidProductPriceException;
import session15.ex3.exceptions.OrderNotFoundException;
import session15.ex3.exceptions.ProductNotFoundException;
import session15.ex3.models.Product;
import session15.ex3.services.OrderManager;
import session15.ex3.services.ProductManager;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ProductManager<Product> productManager = new ProductManager<>();
    private static final OrderManager orderManager = new OrderManager();

    public static void main(String[] args) {
        initializeProducts();

        do {
            printMenu();
            int choice = getIntInput("Lựa chọn của bạn: ");

            try {
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        removeProduct();
                        break;
                    case 3:
                        productManager.display();
                        break;
                    case 4:
                        createOrder();
                        break;
                    case 5:
                        addProductToOrder();
                        break;
                    case 6:
                        orderManager.display();
                        break;
                    case 0:
                        System.out.println("Thoát chương trình.");
                        return;
                    default:
                        System.out.println("Vui lòng chọn chức năng từ 0 đến 6.");
                }
            } catch (InvalidProductPriceException | ProductNotFoundException | OrderNotFoundException e) {
                System.out.println("Lỗi: " + e.getMessage());
            } finally {
                System.out.println("Hoàn tất thao tác.");
            }
        } while (true);
    }

    private static void initializeProducts() {
        try {
            productManager.add(new Product(1, "Bàn phím", 350_000));
            productManager.add(new Product(2, "Chuột", 250_000));
            productManager.add(new Product(3, "Tai nghe", 500_000));
        } catch (InvalidProductPriceException e) {
            System.out.println("Lỗi dữ liệu mẫu: " + e.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("================ MENU ================");
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Xóa sản phẩm");
        System.out.println("3. Hiển thị sản phẩm");
        System.out.println("4. Tạo đơn hàng");
        System.out.println("5. Thêm sản phẩm vào đơn hàng");
        System.out.println("6. Hiển thị đơn hàng");
        System.out.println("0. Thoát");
        System.out.println("======================================");
    }

    private static void addProduct() throws InvalidProductPriceException {
        int id = getIntInput("Nhập id sản phẩm: ");
        String name = getStringInput("Nhập tên sản phẩm: ");
        double price = getDoubleInput("Nhập giá sản phẩm: ");

        productManager.add(new Product(id, name, price));
        System.out.println("Thêm sản phẩm thành công.");
    }

    private static void removeProduct() throws ProductNotFoundException {
        int id = getIntInput("Nhập id sản phẩm cần xóa: ");
        productManager.removeById(id);
        System.out.println("Xóa sản phẩm thành công.");
    }

    private static void createOrder() {
        int orderId = getIntInput("Nhập mã đơn hàng: ");
        orderManager.createOrder(String.valueOf(orderId));
        System.out.println("Tạo đơn hàng thành công.");
    }

    private static void addProductToOrder() throws OrderNotFoundException, ProductNotFoundException {
        String orderId = getStringInput("Nhập mã đơn hàng: ");
        int productId = getIntInput("Nhập id sản phẩm cần thêm vào đơn hàng: ");

        Product product = productManager.findById(productId);
        orderManager.addProductToOrder(orderId, product);
        System.out.println("Thêm sản phẩm vào đơn hàng thành công.");
    }

    private static String getStringInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String message) {
        do {
            try {
                return Integer.parseInt(getStringInput(message));
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
            }
        } while (true);
    }

    private static double getDoubleInput(String message) {
        do {
            try {
                return Double.parseDouble(getStringInput(message));
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập số thực hợp lệ!");
            }
        } while (true);
    }
}
