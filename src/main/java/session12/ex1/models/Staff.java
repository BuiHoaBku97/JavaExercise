package session12.ex1.models;

public abstract class Staff {
    private int id;
    private String name;
    private Double baseSalary;

    public Staff(int id, String name, Double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public abstract Double calculateTotalSalary();

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    @Override
    public String toString() {
        return "ID: " + id + "      Name: " + name;
    }
}
