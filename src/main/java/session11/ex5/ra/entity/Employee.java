package session11.ex5.ra.entity;

import java.util.Scanner;

public class Employee {
    private String employeeId;
    private String employeeName;
    private Role role;
    private double salary;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, Role role, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void inputData(Scanner scanner, Employee[] arrEmp, int index) {
        this.employeeId = inputEmployeeId(scanner, arrEmp, index);
        this.employeeName = inputEmployeeName(scanner);
        this.role = inputRole(scanner);
        this.salary = inputSalary(scanner);
    }

    public void displayData() {
        System.out.printf("Ma NV: %s | Ten NV: %s | Vai tro: %s | Luong: %.2f%n",
                employeeId, employeeName, role, salary);
    }

    private String inputEmployeeId(Scanner scanner, Employee[] arrEmp, int index) {
        do {
            System.out.print("Nhap ma nhan vien (bat dau bang E, 5 ky tu): ");
            String input = scanner.nextLine().trim();
            if (!input.matches("E\\w{4}")) {
                System.out.println("Ma nhan vien khong hop le.");
                continue;
            }
            if (isDuplicateEmployeeId(input, arrEmp, index)) {
                System.out.println("Ma nhan vien da ton tai.");
                continue;
            }
            return input;
        } while (true);
    }

    private boolean isDuplicateEmployeeId(String id, Employee[] arrEmp, int index) {
        for (int i = 0; i < index; i++) {
            if (arrEmp[i] != null && arrEmp[i].getEmployeeId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private String inputEmployeeName(Scanner scanner) {
        do {
            System.out.print("Nhap ten nhan vien (6-30 ky tu): ");
            String input = scanner.nextLine().trim();
            if (input.length() >= 6 && input.length() <= 30) {
                return input;
            }
            System.out.println("Ten nhan vien phai tu 6 den 30 ky tu.");
        } while (true);
    }

    private Role inputRole(Scanner scanner) {
        do {
            System.out.print("Nhap vai tro (DEV, TESTER, PM, BA): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Role.valueOf(input);
            } catch (IllegalArgumentException ex) {
                System.out.println("Vai tro khong hop le.");
            }
        } while (true);
    }

    private double inputSalary(Scanner scanner) {
        do {
            System.out.print("Nhap luong (> 0): ");
            try {
                double input = Double.parseDouble(scanner.nextLine().trim());
                if (input > 0) {
                    return input;
                }
                System.out.println("Luong phai lon hon 0.");
            } catch (NumberFormatException ex) {
                System.out.println("Vui long nhap so hop le.");
            }
        } while (true);
    }
}
