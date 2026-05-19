package session13.ex2.models;

import java.util.ArrayList;
import java.util.List;

public class AttendanceManager implements Manage<Student> {
    private List<Student> students = new ArrayList<>();

    @Override
    public void add(Student item) {
        students.add(item);
    }

    @Override
    public void update(int index, Student item) {
        students.set(index, item);
    }

    @Override
    public void delete(int index) {
        students.remove(index);
    }

    @Override
    public void display() {
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống!");
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + ". ID : " + student.getId() + " , Tên sinh viên: " + student.getName());
        }
    }

    public int findIndexById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                return i;
            }
        }

        return -1;
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}
