package session11.ex3;

import session11.ex3.models.Employee;
import session11.ex3.models.FullTimeEmployee;
import session11.ex3.models.PartTimeEmployee;

import java.text.NumberFormat;
import java.util.Locale;

public class Test {
    static void main() {
        Employee[] employees = new Employee[5];
        employees[0] = new FullTimeEmployee(1, "Hoa", 10000000);
        employees[1] = new PartTimeEmployee(2, "Vu", 20);
        employees[2] = new FullTimeEmployee(3, "Nguyen", 12000000);
        employees[3] = new FullTimeEmployee(4, "Cam", 7000000);
        employees[4] = new PartTimeEmployee(5, "Le", 25);

        for (Employee employee : employees){
            var salary = employee.calculateSalary();
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.of("vi"));
            employee.showInfo();
            System.out.println("Salary and bonus: " + nf.format(salary) + "\n");
        }
    }
}
