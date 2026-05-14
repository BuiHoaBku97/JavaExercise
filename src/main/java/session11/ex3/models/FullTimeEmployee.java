package session11.ex3.models;

public class FullTimeEmployee extends Employee implements IBonusEligible {
    private double salary;

    public FullTimeEmployee(int id, String name, int salary) {
        super(id, name);
        this.salary = salary;
    }

    @Override
    public double calculateSalary() {
        return salary + calculateBonus();
    }

    @Override
    public double calculateBonus() {
        return salary * 0.5;
    }
}
