package session12.ex1.models;

public class Lecturer extends Staff{
    private Double teachingHours;
    private static final Double hourSalary = 200000.0;

    public void setTeachingHours(Double teachingHours) {
        this.teachingHours = teachingHours;
    }

    public Lecturer(int id, String name, Double baseSalary, Double teachingHours) {
        super(id, name, baseSalary);
        this.teachingHours = teachingHours;
    }

    @Override
    public Double calculateTotalSalary() {
        return super.getBaseSalary() + teachingHours * hourSalary;
    }
}
