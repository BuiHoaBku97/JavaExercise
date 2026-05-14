package session11.ex4;

import session11.ex4.models.Chargeable;
import session11.ex4.models.Connectable;
import session11.ex4.models.Device;
import session11.ex4.models.Laptop;
import session11.ex4.models.SmartPhone;
import session11.ex4.models.Television;

public class Test {
    public static void main(String[] args) {
        Device[] devices = new Device[3];
        devices[0] = new SmartPhone(1, "iPhone 15");
        devices[1] = new Laptop(2, "Dell XPS");
        devices[2] = new Television(3, "Samsung TV");

        for (Device device : devices) {
            device.showInfo();
            device.turnOn();

            if (device instanceof Connectable connectableDevice) {
                connectableDevice.connectWifi();
            }

            if (device instanceof Chargeable chargeableDevice) {
                chargeableDevice.charge();
            }

            device.turnOff();
            System.out.println();
        }
    }
}
