package session13.ex5.presentation;

import session13.ex5.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        do {
            printMenu();
            int option = getIntInput("Lựa chọn của bạn: ");

            switch (option) {
                case 1:
                    addStudents();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudentByName();
                    break;
                case 4:
                    classifyStudentsByGpa();
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
        System.out.println("1. Nhập danh sách sinh viên");
        System.out.println("2. Hiển thị danh sách sinh viên");
        System.out.println("3. Tìm kiếm sinh viên theo tên");
        System.out.println("4. Phân loại sinh viên theo GPA");
        System.out.println("0. Thoát chương trình");
        System.out.println("==============================================");
    }

    private static void addStudents() {
        int quantity;
        do {
            quantity = getIntInput("Nhập số lượng sinh viên muốn thêm: ");
            if (quantity >= 5) {
                break;
            }
            System.out.println("Vui lòng nhập ít nhất 5 sinh viên.");
        } while (true);

        for (int i = 0; i < quantity; i++) {
            System.out.println("Nhập thông tin sinh viên thứ " + (i + 1) + ":");
            String name = getRequiredInput("Nhập họ tên sinh viên: ");
            double gpa = getGpaInput("Nhập GPA: ");
            students.add(new Student(nextId++, name, gpa));
        }

        System.out.println("Đã thêm danh sách sinh viên thành công.");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống!");
            return;
        }

        System.out.println("Danh sách sinh viên:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudentByName() {
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống!");
            return;
        }

        String keyword = getRequiredInput("Nhập tên sinh viên cần tìm: ").toLowerCase();
        boolean found = false;

        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword)) {
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sinh viên!");
        }
    }

    private static void classifyStudentsByGpa() {
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống!");
            return;
        }

        System.out.println("Phân loại sinh viên theo GPA:");
        for (Student student : students) {
            System.out.println(student + ", Xếp loại: " + getRank(student.getGpa()));
        }
    }

    private static String getRank(double gpa) {
        if (gpa >= 8.5) {
            return "Xuất sắc";
        }
        if (gpa >= 7.0) {
            return "Giỏi";
        }
        if (gpa >= 5.5) {
            return "Khá";
        }
        return "Trung bình / Yếu";
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

    private static double getGpaInput(String message) {
        do {
            try {
                double value = Double.parseDouble(getNextInput(message));
                if (value < 0 || value > 10) {
                    System.out.println("GPA phải nằm trong khoảng từ 0 đến 10.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập GPA hợp lệ.");
            }
        } while (true);
    }
}
