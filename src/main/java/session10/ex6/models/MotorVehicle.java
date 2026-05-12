package session10.ex6.models;

public class MotorVehicle extends Vehicle {
    protected String fuelType;

    public MotorVehicle(String brand, int year, String fuelType) {
        super(brand, year);
        this.fuelType = fuelType;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Fuel Type: " + fuelType);
    }
}
