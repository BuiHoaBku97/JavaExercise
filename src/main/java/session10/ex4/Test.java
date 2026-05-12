package session10.ex4;

public class Test {
    public static void main(String[] args) {
        Car car = new Car();

        car.accelerate();
        car.printStatus();

        car.accelerate(20);
        car.printStatus();

        car.accelerate(15, 3);
        car.printStatus();
    }
}
