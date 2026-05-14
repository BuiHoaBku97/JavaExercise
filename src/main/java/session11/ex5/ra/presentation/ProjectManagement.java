package session11.ex5.ra.presentation;

import session11.ex5.ra.entity.Employee;
import session11.ex5.ra.entity.Project;
import session11.ex5.ra.entity.ProjectStatus;
import session11.ex5.ra.entity.Role;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ProjectManagement {
    private static Employee[] arrEmp = new Employee[100];
    private static int empIndex = 0;
    private static Project[] arrProject = new Project[100];
    private static int projectIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            printMainMenu();
            int choice = inputInt(scanner, "Lua chon cua ban: ");
            switch (choice) {
                case 1:
                    employeeMenu(scanner);
                    break;
                case 2:
                    projectMenu(scanner);
                    break;
                case 3:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Vui long chon tu 1 den 3.");
            }
        } while (true);
    }

    private static void printMainMenu() {
        System.out.println("================ QUAN LY DU AN ================");
        System.out.println("1. Quan ly nhan vien");
        System.out.println("2. Quan ly du an");
        System.out.println("3. Thoat");
        System.out.println("================================================");
    }

    private static void employeeMenu(Scanner scanner) {
        do {
            System.out.println("================ QUAN LY NHAN VIEN ================");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Hien thi danh sach nhan vien");
            System.out.println("3. Cap nhat thong tin nhan vien");
            System.out.println("4. Xoa nhan vien");
            System.out.println("5. Tim kiem nhan vien theo ten");
            System.out.println("6. Sap xep nhan vien theo luong giam dan");
            System.out.println("7. Thoat");
            System.out.println("====================================================");
            int choice = inputInt(scanner, "Lua chon cua ban: ");
            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    displayEmployees();
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    searchEmployeeByName(scanner);
                    break;
                case 6:
                    sortEmployeeBySalaryDesc();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Vui long chon tu 1 den 7.");
            }
        } while (true);
    }

    private static void projectMenu(Scanner scanner) {
        do {
            System.out.println("================ QUAN LY DU AN ================");
            System.out.println("1. Them du an");
            System.out.println("2. Hien thi danh sach du an");
            System.out.println("3. Cap nhat thong tin du an");
            System.out.println("4. Xoa du an (chi khi chua co nhan vien tham gia)");
            System.out.println("5. Them nhan vien vao du an");
            System.out.println("6. Tim du an theo ten");
            System.out.println("7. Thong ke so luong nhan vien theo vai tro trong tung du an");
            System.out.println("8. Tim du an dang chay va gan ket thuc nhat");
            System.out.println("9. Thoat");
            System.out.println("================================================");
            int choice = inputInt(scanner, "Lua chon cua ban: ");
            switch (choice) {
                case 1:
                    addProject(scanner);
                    break;
                case 2:
                    displayProjects();
                    break;
                case 3:
                    updateProject(scanner);
                    break;
                case 4:
                    deleteProject(scanner);
                    break;
                case 5:
                    addEmployeeToProject(scanner);
                    break;
                case 6:
                    searchProjectByName(scanner);
                    break;
                case 7:
                    statisticEmployeeRoleByProject();
                    break;
                case 8:
                    findNearestRunningProject();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("Vui long chon tu 1 den 9.");
            }
        } while (true);
    }

    private static void addEmployee(Scanner scanner) {
        arrEmp = ensureEmployeeCapacity();
        Employee employee = new Employee();
        employee.inputData(scanner, arrEmp, empIndex);
        arrEmp[empIndex++] = employee;
        System.out.println("Them nhan vien thanh cong.");
    }

    private static void displayEmployees() {
        if (empIndex == 0) {
            System.out.println("Danh sach nhan vien trong.");
            return;
        }
        for (int i = 0; i < empIndex; i++) {
            arrEmp[i].displayData();
        }
    }

    private static void updateEmployee(Scanner scanner) {
        int index = findEmployeeIndexById(inputString(scanner, "Nhap ma nhan vien can cap nhat: "));
        if (index == -1) {
            System.out.println("Khong tim thay nhan vien.");
            return;
        }

        System.out.println("1. Cap nhat ten");
        System.out.println("2. Cap nhat vai tro");
        System.out.println("3. Cap nhat luong");
        int choice = inputInt(scanner, "Lua chon cua ban: ");
        switch (choice) {
            case 1:
                arrEmp[index].setEmployeeName(inputEmployeeName(scanner));
                break;
            case 2:
                arrEmp[index].setRole(inputRole(scanner));
                break;
            case 3:
                arrEmp[index].setSalary(inputPositiveDouble(scanner, "Nhap luong moi: "));
                break;
            default:
                System.out.println("Lua chon khong hop le.");
                return;
        }
        System.out.println("Cap nhat nhan vien thanh cong.");
    }

    private static void deleteEmployee(Scanner scanner) {
        int index = findEmployeeIndexById(inputString(scanner, "Nhap ma nhan vien can xoa: "));
        if (index == -1) {
            System.out.println("Khong tim thay nhan vien.");
            return;
        }

        String deletedId = arrEmp[index].getEmployeeId();
        for (int i = index; i < empIndex - 1; i++) {
            arrEmp[i] = arrEmp[i + 1];
        }
        arrEmp[--empIndex] = null;
        removeEmployeeFromProjects(deletedId);
        System.out.println("Xoa nhan vien thanh cong.");
    }

    private static void searchEmployeeByName(Scanner scanner) {
        String keyword = inputString(scanner, "Nhap ten nhan vien can tim: ").toLowerCase();
        boolean isFound = false;
        for (int i = 0; i < empIndex; i++) {
            if (arrEmp[i].getEmployeeName().toLowerCase().contains(keyword)) {
                arrEmp[i].displayData();
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Khong tim thay nhan vien phu hop.");
        }
    }

    private static void sortEmployeeBySalaryDesc() {
        Arrays.sort(arrEmp, 0, empIndex, Comparator.comparingDouble(Employee::getSalary).reversed());
        System.out.println("Da sap xep nhan vien theo luong giam dan.");
    }

    private static void addProject(Scanner scanner) {
        arrProject = ensureProjectCapacity();
        Project project = new Project();
        project.inputData(scanner, arrProject, projectIndex, arrEmp, empIndex);
        arrProject[projectIndex++] = project;
        System.out.println("Them du an thanh cong.");
    }

    private static void displayProjects() {
        if (projectIndex == 0) {
            System.out.println("Danh sach du an trong.");
            return;
        }
        for (int i = 0; i < projectIndex; i++) {
            arrProject[i].displayData();
            System.out.println("----------------------------------------");
        }
    }

    private static void updateProject(Scanner scanner) {
        int index = findProjectIndexById(inputString(scanner, "Nhap ma du an can cap nhat: "));
        if (index == -1) {
            System.out.println("Khong tim thay du an.");
            return;
        }

        System.out.println("1. Cap nhat ten du an");
        System.out.println("2. Cap nhat ngay bat dau");
        System.out.println("3. Cap nhat ngay ket thuc");
        System.out.println("4. Cap nhat trang thai");
        int choice = inputInt(scanner, "Lua chon cua ban: ");
        switch (choice) {
            case 1:
                arrProject[index].setProjectName(inputProjectName(scanner, index));
                break;
            case 2:
                LocalDate startDate = inputDate(scanner, "Nhap ngay bat dau moi (yyyy-MM-dd): ");
                if (startDate.isAfter(arrProject[index].getEndDate())) {
                    System.out.println("Ngay bat dau khong duoc sau ngay ket thuc.");
                    return;
                }
                arrProject[index].setStartDate(startDate);
                break;
            case 3:
                LocalDate endDate = inputDate(scanner, "Nhap ngay ket thuc moi (yyyy-MM-dd): ");
                if (endDate.isBefore(arrProject[index].getStartDate())) {
                    System.out.println("Ngay ket thuc phai lon hon hoac bang ngay bat dau.");
                    return;
                }
                arrProject[index].setEndDate(endDate);
                break;
            case 4:
                arrProject[index].setStatus(inputStatus(scanner));
                break;
            default:
                System.out.println("Lua chon khong hop le.");
                return;
        }
        System.out.println("Cap nhat du an thanh cong.");
    }

    private static void deleteProject(Scanner scanner) {
        int index = findProjectIndexById(inputString(scanner, "Nhap ma du an can xoa: "));
        if (index == -1) {
            System.out.println("Khong tim thay du an.");
            return;
        }
        if (arrProject[index].getEmployeeCount() > 0) {
            System.out.println("Chi duoc xoa du an chua co nhan vien tham gia.");
            return;
        }
        for (int i = index; i < projectIndex - 1; i++) {
            arrProject[i] = arrProject[i + 1];
        }
        arrProject[--projectIndex] = null;
        System.out.println("Xoa du an thanh cong.");
    }

    private static void addEmployeeToProject(Scanner scanner) {
        int projectPosition = findProjectIndexById(inputString(scanner, "Nhap ma du an: "));
        if (projectPosition == -1) {
            System.out.println("Khong tim thay du an.");
            return;
        }
        int employeePosition = findEmployeeIndexById(inputString(scanner, "Nhap ma nhan vien can them: "));
        if (employeePosition == -1) {
            System.out.println("Khong tim thay nhan vien.");
            return;
        }

        Project project = arrProject[projectPosition];
        Employee employee = arrEmp[employeePosition];
        Employee[] currentEmployees = project.getEmployees();
        if (currentEmployees == null) {
            currentEmployees = new Employee[0];
        }
        for (Employee currentEmployee : currentEmployees) {
            if (currentEmployee.getEmployeeId().equals(employee.getEmployeeId())) {
                System.out.println("Nhan vien da co trong du an.");
                return;
            }
        }

        Employee[] newEmployees = Arrays.copyOf(currentEmployees, currentEmployees.length + 1);
        newEmployees[newEmployees.length - 1] = employee;
        project.setEmployees(newEmployees);
        System.out.println("Them nhan vien vao du an thanh cong.");
    }

    private static void searchProjectByName(Scanner scanner) {
        String keyword = inputString(scanner, "Nhap ten du an can tim: ").toLowerCase();
        boolean isFound = false;
        for (int i = 0; i < projectIndex; i++) {
            if (arrProject[i].getProjectName().toLowerCase().contains(keyword)) {
                arrProject[i].displayData();
                isFound = true;
            }
        }
        if (!isFound) {
            System.out.println("Khong tim thay du an phu hop.");
        }
    }

    private static void statisticEmployeeRoleByProject() {
        if (projectIndex == 0) {
            System.out.println("Danh sach du an trong.");
            return;
        }
        for (int i = 0; i < projectIndex; i++) {
            Project project = arrProject[i];
            System.out.println("Du an: " + project.getProjectName());
            for (Role role : Role.values()) {
                int count = 0;
                Employee[] employees = project.getEmployees();
                if (employees != null) {
                    for (Employee employee : employees) {
                        if (employee.getRole() == role) {
                            count++;
                        }
                    }
                }
                System.out.printf("  %s: %d%n", role, count);
            }
        }
    }

    private static void findNearestRunningProject() {
        Project nearestProject = null;
        for (int i = 0; i < projectIndex; i++) {
            Project project = arrProject[i];
            if (project.getStatus() == ProjectStatus.RUNNING
                    && (nearestProject == null || project.getEndDate().isBefore(nearestProject.getEndDate()))) {
                nearestProject = project;
            }
        }
        if (nearestProject == null) {
            System.out.println("Khong co du an dang chay.");
            return;
        }
        System.out.println("Du an dang chay gan ket thuc nhat:");
        nearestProject.displayData();
    }

    private static Employee[] ensureEmployeeCapacity() {
        return empIndex == arrEmp.length ? Arrays.copyOf(arrEmp, arrEmp.length * 2) : arrEmp;
    }

    private static Project[] ensureProjectCapacity() {
        return projectIndex == arrProject.length ? Arrays.copyOf(arrProject, arrProject.length * 2) : arrProject;
    }

    private static int findEmployeeIndexById(String employeeId) {
        for (int i = 0; i < empIndex; i++) {
            if (arrEmp[i].getEmployeeId().equals(employeeId)) {
                return i;
            }
        }
        return -1;
    }

    private static int findProjectIndexById(String projectId) {
        for (int i = 0; i < projectIndex; i++) {
            if (arrProject[i].getProjectId().equals(projectId)) {
                return i;
            }
        }
        return -1;
    }

    private static void removeEmployeeFromProjects(String deletedId) {
        for (int i = 0; i < projectIndex; i++) {
            Employee[] employees = arrProject[i].getEmployees();
            if (employees == null || employees.length == 0) {
                continue;
            }
            Employee[] remainingEmployees = Arrays.stream(employees)
                    .filter(employee -> !employee.getEmployeeId().equals(deletedId))
                    .toArray(Employee[]::new);
            arrProject[i].setEmployees(remainingEmployees);
        }
    }

    private static String inputEmployeeName(Scanner scanner) {
        do {
            String input = inputString(scanner, "Nhap ten nhan vien moi (6-30 ky tu): ");
            if (input.length() >= 6 && input.length() <= 30) {
                return input;
            }
            System.out.println("Ten nhan vien phai tu 6 den 30 ky tu.");
        } while (true);
    }

    private static String inputProjectName(Scanner scanner, int currentIndex) {
        do {
            String input = inputString(scanner, "Nhap ten du an moi (10-50 ky tu, duy nhat): ");
            if (input.length() < 10 || input.length() > 50) {
                System.out.println("Ten du an phai tu 10 den 50 ky tu.");
                continue;
            }
            boolean isDuplicate = false;
            for (int i = 0; i < projectIndex; i++) {
                if (i != currentIndex && arrProject[i].getProjectName().equalsIgnoreCase(input)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                return input;
            }
            System.out.println("Ten du an da ton tai.");
        } while (true);
    }

    private static Role inputRole(Scanner scanner) {
        do {
            String input = inputString(scanner, "Nhap vai tro moi (DEV, TESTER, PM, BA): ").toUpperCase();
            try {
                return Role.valueOf(input);
            } catch (IllegalArgumentException ex) {
                System.out.println("Vai tro khong hop le.");
            }
        } while (true);
    }

    private static ProjectStatus inputStatus(Scanner scanner) {
        do {
            String input = inputString(scanner, "Nhap trang thai moi (PLANNING, RUNNING, FINISHED): ").toUpperCase();
            try {
                return ProjectStatus.valueOf(input);
            } catch (IllegalArgumentException ex) {
                System.out.println("Trang thai khong hop le.");
            }
        } while (true);
    }

    private static LocalDate inputDate(Scanner scanner, String message) {
        do {
            try {
                return LocalDate.parse(inputString(scanner, message));
            } catch (DateTimeParseException ex) {
                System.out.println("Ngay khong hop le. Dinh dang dung: yyyy-MM-dd.");
            }
        } while (true);
    }

    private static double inputPositiveDouble(Scanner scanner, String message) {
        do {
            try {
                double input = Double.parseDouble(inputString(scanner, message));
                if (input > 0) {
                    return input;
                }
                System.out.println("Gia tri phai lon hon 0.");
            } catch (NumberFormatException ex) {
                System.out.println("Vui long nhap so hop le.");
            }
        } while (true);
    }

    private static int inputInt(Scanner scanner, String message) {
        do {
            try {
                return Integer.parseInt(inputString(scanner, message));
            } catch (NumberFormatException ex) {
                System.out.println("Vui long nhap so nguyen hop le.");
            }
        } while (true);
    }

    private static String inputString(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }
}
