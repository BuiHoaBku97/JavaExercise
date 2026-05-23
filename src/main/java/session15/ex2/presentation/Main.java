package session15.ex2.presentation;

import session15.ex2.exceptions.InvalidCreditsException;
import session15.ex2.models.Subject;
import session15.ex2.models.SubjectManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final SubjectManager<Subject> subjectManager = new SubjectManager<>();

    public static void main(String[] args) {
        initializeSubjects();

        do {
            printMenu();
            int choice = getIntInput("Lựa chọn của bạn: ");

            switch (choice) {
                case 1:
                    displaySubjects(subjectManager.getAll(), "Danh sách môn học:");
                    break;
                case 2:
                    addSubject();
                    break;
                case 3:
                    deleteSubject();
                    break;
                case 4:
                    searchSubjectByName();
                    break;
                case 5:
                    filterSubjectsByCredits();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Vui lòng chọn chức năng từ 0 đến 5.");
            }
        } while (true);
    }

    private static void initializeSubjects() {
        subjectManager.add(new Subject("JAVA", "Java Core", 4, LocalDate.of(2026, 1, 10)));
        subjectManager.add(new Subject("SQL", "Database SQL", 3, LocalDate.of(2026, 2, 15)));
        subjectManager.add(new Subject("WEB", "Web Backend", 5, LocalDate.of(2026, 3, 20)));
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Chọn chức năng:");
        System.out.println("1. Hiển thị danh sách môn học");
        System.out.println("2. Thêm môn học");
        System.out.println("3. Xóa môn học");
        System.out.println("4. Tìm kiếm môn học theo tên");
        System.out.println("5. Lọc môn học theo tín chỉ");
        System.out.println("0. Thoát");
    }

    private static void addSubject() {
        String code = getStringInput("Nhập mã môn học: ");
        String name = getStringInput("Nhập tên môn học: ");
        int credits = getCreditsInput("Nhập số tín chỉ: ");
        LocalDate startDate = getDateInput("Nhập ngày bắt đầu (yyyy-MM-dd): ");

        subjectManager.add(new Subject(code, name, credits, startDate));
        System.out.println("Thêm môn học thành công.");
    }

    private static void deleteSubject() {
        String code = getStringInput("Nhập mã môn học cần xóa: ");
        if (subjectManager.delete(code)) {
            System.out.println("Xóa môn học thành công.");
        } else {
            System.out.println("Lỗi: Không tìm thấy môn học cần xóa.");
        }
    }

    private static void searchSubjectByName() {
        String name = getStringInput("Nhập tên môn học cần tìm: ");
        Optional<Subject> subject = subjectManager.searchByName(name);

        if (subject.isPresent()) {
            System.out.println("Môn học phù hợp:");
            System.out.println(subject.get());
        } else {
            System.out.println("Không có môn học phù hợp");
        }
    }

    private static void filterSubjectsByCredits() {
        List<Subject> subjects = subjectManager.filterByCreditsGreaterThan(3);
        displaySubjects(subjects, "Danh sách môn học có tín chỉ > 3:");
    }

    private static void displaySubjects(List<Subject> subjects, String title) {
        System.out.println(title);
        if (subjects.isEmpty()) {
            System.out.println("Danh sách môn học trống.");
            return;
        }

        subjects.forEach(System.out::println);
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
                System.out.println("Lỗi: Vui lòng nhập số nguyên hợp lệ!");
            }
        } while (true);
    }

    private static int getCreditsInput(String message) {
        do {
            try {
                int credits = Integer.parseInt(getStringInput(message));
                validateCredits(credits);
                return credits;
            } catch (NumberFormatException | InvalidCreditsException e) {
                System.out.println("Lỗi: Số tín chỉ không hợp lệ!");
            }
        } while (true);
    }

    private static void validateCredits(int credits) throws InvalidCreditsException {
        if (credits <= 0 || credits > 10) {
            throw new InvalidCreditsException("Số tín chỉ phải nằm trong khoảng 1 đến 10");
        }
    }

    private static LocalDate getDateInput(String message) {
        do {
            try {
                return LocalDate.parse(getStringInput(message), DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Lỗi: Ngày bắt đầu phải đúng định dạng yyyy-MM-dd!");
            }
        } while (true);
    }
}
