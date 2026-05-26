package session16.ex5.presentation;

import session16.ex5.models.Student;
import session16.ex5.models.StudentManager;

import java.util.List;
import java.util.Map;

public class Main {
    private static final StudentManager<Student> studentManager = new StudentManager<>();

    public static void main(String[] args) {
        initializeStudents();

        displayStudents();
        displayMajorStatistics();
    }

    private static void initializeStudents() {
        studentManager.add(new Student("Nguyen Van An", "IT", 8.5));
        studentManager.add(new Student("Tran Thi Binh", "Marketing", 7.8));
        studentManager.add(new Student("Le Minh Cuong", "IT", 9.0));
        studentManager.add(new Student("Pham Thu Dung", "Business", 8.1));
        studentManager.add(new Student("Hoang Gia Huy", "IT", 7.4));
        studentManager.add(new Student("Do Thanh Lam", "Design", 8.7));
        studentManager.add(new Student("Vu Ngoc Mai", "Marketing", 8.3));
        studentManager.add(new Student("Bui Quang Nam", "Business", 7.6));
        studentManager.add(new Student("Dang Ha Phuong", "IT", 9.2));
        studentManager.add(new Student("Ngo Tuan Kiet", "Design", 7.9));
    }

    private static void displayStudents() {
        System.out.println("Danh sach sinh vien:");
        studentManager.getAll().forEach(System.out::println);
    }

    private static void displayMajorStatistics() {
        System.out.println();
        System.out.println("Thong ke sinh vien theo chuyen nganh:");

        List<Map.Entry<String, Long>> statistics = studentManager.getMajorStatisticsSorted();
        for (Map.Entry<String, Long> entry : statistics) {
            System.out.println("Chuyen nganh: " + entry.getKey() + " - So luong: " + entry.getValue());
        }
    }
}
