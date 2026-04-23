package session6;

import java.util.Scanner;

public class StudentGradeManagement {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        double[] scores = new double[0];
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            int choice = inputInt(scanner, "Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    scores = inputScores(scanner);
                    break;
                case 2:
                    printScores(scores);
                    break;
                case 3:
                    printAverage(scores);
                    break;
                case 4:
                    printHighestAndLowest(scores);
                    break;
                case 5:
                    printPassedAndFailed(scores);
                    break;
                case 6:
                    printSortedScores(scores);
                    break;
                case 7:
                    printExcellentAndGood(scores);
                    break;
                case 8:
                    isRunning = false;
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1 đến 8.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("****************QUẢN LÝ ĐIỂM SV****************");
        System.out.println("1. Nhập danh sách điểm sinh viên");
        System.out.println("2. In danh sách điểm");
        System.out.println("3. Tính điểm trung bình của các sinh viên");
        System.out.println("4. Tìm điểm cao nhất và thấp nhất");
        System.out.println("5. Đếm số lượng sinh viên đạt và trượt");
        System.out.println("6. Sắp xếp điểm tăng dần");
        System.out.println("7. Thống kê số lượng sinh viên giỏi và xuất sắc");
        System.out.println("8. Thoát");
    }

    private static double[] inputScores(Scanner scanner) {
        int size;
        do {
            size = inputInt(scanner, "Nhập số lượng sinh viên: ");
            if (size <= 0) {
                System.out.println("Số lượng sinh viên phải lớn hơn 0.");
            }
        } while (size <= 0);

        double[] scores = new double[size];
        for (int i = 0; i < size; i++) {
            scores[i] = inputScore(scanner, "Nhập điểm sinh viên thứ " + (i + 1) + ": ");
        }
        return scores;
    }

    private static void printScores(double[] scores) {
        if (!hasData(scores)) {
            System.out.println("Danh sách điểm đang trống.");
            return;
        }

        System.out.println("Danh sách điểm sinh viên:");
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("Sinh viên %d: %.2f%n", i + 1, scores[i]);
        }
    }

    private static void printAverage(double[] scores) {
        if (!hasData(scores)) {
            System.out.println("Danh sách điểm đang trống.");
            return;
        }

        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        System.out.printf("Điểm trung bình: %.2f%n", sum / scores.length);
    }

    private static void printHighestAndLowest(double[] scores) {
        if (!hasData(scores)) {
            System.out.println("Danh sách điểm đang trống.");
            return;
        }

        double max = scores[0];
        double min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
            if (scores[i] < min) {
                min = scores[i];
            }
        }

        System.out.printf("Điểm cao nhất: %.2f%n", max);
        System.out.printf("Điểm thấp nhất: %.2f%n", min);
    }

    private static void printPassedAndFailed(double[] scores) {
        if (!hasData(scores)) {
            System.out.println("Danh sách điểm đang trống.");
            return;
        }

        int passed = 0;
        int failed = 0;
        for (double score : scores) {
            if (score >= 5) {
                passed++;
            } else {
                failed++;
            }
        }

        System.out.println("Số sinh viên đạt: " + passed);
        System.out.println("Số sinh viên trượt: " + failed);
    }

    private static void printSortedScores(double[] scores) {
        if (!hasData(scores)) {
            System.out.println("Danh sách điểm đang trống.");
            return;
        }

        double[] sortedScores = new double[scores.length];
        for (int i = 0; i < scores.length; i++) {
            sortedScores[i] = scores[i];
        }

        for (int i = 0; i < sortedScores.length - 1; i++) {
            for (int j = i + 1; j < sortedScores.length; j++) {
                if (sortedScores[i] > sortedScores[j]) {
                    double temp = sortedScores[i];
                    sortedScores[i] = sortedScores[j];
                    sortedScores[j] = temp;
                }
            }
        }

        System.out.println("Danh sách điểm sau khi sắp xếp tăng dần:");
        for (int i = 0; i < sortedScores.length; i++) {
            System.out.printf("Sinh viên %d: %.2f%n", i + 1, sortedScores[i]);
        }
    }

    private static void printExcellentAndGood(double[] scores) {
        if (!hasData(scores)) {
            System.out.println("Danh sách điểm đang trống.");
            return;
        }

        int excellentAndGood = 0;
        for (double score : scores) {
            if (score >= 8) {
                excellentAndGood++;
            }
        }

        System.out.println("Số sinh viên giỏi và xuất sắc: " + excellentAndGood);
    }

    private static boolean hasData(double[] scores) {
        return scores != null && scores.length > 0;
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

    private static double inputScore(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                double score = scanner.nextDouble();
                scanner.nextLine();
                if (score >= 0 && score <= 10) {
                    return score;
                }
                System.out.println("Điểm phải nằm trong khoảng từ 0 đến 10.");
            } else {
                System.out.println("Vui lòng nhập điểm hợp lệ.");
                scanner.nextLine();
            }
        }
    }
}
