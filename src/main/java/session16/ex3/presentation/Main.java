package session16.ex3.presentation;

import session16.ex3.models.Message;
import session16.ex3.models.MessageManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MessageManager<Message> messageManager = new MessageManager<>();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        do {
            printMenu();
            int choice = getIntInput("Lua chon cua ban: ");

            switch (choice) {
                case 1:
                    sendMessage();
                    break;
                case 2:
                    displayMessages(messageManager.getAll(), "Lich su chat:");
                    break;
                case 3:
                    filterBySender();
                    break;
                case 4:
                    filterByDate();
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Vui long chon chuc nang tu 0 den 4.");
            }
        } while (true);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("================ CHAT APP ================");
        System.out.println("1. Gui tin nhan");
        System.out.println("2. Xem lich su chat");
        System.out.println("3. Loc tin nhan theo nguoi gui");
        System.out.println("4. Loc tin nhan theo ngay");
        System.out.println("0. Thoat");
        System.out.println("==========================================");
    }

    private static void sendMessage() {
        String sender = getStringInput("Nhap ten nguoi gui: ");
        String content = getStringInput("Nhap noi dung tin nhan: ");

        messageManager.add(new Message(sender, content, LocalDateTime.now()));
        System.out.println("Gui tin nhan thanh cong.");
    }

    private static void filterBySender() {
        String sender = getStringInput("Nhap ten nguoi gui can loc: ");
        List<Message> results = messageManager.filterBySender(sender);

        displayMessages(results, "Tin nhan cua " + sender + ":");
    }

    private static void filterByDate() {
        String dateInput = getStringInput("Nhap ngay can loc (dd/MM/yyyy): ");

        try {
            LocalDate date = LocalDate.parse(dateInput, DATE_FORMATTER);
            List<Message> results = messageManager.filterByDate(date);

            displayMessages(results, "Tin nhan trong ngay " + dateInput + ":");
        } catch (DateTimeParseException e) {
            System.out.println("Loi: Ngay phai dung dinh dang dd/MM/yyyy.");
        }
    }

    private static void displayMessages(List<Message> messages, String title) {
        System.out.println(title);

        if (messages.isEmpty()) {
            System.out.println("Khong co tin nhan nao.");
            return;
        }

        for (Message message : messages) {
            System.out.println(message);
        }
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
                System.out.println("Loi: Vui long nhap so nguyen hop le.");
            }
        } while (true);
    }
}
