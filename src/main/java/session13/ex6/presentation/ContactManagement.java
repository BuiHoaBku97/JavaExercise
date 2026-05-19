package session13.ex6.presentation;

import session13.ex6.models.Contact;
import session13.ex6.models.ContactManager;

import java.util.Scanner;

public class ContactManagement {
    private static ContactManager contactManager = new ContactManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            printMenu();
            int option = getIntInput("Lựa chọn của bạn: ");

            switch (option) {
                case 1:
                    addContact();
                    break;
                case 2:
                    deleteContact();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    contactManager.display();
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 0 đến 4.");
            }
        } while (true);
    }

    private static void printMenu() {
        System.out.println("==================== MENU ====================");
        System.out.println("1. Thêm liên lạc");
        System.out.println("2. Xóa liên lạc theo số điện thoại");
        System.out.println("3. Tìm kiếm liên lạc");
        System.out.println("4. Hiển thị danh bạ");
        System.out.println("0. Thoát");
        System.out.println("==============================================");
    }

    private static void addContact() {
        String name = getRequiredInput("Nhập tên liên lạc: ");
        String phoneNumber = getRequiredInput("Nhập số điện thoại: ");

        if (!contactManager.add(new Contact(name, phoneNumber))) {
            System.out.println("Số điện thoại đã tồn tại");
            return;
        }

        System.out.println("Thêm liên lạc thành công.");
    }

    private static void deleteContact() {
        String phoneNumber = getRequiredInput("Nhập số điện thoại cần xóa: ");

        if (contactManager.deleteByPhoneNumber(phoneNumber)) {
            System.out.println("Xóa thành công.");
            return;
        }

        System.out.println("Không tìm thấy liên lạc.");
    }

    private static void searchContact() {
        String phoneNumber = getRequiredInput("Nhập số điện thoại cần tìm: ");

        if (contactManager.containsPhoneNumber(phoneNumber)) {
            System.out.println("Có tồn tại liên lạc.");
            return;
        }

        System.out.println("Không tồn tại liên lạc.");
    }

    private static String getRequiredInput(String message) {
        do {
            String value = getNextInput(message);
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Vui lòng không để trống!");
        } while (true);
    }

    private static String getNextInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String message) {
        do {
            try {
                int value = Integer.parseInt(getNextInput(message));
                if (value < 0) {
                    System.out.println("Vui lòng nhập số nguyên không âm.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ.");
            }
        } while (true);
    }
}
