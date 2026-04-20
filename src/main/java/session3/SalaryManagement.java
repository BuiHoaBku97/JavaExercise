package session3;

import java.util.Scanner;

public class SalaryManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double totalSalary = 0;
        double totalBonus = 0;
        int count = 0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        while (true) {
            System.out.println("*************** MENU NHAP LUONG ***************");
            System.out.println("1. Nhap luong nhan vien");
            System.out.println("2. Hien thi thong ke");
            System.out.println("3. Tinh tong tien thuong");
            System.out.println("4. Thoat");
            System.out.print("Lua chon cua ban: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("Nhap luong (-1 de ket thuc): ");
                        double salary = sc.nextDouble();

                        if (salary == -1) break;

                        if (salary < 0 || salary > 500_000_000) {
                            System.out.println("Luong khong hop le!");
                            continue;
                        }

                        // Phan loai
                        if (salary < 5_000_000) {
                            System.out.println("Thu nhap thap");
                        } else if (salary < 15_000_000) {
                            System.out.println("Thu nhap trung binh");
                        } else if (salary < 50_000_000) {
                            System.out.println("Thu nhap kha");
                        } else {
                            System.out.println("Thu nhap cao");
                        }

                        // Tinh bonus ngay tai day
                        double rate;
                        if (salary < 5_000_000) rate = 0.05;
                        else if (salary < 15_000_000) rate = 0.10;
                        else if (salary < 50_000_000) rate = 0.15;
                        else if (salary < 100_000_000) rate = 0.20;
                        else rate = 0.25;

                        double bonus = salary * rate;

                        // Cong don
                        totalSalary += salary;
                        totalBonus += bonus;
                        count++;

                        if (salary > max) max = salary;
                        if (salary < min) min = salary;
                    }
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("Chua co du lieu");
                    } else {
                        System.out.println("===== THONG KE =====");
                        System.out.println("So nhan vien: " + count);
                        System.out.println("Luong trung binh: " + (totalSalary / count));
                        System.out.println("Luong cao nhat: " + max);
                        System.out.println("Luong thap nhat: " + min);
                        System.out.println("Tong luong: " + totalSalary);
                    }
                    break;

                case 3:
                    if (count == 0) {
                        System.out.println("Chua co du lieu");
                    } else {
                        System.out.println("Tong tien thuong: " + totalBonus);
                    }
                    break;

                case 4:
                    System.out.println("Ket thuc chuong trinh!");
                    System.exit(0);

                default:
                    System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
