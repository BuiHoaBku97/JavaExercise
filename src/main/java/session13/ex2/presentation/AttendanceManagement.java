package session13.ex2.presentation;

import session13.ex2.models.AttendanceManager;
import session13.ex2.models.Student;

import java.util.Scanner;

public class AttendanceManagement {
    private static AttendanceManager attendanceManager = new AttendanceManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            printMenu();
            int option = getIntInput("Lựa chọn của bạn: ");

            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    attendanceManager.display();
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
        System.out.println("*************** MENU QUẢN LÝ ĐIỂM DANH ***************");
        System.out.println("1. Thêm sinh viên");
        System.out.println("2. Sửa sinh viên");
        System.out.println("3. Xóa sinh viên");
        System.out.println("4. Hiển thị danh sách sinh viên");
        System.out.println("5. Thoát");
    }

    private static void addStudent() {
        int id = inputUniqueId();
        String name = getNextInput("Nhập tên sinh viên: ");

        attendanceManager.add(new Student(id, name));
        System.out.println("Sinh viên đã được thêm thành công.");
    }

    private static void updateStudent() {
        if (attendanceManager.isEmpty()) {
            System.out.println("Danh sách sinh viên trống!");
            return;
        }

        attendanceManager.display();
        int id = getIntInput("Nhập id sinh viên cần sửa: ");
        int index = attendanceManager.findIndexById(id);

        if (index == -1) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }

        String name = getNextInput("Nhập tên mới sinh viên: ");
        attendanceManager.update(index, new Student(id, name));
        System.out.println("Sinh viên đã được sửa thành công.");
    }

    private static void deleteStudent() {
        if (attendanceManager.isEmpty()) {
            System.out.println("Danh sách sinh viên trống!");
            return;
        }

        attendanceManager.display();
        int id = getIntInput("Nhập id sinh viên cần xóa: ");
        int index = attendanceManager.findIndexById(id);

        if (index == -1) {
            System.out.println("Không tìm thấy sinh viên!");
            return;
        }

        attendanceManager.delete(index);
        System.out.println("Đã xóa thành công sinh viên !");
    }

    private static int inputUniqueId() {
        do {
            int id = getIntInput("Nhập id sinh viên : ");
            if (attendanceManager.findIndexById(id) == -1) {
                return id;
            }
            System.out.println("ID sinh viên đã tồn tại!");
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
                    System.out.println("Vui lòng nhập số nguyên dương.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ.");
            }
        } while (true);
    }
}
