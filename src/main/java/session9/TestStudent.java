package session9;

public class TestStudent {
    public static void main(String[] args) {
        Student student1 = new Student(1, "Nguyen Van A", 20, 3.2);
        Student student2 = new Student(2, "Tran Thi B", 21, 3.6);
        Student student3 = new Student(3, "Le Van C", 19, 3.8);

        student1.printInfo();
        student2.printInfo();
        student3.printInfo();

        System.out.println("Tong so sinh vien duoc tao: " + Student.getCount());
    }
}
