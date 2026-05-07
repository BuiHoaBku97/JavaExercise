package session8;

import java.util.Scanner;

public class TestStudent {
    static void main() {
        int option = 0;
        Student[] students = new Student[3];

        do{
            option = printMenu();

            switch (option){
                case 1:
                    for (int i = 0; i < 3; i++){
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Nhap thong tin sinh vien " + (i + 1));
                        System.out.print("Ten: ");
                        var name = scanner.nextLine();
                        System.out.print("Gpa: ");
                        var gpa = scanner.nextDouble();
                        students[i] = new Student(i, name, gpa);
                    }
                    break;
                case 2:
                    if(Student.countStudent == 0 ){
                        System.out.println("Khong co sinh vien");
                        break;
                    }

                    System.out.println("DS sinh vien");
                    for (Student student : students){
                        System.out.println(student);
                    }

                    break;
                case 3:
                    if(Student.countStudent == 0  ){
                        System.out.println("Khong co sinh vien");
                        break;
                    }

                    double maxGPA = students[0].getGpa();
                    for (Student student : students){
                        if ( student.getGpa() > maxGPA){
                            maxGPA = student.getGpa();
                        }
                    }

                    for (Student student : students){
                        if ( student.getGpa() == maxGPA){
                            System.out.println(student);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Tong so sinh vien da tao: " + Student.countStudent);
                    break;
                case 0:
                    System.out.println("Da thoat chuong trinh");
                    return;
            }
        }
        while (option >= 0 && option < 5);
    }

    private static int printMenu(){
        System.out.println("===MENU SINH VIEN===");
        System.out.println("1. Nhap danh sach sinh vien(3)");
        System.out.println("2. In danh sach sinh vien");
        System.out.println("3. Tim sinh vien GPA cao nhat");
        System.out.println("4. In tong so sinh vien da tao");
        System.out.println("0. Thoat");
        System.out.print("Lua chon cua ban: ");

        Scanner scanner = new Scanner(System.in);
        var option = scanner.nextInt();
        return option;
    }
}
