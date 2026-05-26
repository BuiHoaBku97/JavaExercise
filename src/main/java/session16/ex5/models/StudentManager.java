package session16.ex5.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentManager<T extends Student> {
    private final ArrayList<T> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    public void add(T student) {
        students.add(student);
    }

    public List<T> getAll() {
        return students;
    }

    public Map<String, Long> countByMajor() {
        return students
                .stream().collect( Collectors.groupingBy(Student::getMajor, Collectors.counting() ));
    }

    public List<Map.Entry<String, Long>> getMajorStatisticsSorted() {
        return countByMajor()
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                                               .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());
    }
}
