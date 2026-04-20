package session4;

import java.util.Scanner;

public class EmployeeSalaryManagement {
    static void main() {
        Scanner scanner = new Scanner(System.in);

        int employeeCount = inputEmployeeCount(scanner);
        double[][] originalRecords = inputSalaries(scanner, employeeCount);
        double[][] sortedRecords = null;

        while (true) {
            printMainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Danh sach luong goc:");
                    printRecords(originalRecords);
                    break;
                case 2:
                    int sortOrder = inputSortOrder(scanner);
                    sortedRecords = copyRecords(originalRecords);
                    sortSalaries(sortedRecords, sortOrder == 1);
                    System.out.println("Danh sach luong sau khi sap xep:");
                    printRecords(sortedRecords);
                    break;
                case 3:
                    searchSalary(scanner, originalRecords);
                    break;
                case 4:
                    printStatistics(originalRecords);
                    break;
                case 5:
                    System.out.println("Ket thuc chuong trinh!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }

    private static int inputEmployeeCount(Scanner scanner) {
        int employeeCount;

        do {
            System.out.print("Nhap so luong nhan vien: ");
            employeeCount = scanner.nextInt();

            if (employeeCount <= 0) {
                System.out.println("So luong nhan vien phai lon hon 0.");
            }
        } while (employeeCount <= 0);

        return employeeCount;
    }

    private static double[][] inputSalaries(Scanner scanner, int employeeCount) {
        double[][] records = new double[employeeCount][2];

        for (int i = 0; i < employeeCount; i++) {
            records[i][0] = i + 1;
            records[i][1] = inputSalary(scanner, i + 1);
        }

        return records;
    }

    private static double inputSalary(Scanner scanner, int employeeIndex) {
        double salary;

        do {
            System.out.print("Nhap luong nhan vien " + employeeIndex + ": ");
            salary = scanner.nextDouble();

            if (salary < 0) {
                System.out.println("Luong khong hop le. Vui long nhap so khong am.");
            }
        } while (salary < 0);

        return salary;
    }

    private static void printMainMenu() {
        System.out.println("========== MENU QUAN LY LUONG ==========");
        System.out.println("1. Xem danh sach luong");
        System.out.println("2. Sap xep luong");
        System.out.println("3. Tim kiem luong");
        System.out.println("4. Thong ke luong");
        System.out.println("5. Thoat");
        System.out.print("Lua chon cua ban: ");
    }

    private static void printRecords(double[][] records) {
        System.out.println("Danh sach luong:");
        for (int i = 0; i < records.length; i++) {
            System.out.println("Nhan vien " + (int) records[i][0] + ": " + records[i][1]);
        }
    }

    private static int inputSortOrder(Scanner scanner) {
        int choice;

        do {
            System.out.println("Chon kieu sap xep:");
            System.out.println("1. Tang dan");
            System.out.println("2. Giam dan");
            System.out.print("Lua chon cua ban: ");
            choice = scanner.nextInt();

            if (choice != 1 && choice != 2) {
                System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 1 && choice != 2);

        return choice;
    }

    private static double[][] copyRecords(double[][] records) {
        double[][] copiedRecords = new double[records.length][2];

        for (int i = 0; i < records.length; i++) {
            copiedRecords[i][0] = records[i][0];
            copiedRecords[i][1] = records[i][1];
        }

        return copiedRecords;
    }

    private static void sortSalaries(double[][] records, boolean ascending) {
        for (int i = 0; i < records.length - 1; i++) {
            int selectedIndex = i;

            for (int j = i + 1; j < records.length; j++) {
                if (ascending) {
                    if (records[j][1] < records[selectedIndex][1]) {
                        selectedIndex = j;
                    }
                } else {
                    if (records[j][1] > records[selectedIndex][1]) {
                        selectedIndex = j;
                    }
                }
            }

            swapRows(records, i, selectedIndex);
        }
    }

    private static void swapRows(double[][] records, int firstIndex, int secondIndex) {
        double[] temp = records[firstIndex];
        records[firstIndex] = records[secondIndex];
        records[secondIndex] = temp;
    }

    private static void searchSalary(Scanner scanner, double[][] records) {
        System.out.print("Nhap luong can tim: ");
        double target = scanner.nextDouble();

        boolean found = false;
        for (int i = 0; i < records.length; i++) {
            if (Double.compare(records[i][1], target) == 0) {
                System.out.println(
                        "Tim thay trong danh sach goc: nhan vien " + (int) records[i][0] + ", luong " + records[i][1]
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay luong can tim.");
        }
    }

    private static void printStatistics(double[][] records) {
        double totalSalary = 0;
        double maxSalary = records[0][1];
        double minSalary = records[0][1];

        for (int i = 0; i < records.length; i++) {
            totalSalary += records[i][1];

            if (records[i][1] > maxSalary) {
                maxSalary = records[i][1];
            }

            if (records[i][1] < minSalary) {
                minSalary = records[i][1];
            }
        }

        double averageSalary = totalSalary / records.length;
        int aboveAverageCount = 0;

        for (int i = 0; i < records.length; i++) {
            if (records[i][1] > averageSalary) {
                aboveAverageCount++;
            }
        }

        System.out.println("===== THONG KE LUONG =====");
        System.out.println("Tong luong: " + totalSalary);
        System.out.println("Luong trung binh: " + averageSalary);
        System.out.println("Luong cao nhat: " + maxSalary);
        System.out.println("Luong thap nhat: " + minSalary);
        System.out.println("So nhan vien co luong tren trung binh: " + aboveAverageCount);
    }
}
