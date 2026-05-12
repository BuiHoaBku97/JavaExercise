package session10.ex6.models;

public class Motorcycle extends MotorVehicle {
    public Motorcycle(String brand, int year, String fuelType) {
        super(brand, year, fuelType);
    }

    @Override
    public void startEngine() {
        System.out.println(brand + " (Motorcycle) engine starts: Brum Brum!");
    }

    public void doWheelie() {
        System.out.println(brand + " is doing a wheelie!");
    }
}
