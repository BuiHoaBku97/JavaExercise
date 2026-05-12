package session10.ex6.models;

public class Car extends MotorVehicle {
    public Car(String brand, int year, String fuelType) {
        super(brand, year, fuelType);
    }

    @Override
    public void startEngine() {
        System.out.println(brand + " (Car) engine starts: Vroom Vroom!");
    }

    public void openTrunk() {
        System.out.println(brand + " trunk is opening...");
    }
}
