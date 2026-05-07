package session8;

public class Student{
    private int id;
    private String name;
    private double gpa;

    public static int countStudent;
    private final double SCORE_FACTOR = 0.25;

    public Student(int id, String name, double gpa) {
        this();
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public Student(){
        countStudent += 1;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                ", SCORE_FACTOR=" + SCORE_FACTOR +
                '}';
    }
}