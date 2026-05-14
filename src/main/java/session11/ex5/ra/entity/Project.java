package session11.ex5.ra.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Project {
    private String projectId;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Employee[] employees;
    private ProjectStatus status;

    public Project() {
    }

    public Project(String projectId, String projectName, LocalDate startDate, LocalDate endDate,
                   Employee[] employees, ProjectStatus status) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employees = employees;
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Project[] arrProject, int index, Employee[] arrEmp, int empIndex) {
        this.projectId = inputProjectId(scanner, arrProject, index);
        this.projectName = inputProjectName(scanner, arrProject, index);
        this.startDate = inputDate(scanner, "Nhap ngay bat dau (yyyy-MM-dd): ");
        this.endDate = inputEndDate(scanner);
        this.employees = inputEmployees(scanner, arrEmp, empIndex);
        this.status = inputStatus(scanner);
    }

    public void displayData() {
        System.out.printf("Ma DA: %s | Ten DA: %s | Bat dau: %s | Ket thuc: %s | Trang thai: %s%n",
                projectId, projectName, startDate, endDate, status);
        System.out.println("Nhan vien tham gia:");
        if (employees == null || employees.length == 0) {
            System.out.println("  Chua co nhan vien.");
            return;
        }
        for (Employee employee : employees) {
            System.out.print("  ");
            employee.displayData();
        }
    }

    public int getEmployeeCount() {
        return employees == null ? 0 : employees.length;
    }

    private String inputProjectId(Scanner scanner, Project[] arrProject, int index) {
        do {
            System.out.print("Nhap ma du an (bat dau bang P, 5 ky tu): ");
            String input = scanner.nextLine().trim();
            if (!input.matches("P\\w{4}")) {
                System.out.println("Ma du an khong hop le.");
                continue;
            }
            if (isDuplicateProjectId(input, arrProject, index)) {
                System.out.println("Ma du an da ton tai.");
                continue;
            }
            return input;
        } while (true);
    }

    private boolean isDuplicateProjectId(String id, Project[] arrProject, int index) {
        for (int i = 0; i < index; i++) {
            if (arrProject[i] != null && arrProject[i].getProjectId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private String inputProjectName(Scanner scanner, Project[] arrProject, int index) {
        do {
            System.out.print("Nhap ten du an (10-50 ky tu, duy nhat): ");
            String input = scanner.nextLine().trim();
            if (input.length() < 10 || input.length() > 50) {
                System.out.println("Ten du an phai tu 10 den 50 ky tu.");
                continue;
            }
            if (isDuplicateProjectName(input, arrProject, index)) {
                System.out.println("Ten du an da ton tai.");
                continue;
            }
            return input;
        } while (true);
    }

    private boolean isDuplicateProjectName(String name, Project[] arrProject, int index) {
        for (int i = 0; i < index; i++) {
            if (arrProject[i] != null && arrProject[i].getProjectName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private LocalDate inputDate(Scanner scanner, String message) {
        do {
            System.out.print(message);
            try {
                return LocalDate.parse(scanner.nextLine().trim());
            } catch (DateTimeParseException ex) {
                System.out.println("Ngay khong hop le. Dinh dang dung: yyyy-MM-dd.");
            }
        } while (true);
    }

    private LocalDate inputEndDate(Scanner scanner) {
        do {
            LocalDate input = inputDate(scanner, "Nhap ngay ket thuc (yyyy-MM-dd): ");
            if (!input.isBefore(startDate)) {
                return input;
            }
            System.out.println("Ngay ket thuc phai lon hon hoac bang ngay bat dau.");
        } while (true);
    }

    private Employee[] inputEmployees(Scanner scanner, Employee[] arrEmp, int empIndex) {
        if (empIndex == 0) {
            System.out.println("Chua co nhan vien de gan vao du an.");
            return new Employee[0];
        }

        for (int i = 0; i < empIndex; i++) {
            System.out.printf("%d. ", i + 1);
            arrEmp[i].displayData();
        }

        int count;
        do {
            System.out.print("Nhap so luong nhan vien tham gia du an: ");
            try {
                count = Integer.parseInt(scanner.nextLine().trim());
                if (count >= 0 && count <= empIndex) {
                    break;
                }
                System.out.printf("So luong phai tu 0 den %d.%n", empIndex);
            } catch (NumberFormatException ex) {
                System.out.println("Vui long nhap so nguyen hop le.");
            }
        } while (true);

        Employee[] selectedEmployees = new Employee[count];
        int selectedCount = 0;
        while (selectedCount < count) {
            System.out.printf("Nhap ma nhan vien thu %d: ", selectedCount + 1);
            String employeeId = scanner.nextLine().trim();
            Employee employee = findEmployeeById(employeeId, arrEmp, empIndex);
            if (employee == null) {
                System.out.println("Khong tim thay nhan vien.");
                continue;
            }
            if (containsEmployee(selectedEmployees, selectedCount, employeeId)) {
                System.out.println("Nhan vien da duoc chon.");
                continue;
            }
            selectedEmployees[selectedCount++] = employee;
        }
        return selectedEmployees;
    }

    private Employee findEmployeeById(String employeeId, Employee[] arrEmp, int empIndex) {
        for (int i = 0; i < empIndex; i++) {
            if (arrEmp[i].getEmployeeId().equals(employeeId)) {
                return arrEmp[i];
            }
        }
        return null;
    }

    private boolean containsEmployee(Employee[] employees, int count, String employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return true;
            }
        }
        return false;
    }

    private ProjectStatus inputStatus(Scanner scanner) {
        do {
            System.out.print("Nhap trang thai (PLANNING, RUNNING, FINISHED): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return ProjectStatus.valueOf(input);
            } catch (IllegalArgumentException ex) {
                System.out.println("Trang thai khong hop le.");
            }
        } while (true);
    }
}
