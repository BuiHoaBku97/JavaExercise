package session14.ex6.presentation;

import session14.ex6.exceptions.ProductNotFoundInCartException;
import session14.ex6.models.Product;
import session14.ex6.models.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCartApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Product> products = new ArrayList<>();
    private static final ShoppingCart shoppingCart = new ShoppingCart();

    public static void main(String[] args) {
        initializeProducts();

        do {
            printMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    displayProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    removeProductFromCart();
                    break;
                case 4:
                    shoppingCart.displayCart();
                    break;
                case 5:
                    shoppingCart.checkout();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Vui lòng chọn chức năng từ 0 đến 5.");
            }
        } while (true);
    }

    private static void initializeProducts() {
        products.add(new Product("P001", "Bàn phím", 350_000));
        products.add(new Product("P002", "Chuột không dây", 250_000));
        products.add(new Product("P003", "Tai nghe", 500_000));
        products.add(new Product("P004", "Màn hình", 2_500_000));
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("============= MENU =============");
        System.out.println("1. Xem danh sách sản phẩm");
        System.out.println("2. Thêm sản phẩm vào giỏ hàng");
        System.out.println("3. Xóa sản phẩm khỏi giỏ hàng");
        System.out.println("4. Xem giỏ hàng");
        System.out.println("5. Thanh toán");
        System.out.println("0. Thoát");
        System.out.println("================================");
    }

    private static int getMenuChoice() {
        try {
            return Integer.parseInt(getInput("Lựa chọn của bạn: "));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void displayProducts() {
        System.out.println("Danh sách sản phẩm có sẵn:");
        for (Product product : products) {
            System.out.println("- " + product);
        }
    }

    private static void addProductToCart() {
        displayProducts();
        String productId = getInput("Nhập mã sản phẩm cần thêm: ");
        Product product = findProductById(productId);

        if (product == null) {
            System.out.println("Lỗi: Không tìm thấy sản phẩm!");
            return;
        }

        try {
            int quantity = getQuantityInput("Nhập số lượng cần mua: ");
            shoppingCart.addToCart(product, quantity);
            System.out.println("Thêm sản phẩm vào giỏ hàng thành công.");
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Số lượng không hợp lệ!");
        }
    }

    private static void removeProductFromCart() {
        String productId = getInput("Nhập mã sản phẩm cần xóa khỏi giỏ hàng: ");
        try {
            shoppingCart.removeFromCart(productId);
            System.out.println("Xóa sản phẩm khỏi giỏ hàng thành công.");
        } catch (ProductNotFoundInCartException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    private static int getQuantityInput(String message) {
        int quantity = Integer.parseInt(getInput(message));
        if (quantity <= 0) {
            throw new NumberFormatException();
        }
        return quantity;
    }

    private static Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }

    private static String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
