package session13.ex3.presentation;

import session13.ex3.models.Invoice;
import session13.ex3.models.InvoiceManager;

import java.util.Scanner;

public class InvoiceManagement {
    private static InvoiceManager invoiceManager = new InvoiceManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            printMenu();
            int option = getIntInput("Lựa chọn của bạn: ");

            switch (option) {
                case 1:
                    addInvoice();
                    break;
                case 2:
                    updateInvoice();
                    break;
                case 3:
                    deleteInvoice();
                    break;
                case 4:
                    invoiceManager.display();
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
        System.out.println("*************** MENU QUẢN LÝ HÓA ĐƠN ***************");
        System.out.println("1. Thêm hóa đơn");
        System.out.println("2. Sửa hóa đơn");
        System.out.println("3. Xóa hóa đơn");
        System.out.println("4. Hiển thị danh sách hóa đơn");
        System.out.println("5. Thoát");
    }

    private static void addInvoice() {
        String code = getRequiredInput("Nhập mã hóa đơn: ");
        double amount = getDoubleInput("Nhập số tiền: ");

        invoiceManager.add(new Invoice(code, amount));
        System.out.println("Hóa đơn đã được thêm thành công.");
    }

    private static void updateInvoice() {
        if (invoiceManager.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống!");
            return;
        }

        invoiceManager.display();
        int id = getIntInput("Nhập id hóa đơn cần sửa: ");
        int index = id - 1;

        if (!invoiceManager.isValidIndex(index)) {
            System.out.println("Không tìm thấy hóa đơn nào có id = " + id);
            return;
        }

        String code = getRequiredInput("Nhập mã hóa đơn mới: ");
        double amount = getDoubleInput("Nhập số tiền mới: ");

        invoiceManager.update(index, new Invoice(code, amount));
        System.out.println("Hóa đơn đã được sửa thành công.");
    }

    private static void deleteInvoice() {
        if (invoiceManager.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống!");
            return;
        }

        invoiceManager.display();
        int id = getIntInput("Nhập id hóa đơn cần xóa: ");
        int index = id - 1;

        if (!invoiceManager.isValidIndex(index)) {
            System.out.println("Không tìm thấy hóa đơn nào có id = " + id);
            return;
        }

        invoiceManager.delete(index);
        System.out.println("Hóa đơn đã được xóa thành công.");
    }

    private static String getRequiredInput(String message) {
        do {
            String value = getNextInput(message);
            if (value.isEmpty()) {
                System.out.println("Vui lòng ko để trống !");
            }
            else if ( value.matches("^HD\\d{3}$"))
            {
                return value;
            }
            System.out.println("Mã hóa đơn phải có định dạng HDxxx");
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

    private static double getDoubleInput(String message) {
        do {
            try {
                double value = Double.parseDouble(getNextInput(message));
                if (value < 0) {
                    System.out.println("Vui lòng nhập số thực >= 0 !");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực hợp lệ !");
            }
        } while (true);
    }
}
