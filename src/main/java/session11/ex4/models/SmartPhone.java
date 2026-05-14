package session11.ex4.models;

public class SmartPhone extends Device implements Connectable, Chargeable {
    public SmartPhone(int id, String name) {
        super(id, name);
    }

    @Override
    public void turnOn() {
        System.out.println(getName() + " is turning on.");
    }

    @Override
    public void turnOff() {
        System.out.println(getName() + " is turning off.");
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
