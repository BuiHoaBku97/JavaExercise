package session4;

import java.util.Scanner;

public class StudentGradeManagement {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        int studentCount = inputStudentCount(scanner);
        double[][] originalRecords = inputGrades(scanner, studentCount);
        double[][] sortedRecords = null;
        boolean hasSortedSnapshot = false;
        boolean isAscending = true;

        while (true) {
            printMainMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Danh sach diem goc:");
                    printRecords(originalRecords);
                    break;
                case 2:
                    int sortOrder = inputSortOrder(scanner);
                    int sortMethod = inputSortMethod(scanner);

                    sortedRecords = copyRecords(originalRecords);
                    isAscending = sortOrder == 1;

                    if (sortMethod == 1) {
                        bubbleSort(sortedRecords, isAscending);
                    } else {
                        selectionSort(sortedRecords, isAscending);
                    }

                    hasSortedSnapshot = true;
                    System.out.println("Danh sach diem sau khi sap xep:");
                    printRecords(sortedRecords);
                    break;
                case 3:
                    searchGrade(scanner, originalRecords, sortedRecords, hasSortedSnapshot, isAscending);
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

    private static int inputStudentCount(Scanner scanner) {
        int studentCount;
        do {
            System.out.print("Nhap so luong sinh vien: ");
            studentCount = scanner.nextInt();

            if (studentCount <= 0) {
                System.out.println("So luong sinh vien phai lon hon 0.");
            }
        } while (studentCount <= 0);

        return studentCount;
    }

    private static double[][] inputGrades(Scanner scanner, int studentCount) {
        double[][] records = new double[studentCount][2];

        for (int i = 0; i < studentCount; i++) {
            records[i][0] = i + 1;
            records[i][1] = inputGrade(scanner, i + 1);
        }

        return records;
    }

    private static double inputGrade(Scanner scanner, int studentIndex) {
        double grade;

        do {
            System.out.print("Nhap diem sinh vien " + studentIndex + ": ");
            grade = scanner.nextDouble();

            if (grade < 0 || grade > 10) {
                System.out.println("Diem khong hop le. Vui long nhap trong khoang 0 den 10.");
            }
        } while (grade < 0 || grade > 10);

        return grade;
    }

    private static void printMainMenu() {
        System.out.println("========== MENU QUAN LY DIEM ==========");
        System.out.println("1. Xem tat ca diem");
        System.out.println("2. Sap xep diem");
        System.out.println("3. Tim kiem diem");
        System.out.println("4. Thong ke diem");
        System.out.println("5. Thoat");
        System.out.print("Lua chon cua ban: ");
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

    private static int inputSortMethod(Scanner scanner) {
        int choice;

        do {
            System.out.println("Chon thuat toan sap xep:");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Selection Sort");
            System.out.print("Lua chon cua ban: ");
            choice = scanner.nextInt();

            if (choice != 1 && choice != 2) {
                System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 1 && choice != 2);

        return choice;
    }

    private static void printRecords(double[][] records) {
        for (int i = 0; i < records.length; i++) {
            System.out.println("Sinh vien " + (int) records[i][0] + ": " + records[i][1]);
        }
    }

    private static double[][] copyRecords(double[][] records) {
        double[][] copiedRecords = new double[records.length][2];

        for (int i = 0; i < records.length; i++) {
            copiedRecords[i][0] = records[i][0];
            copiedRecords[i][1] = records[i][1];
        }

        return copiedRecords;
    }

    private static void bubbleSort(double[][] records, boolean ascending) {
        for (int i = 0; i < records.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < records.length - 1 - i; j++) {
                if (shouldSwap(records[j][1], records[j + 1][1], ascending)) {
                    swapRows(records, j, j + 1);
                    swapped = true;
                }
            }

            if (!swapped) {
                return;
            }
        }
    }

    private static void selectionSort(double[][] records, boolean ascending) {
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

    private static boolean shouldSwap(double left, double right, boolean ascending) {
        if (ascending) {
            return left > right;
        }

        return left < right;
    }

    private static void searchGrade(
            Scanner scanner,
            double[][] originalRecords,
            double[][] sortedRecords,
            boolean hasSortedSnapshot,
            boolean isAscending
    ) {
        System.out.print("Nhap diem can tim: ");
        double target = scanner.nextDouble();

        System.out.println("Chon cach tim kiem:");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search");
        System.out.print("Lua chon cua ban: ");
        int searchMethod = scanner.nextInt();

        switch (searchMethod) {
            case 1:
                printLinearSearchResult(originalRecords, target);
                break;
            case 2:
                if (!hasSortedSnapshot) {
                    System.out.println("Chua co danh sach sap xep. Hay sap xep truoc khi dung Binary Search.");
                    return;
                }

                int binaryIndex = binarySearch(sortedRecords, target, isAscending);
                printBinarySearchResult(sortedRecords, binaryIndex);
                break;
            default:
                System.out.println("Lua chon khong hop le!");
        }
    }

    private static void printLinearSearchResult(double[][] records, double target) {
        boolean found = false;

        for (int i = 0; i < records.length; i++) {
            if (Double.compare(records[i][1], target) == 0) {
                System.out.println(
                        "Tim thay diem o danh sach goc: sinh vien " + (int) records[i][0] + ", diem " + records[i][1]
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("Khong tim thay diem can tim.");
        }
    }

    private static int binarySearch(double[][] records, double target, boolean ascending) {
        int left = 0;
        int right = records.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int compare = Double.compare(records[mid][1], target);

            if (compare == 0) {
                return mid;
            }

            if (ascending) {
                if (compare < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (compare < 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;
    }

    private static void printBinarySearchResult(double[][] sortedRecords, int index) {
        if (index == -1) {
            System.out.println("Khong tim thay diem can tim.");
            return;
        }

        System.out.println(
                "Tim thay trong danh sach da sap xep tai index " + index
                        + ": sinh vien " + (int) sortedRecords[index][0]
                        + ", diem " + sortedRecords[index][1]
        );
    }

    private static void printStatistics(double[][] records) {
        double sum = 0;
        double max = records[0][1];
        double min = records[0][1];

        for (int i = 0; i < records.length; i++) {
            sum += records[i][1];
            if (records[i][1] > max) {
                max = records[i][1];
            }
            if (records[i][1] < min) {
                min = records[i][1];
            }
        }

        double average = sum / records.length;
        int aboveAverageCount = 0;

        for (int i = 0; i < records.length; i++) {
            if (records[i][1] > average) {
                aboveAverageCount++;
            }
        }

        System.out.println("===== THONG KE DIEM =====");
        System.out.println("Diem trung binh: " + average);
        System.out.println("Diem cao nhat: " + max);
        System.out.println("Diem thap nhat: " + min);
        System.out.println("So sinh vien co diem tren trung binh: " + aboveAverageCount);
    }
}
