package session11.ex4.models;

public abstract class Device {
    private int id;
    private String name;

    public Device(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void turnOn();

    public abstract void turnOff();

    public void showInfo() {
        System.out.println("Device: " + id + " - " + name);
    }

    public String getName() {
        return name;
    }
}
