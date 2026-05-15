package session12.ex1.models;

public class AdminStaff extends Staff{
    private Double bonus;

    public AdminStaff(int id, String name, Double baseSalary, Double bonus) {
        super(id, name, baseSalary);
        this.bonus = bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    @Override
    public Double calculateTotalSalary() {
        return super.getBaseSalary() + bonus;
    }
}
