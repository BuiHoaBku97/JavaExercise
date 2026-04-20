package session3;

import java.util.Scanner;

public class StudentManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        double sum = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        while (true) {
            // Menu
            System.out.println("*************** MENU NHAP DIEM ***************");
            System.out.println("1. Nhap diem hoc vien");
            System.out.println("2. Hien thi thong ke");
            System.out.println("3. Thoat");
            System.out.print("Lua chon cua ban: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("Nhap diem (-1 de ket thuc): ");
                        double score = sc.nextDouble();

                        if (score == -1) break;

                        if (score < 0 || score > 10) {
                            System.out.println("Diem khong hop le! Nhap lai.");
                            continue;
                        }

                        // Xep loai
                        if (score < 5) {
                            System.out.println("Xep loai: Yeu");
                        } else if (score < 7) {
                            System.out.println("Xep loai: Trung Binh");
                        } else if (score < 8) {
                            System.out.println("Xep loai: Kha");
                        } else if (score < 9) {
                            System.out.println("Xep loai: Gioi");
                        } else {
                            System.out.println("Xep loai: Xuat sac");
                        }

                        // Cap nhat thong ke
                        count++;
                        sum += score;
                        if (score > max) max = score;
                        if (score < min) min = score;
                    }
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("Chua co du lieu");
                    } else {
                        double avg = sum / count;

                        System.out.println("===== THONG KE =====");
                        System.out.println("So hoc vien: " + count);
                        System.out.println("Diem trung binh: " + avg);
                        System.out.println("Diem cao nhat: " + max);
                        System.out.println("Diem thap nhat: " + min);
                    }
                    break;

                case 3:
                    System.out.println("Ket thuc chuong trinh!");
                    System.exit(0);

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
