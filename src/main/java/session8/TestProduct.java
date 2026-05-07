package session8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestProduct {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        int option;

        do {
            option = printMenu(scanner);

            switch (option) {
                case 1:
                    Product product = new Product();
                    product.input();
                    products.add(product);
                    break;
                case 2:
                    if (products.isEmpty()) {
                        System.out.println("Khong co san pham");
                        break;
                    }

                    System.out.println("Danh sach san pham:");
                    for (Product item : products) {
                        item.print();
                    }
                    break;
                case 3:
                    if (products.isEmpty()) {
                        System.out.println("Khong co san pham");
                        break;
                    }

                    System.out.print("Nhap gia thap nhat: ");
                    double minPrice = scanner.nextDouble();
                    System.out.print("Nhap gia cao nhat: ");
                    double maxPrice = scanner.nextDouble();

                    if (minPrice > maxPrice) {
                        double temp = minPrice;
                        minPrice = maxPrice;
                        maxPrice = temp;
                    }

                    boolean found = false;
                    System.out.println("San pham trong khoang gia:");
                    for (Product item : products) {
                        if (item.getPrice() >= minPrice && item.getPrice() <= maxPrice) {
                            item.print();
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Khong tim thay san pham phu hop");
                    }
                    break;
                case 4:
                    System.out.println("So san pham da tao: " + Product.getCreatedCount());
                    break;
                case 0:
                    System.out.println("Da thoat chuong trinh");
                    return;
                default:
                    System.out.println("Lua chon khong hop le");
                    break;
            }
        } while (true);
    }

    private static int printMenu(Scanner scanner) {
        System.out.println("===MENU SAN PHAM===");
        System.out.println("1. Them san pham");
        System.out.println("2. Hien thi danh sach");
        System.out.println("3. Tim san pham theo khoang gia");
        System.out.println("4. Thong ke so san pham da tao");
        System.out.println("0. Thoat");
        System.out.print("Lua chon cua ban: ");

        return scanner.nextInt();
    }
}
