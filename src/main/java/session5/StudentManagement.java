package session5;

import java.util.Arrays;
import java.util.Scanner;

public class StudentManagement {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        String[] students = new String[100];
        int count = 0;

        while (true) {
            printMainMenu();
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    if (count == students.length) {
                        System.out.println("Danh sach da day, khong the them moi.");
                        break;
                    }

                    System.out.print("Nhap ten sinh vien: ");
                    String newName = scanner.nextLine().trim();
                    if (newName.isEmpty()) {
                        System.out.println("Ten sinh vien khong hop le.");
                        break;
                    }

                    students[count] = newName;
                    count++;
                    System.out.println("Da them: " + newName);
                    break;
                case 2:
                    printStudents(students, count);
                    break;
                case 3:
                    System.out.print("Nhap tu khoa can tim: ");
                    String keyword = scanner.nextLine().trim();
                    findStudentsByKeyword(students, count, keyword);
                    break;
                case 4:
                    System.out.print("Nhap chu cai bat dau: ");
                    String input = scanner.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Chu cai khong hop le.");
                    } else {
                        countStudentsStartingWith(students, count, input.charAt(0));
                    }
                    break;
                case 5:
                    sortStudents(students, count);
                    System.out.println("Danh sach sau khi sap xep:");
                    printStudents(students, count);
                    break;
                case 6:
                    System.out.println("Ket thuc chuong trinh!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("========== MENU QUAN LY SINH VIEN ==========");
        System.out.println("1. Them ten sinh vien");
        System.out.println("2. Hien thi danh sach");
        System.out.println("3. Tim ten sinh vien chua tu khoa");
        System.out.println("4. Dem so sinh vien co ten bat dau bang chu cai");
        System.out.println("5. Sap xep danh sach ten");
        System.out.println("6. Thoat");
        System.out.print("Lua chon cua ban: ");
    }

    private static void printStudents(String[] students, int count) {
        if (count == 0) {
            System.out.println("Danh sach sinh vien trong.");
            return;
        }

        System.out.println("Danh sach sinh vien:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + students[i]);
        }
    }

    private static void findStudentsByKeyword(String[] students, int count, String keyword) {
        if (keyword.isEmpty()) {
            System.out.println("Tu khoa khong hop le.");
            return;
        }

        System.out.println("Ket qua tim kiem:");
        String lowerKeyword = keyword.toLowerCase();
        for (int i = 0; i < count; i++) {
            if (students[i].toLowerCase().contains(lowerKeyword)) {
                System.out.println(students[i]);
            }
        }
    }

    private static void countStudentsStartingWith(String[] students, int count, char firstLetter) {
        int matchedCount = 0;
        char lowerFirstLetter = Character.toLowerCase(firstLetter);

        for (int i = 0; i < count; i++) {
            if (!students[i].isEmpty() && Character.toLowerCase(students[i].charAt(0)) == lowerFirstLetter) {
                matchedCount++;
            }
        }

        System.out.println("So sinh vien co ten bat dau bang '" + firstLetter + "': " + matchedCount);
    }

    private static void sortStudents(String[] students, int count) {
        String[] sortedStudents = Arrays.copyOf(students, count);
        Arrays.sort(sortedStudents, String.CASE_INSENSITIVE_ORDER);

        for (int i = 0; i < count; i++) {
            students[i] = sortedStudents[i];
        }
    }
}
