package session6;

import java.util.Scanner;
import java.util.regex.Pattern;

public class VehicleNumberManagement {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        String[] licensePlates = new String[0];
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            int choice = inputInt(scanner, "Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    licensePlates = addLicensePlates(scanner, licensePlates);
                    break;
                case 2:
                    printLicensePlates(licensePlates);
                    break;
                case 3:
                    searchExactLicensePlate(scanner, licensePlates);
                    break;
                case 4:
                    searchByProvinceCode(scanner, licensePlates);
                    break;
                case 5:
                    sortAscending(licensePlates);
                    System.out.println("Danh sách biển số xe sau khi sắp xếp tăng dần:");
                    printLicensePlates(licensePlates);
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1 đến 6.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("**************** QUẢN LÝ BIỂN SỐ XE ****************");
        System.out.println("1. Thêm các biển số xe");
        System.out.println("2. Hiển thị danh sách biển số xe");
        System.out.println("3. Tìm kiếm biển số xe");
        System.out.println("4. Tìm biển số xe theo mã tỉnh");
        System.out.println("5. Sắp xếp biển số xe tăng dần");
        System.out.println("6. Thoát");
    }

    private static String[] addLicensePlates(Scanner scanner, String[] currentLicensePlates) {
        int quantity;
        do {
            quantity = inputInt(scanner, "Nhập số lượng biển số xe cần thêm: ");
            if (quantity <= 0) {
                System.out.println("Số lượng phải lớn hơn 0.");
            }
        } while (quantity <= 0);

        String[] updatedLicensePlates = new String[currentLicensePlates.length + quantity];
        for (int i = 0; i < currentLicensePlates.length; i++) {
            updatedLicensePlates[i] = currentLicensePlates[i];
        }

        for (int i = 0; i < quantity; i++) {
            updatedLicensePlates[currentLicensePlates.length + i] =
                    inputLicensePlate(scanner, "Nhập biển số xe thứ " + (currentLicensePlates.length + i + 1) + ": ");
        }

        return updatedLicensePlates;
    }

    private static void printLicensePlates(String[] licensePlates) {
        if (!hasData(licensePlates)) {
            System.out.println("Danh sách biển số xe đang trống.");
            return;
        }

        StringBuilder builder = new StringBuilder("Danh sách biển số xe:\n");
        for (int i = 0; i < licensePlates.length; i++) {
            builder.append(i + 1)
                    .append(". ")
                    .append(licensePlates[i])
                    .append('\n');
        }
        System.out.print(builder);
    }

    private static void searchExactLicensePlate(Scanner scanner, String[] licensePlates) {
        if (!hasData(licensePlates)) {
            System.out.println("Danh sách biển số xe đang trống.");
            return;
        }

        String target = inputLicensePlate(scanner, "Nhập biển số xe cần tìm: ");
        int foundIndex = linearSearch(licensePlates, target);

        if (foundIndex >= 0) {
            System.out.println("Tìm thấy biển số xe tại vị trí " + (foundIndex + 1) + ": " + licensePlates[foundIndex]);
        } else {
            System.out.println("Không tìm thấy biển số xe: " + target);
        }
    }

    private static void searchByProvinceCode(Scanner scanner, String[] licensePlates) {
        if (!hasData(licensePlates)) {
            System.out.println("Danh sách biển số xe đang trống.");
            return;
        }

        String provinceCode = inputProvinceCode(scanner);
        StringBuffer buffer = new StringBuffer();
        int count = 0;

        for (int i = 0; i < licensePlates.length; i++) {
            if (getProvinceCode(licensePlates[i]).equals(provinceCode)) {
                count++;
                buffer.append(count)
                        .append(". ")
                        .append(licensePlates[i])
                        .append('\n');
            }
        }

        if (count == 0) {
            System.out.println("Không có biển số xe nào thuộc mã tỉnh " + provinceCode + ".");
        } else {
            System.out.println("Các biển số xe thuộc mã tỉnh " + provinceCode + ":");
            System.out.print(buffer);
        }
    }

    private static void sortAscending(String[] licensePlates) {
        if (!hasData(licensePlates)) {
            System.out.println("Danh sách biển số xe đang trống.");
            return;
        }

        for (int i = 0; i < licensePlates.length - 1; i++) {
            for (int j = i + 1; j < licensePlates.length; j++) {
                if (licensePlates[i].compareTo(licensePlates[j]) > 0) {
                    String temp = licensePlates[i];
                    licensePlates[i] = licensePlates[j];
                    licensePlates[j] = temp;
                }
            }
        }
    }

    private static int linearSearch(String[] licensePlates, String target) {
        for (int i = 0; i < licensePlates.length; i++) {
            if (licensePlates[i].equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    private static String getProvinceCode(String licensePlate) {
        return licensePlate.substring(0, 2);
    }

    private static boolean hasData(String[] licensePlates) {
        return licensePlates != null && licensePlates.length > 0;
    }

    private static int inputInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }
            System.out.println("Vui lòng nhập số nguyên hợp lệ.");
            scanner.nextLine();
        }
    }

    private static String inputLicensePlate(Scanner scanner, String message) {
        Pattern pattern = Pattern.compile("^\\d{2}[A-Z]-\\d{3}\\.\\d{2}$");

        while (true) {
            System.out.print(message);
            String licensePlate = scanner.nextLine().trim().toUpperCase();
            if (pattern.matcher(licensePlate).matches()) {
                return licensePlate;
            }
            System.out.println("Biển số không hợp lệ. Định dạng đúng ví dụ: 30F-123.45");
        }
    }

    private static String inputProvinceCode(Scanner scanner) {
        while (true) {
            System.out.print("Nhập mã tỉnh cần tìm (2 chữ số): ");
            String provinceCode = scanner.nextLine().trim();
            if (provinceCode.matches("\\d{2}")) {
                return provinceCode;
            }
            System.out.println("Mã tỉnh phải gồm đúng 2 chữ số.");
        }
    }
}
