package session10.ex6.models;

public class Vehicle {
    protected String brand;
    protected int year;

    public Vehicle(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    public void showInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year);
    }

    public void startEngine() {
        System.out.println(brand + " engine starts.");
    }

    public void move() {
        System.out.println(brand + " is moving.");
    }

    public void move(int speed) {
        System.out.println(brand + " is moving at " + speed + " km/h.");
    }
}
