package session9;

public class Student {
    private int id;
    private String fullName;
    private int age;
    private double gpa;

    private static int count;
    private final double MIN_GPA = 0.0;
    private final double MAX_GPA = 4.0;

    public Student(int id, String fullName, int age, double gpa) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gpa = gpa;
        count++;
    }

    public void printInfo() {
        System.out.println("ID: " + id);
        System.out.println("Full name: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
        System.out.println("Min GPA: " + MIN_GPA);
        System.out.println("Max GPA: " + MAX_GPA);
        System.out.println("----");
    }

    public static int getCount() {
        return count;
    }
}
