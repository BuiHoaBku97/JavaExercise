package session10.ex6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import session10.ex6.models.Car;
import session10.ex6.models.Motorcycle;
import session10.ex6.models.Truck;
import session10.ex6.models.Vehicle;

public class VehicleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Car("Toyota", 2020, "Gasoline"));
        vehicles.add(new Motorcycle("Honda", 2018, "Gasoline"));
        vehicles.add(new Truck("Volvo", 2022, "Diesel"));

        int choice;
        do {
            showMenu();
            System.out.print("Chon chuc nang: ");
            choice = readInt(scanner);

            switch (choice) {
                case 1:
                    showAllVehicles(vehicles);
                    break;
                case 2:
                    testOverriding(vehicles);
                    break;
                case 3:
                    testOverloading(vehicles.get(0));
                    break;
                case 4:
                    testRuntimePolymorphism(vehicles);
                    break;
                case 5:
                    callSpecificMethods(vehicles);
                    break;
                case 6:
                    addNewVehicle(scanner, vehicles);
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
                    break;
            }

            System.out.println();
        } while (choice != 0);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("========= VEHICLE MANAGEMENT MENU =========");
        System.out.println("1. Hien thi thong tin tat ca phuong tien");
        System.out.println("2. Kiem tra Overriding: startEngine()");
        System.out.println("3. Kiem tra Overloading: move()");
        System.out.println("4. Kiem tra da hinh runtime (Vehicle array/list)");
        System.out.println("5. Goi hanh vi dac trung cua tung loai");
        System.out.println("6. Them phuong tien moi");
        System.out.println("0. Thoat");
        System.out.println("===========================================");
    }

    private static int readInt(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            return 0;
        }

        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void showAllVehicles(List<Vehicle> vehicles) {
        System.out.println();
        System.out.println("--- THONG TIN PHUONG TIEN ---");

        for (Vehicle vehicle : vehicles) {
            vehicle.showInfo();
            System.out.println("---------------------");
        }
    }

    private static void testOverriding(List<Vehicle> vehicles) {
        System.out.println();
        System.out.println("--- OVERRIDING: startEngine() ---");

        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    private static void testOverloading(Vehicle vehicle) {
        System.out.println();
        System.out.println("--- OVERLOADING: move() ---");
        vehicle.move();
        vehicle.move(80);
    }

    private static void testRuntimePolymorphism(List<Vehicle> vehicles) {
        System.out.println();
        System.out.println("--- POLYMORPHISM RUNTIME ---");

        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    private static void callSpecificMethods(List<Vehicle> vehicles) {
        System.out.println();
        System.out.println("--- HANH VI DAC TRUNG CUA TUNG LOAI ---");

        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Car) {
                ((Car) vehicle).openTrunk();
            } else if (vehicle instanceof Motorcycle) {
                ((Motorcycle) vehicle).doWheelie();
            } else if (vehicle instanceof Truck) {
                ((Truck) vehicle).loadCargo();
            }
        }
    }

    private static void addNewVehicle(Scanner scanner, List<Vehicle> vehicles) {
        System.out.println();
        System.out.println("--- THEM PHUONG TIEN MOI ---");

        System.out.print("Loai (car/motorcycle/truck): ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Brand: ");
        String brand = scanner.nextLine().trim();

        System.out.print("Year: ");
        int year = readInt(scanner);

        System.out.print("Fuel Type: ");
        String fuelType = scanner.nextLine().trim();

        if (type.equals("car")) {
            vehicles.add(new Car(brand, year, fuelType));
        } else if (type.equals("motorcycle")) {
            vehicles.add(new Motorcycle(brand, year, fuelType));
        } else if (type.equals("truck")) {
            vehicles.add(new Truck(brand, year, fuelType));
        } else {
            System.out.println("Loai phuong tien khong hop le.");
        }
    }
}
