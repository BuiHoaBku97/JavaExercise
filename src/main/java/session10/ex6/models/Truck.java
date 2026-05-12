package session10.ex6.models;

public class Truck extends MotorVehicle {
    public Truck(String brand, int year, String fuelType) {
        super(brand, year, fuelType);
    }

    @Override
    public void startEngine() {
        System.out.println(brand + " (Truck) engine starts: RRRRRR!");
    }

    public void loadCargo() {
        System.out.println(brand + " is loading cargo...");
    }
}
