package session11.ex4.models;

public class Laptop extends Device implements Connectable, Chargeable {
    public Laptop(int id, String name) {
        super(id, name);
    }

    @Override
    public void turnOn() {
        System.out.println(getName() + " is booting.");
    }

    @Override
    public void turnOff() {
        System.out.println(getName() + " is shutting down.");
    }

    @Override
    public void connectWifi() {
        System.out.println(getName() + " is connecting to wifi.");
    }

    @Override
    public void charge() {
        System.out.println(getName() + " is charging.");
    }
}
