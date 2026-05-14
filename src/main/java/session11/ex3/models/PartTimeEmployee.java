package session11.ex3.models;

public class PartTimeEmployee extends Employee{
    private int workingHour;

    public PartTimeEmployee(int id, String name, int workingHour) {
        super(id, name);
        this.workingHour = workingHour;
    }

    @Override
    public double calculateSalary() {
        return workingHour * 80000;
    }
}
