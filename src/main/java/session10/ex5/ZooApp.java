package session10.ex5;

import java.util.Scanner;

import session10.ex5.models.Animal;
import session10.ex5.models.Cat;
import session10.ex5.models.Dog;
import session10.ex5.models.Elephant;
import session10.ex5.models.Mammal;

public class ZooApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Dog dog = new Dog("Buddy", 3, true);
        Cat cat = new Cat("Mimi", 2, true);
        Elephant elephant = new Elephant("Dumbo", 10, false);

        int choice;
        do {
            showMenu();
            System.out.print("Chon chuc nang: ");
            choice = readChoice(scanner);

            switch (choice) {
                case 1:
                    showInheritanceAndSuper(dog, cat, elephant);
                    break;
                case 2:
                    showOverriding(dog, cat, elephant);
                    break;
                case 3:
                    showOverloading(dog, cat, elephant);
                    break;
                case 4:
                    showRuntimePolymorphism(dog, cat, elephant);
                    break;
                case 5:
                    showSpecificMethods(dog, cat, elephant);
                    break;
                case 0:
                    System.out.println("Thoat chuong trinh...");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }

            System.out.println();
        } while (choice != 0);

        scanner.close();
    }

    private static int readChoice(Scanner scanner) {
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

    private static void showMenu() {
        System.out.println("===== ZOO MANAGEMENT MENU =====");
        System.out.println("1. Tao doi tuong va hien thi thong tin");
        System.out.println("2. Kiem tra Overriding: makeSound()");
        System.out.println("3. Kiem tra Overloading: eat()");
        System.out.println("4. Kiem tra da hinh runtime (Animal array)");
        System.out.println("5. Goi phuong thuc dac trung tung loai");
        System.out.println("0. Thoat");
        System.out.println("================================");
    }

    private static void showInheritanceAndSuper(Dog dog, Cat cat, Elephant elephant) {
        System.out.println();
        System.out.println("--- THONG TIN CAC DONG VAT ---");
        dog.showInfo();
        cat.showInfo();
        elephant.showInfo();
    }

    private static void showOverriding(Dog dog, Cat cat, Elephant elephant) {
        System.out.println();
        System.out.println("--- OVERRIDING: makeSound() ---");
        dog.makeSound();
        cat.makeSound();
        elephant.makeSound();
    }

    private static void showOverloading(Dog dog, Cat cat, Elephant elephant) {
        System.out.println();
        System.out.println("--- OVERLOADING: eat() ---");
        dog.eat();
        dog.eat("meat");
        cat.eat("fish");
        elephant.eat();
    }

    private static void showRuntimePolymorphism(Animal dog, Animal cat, Animal elephant) {
        System.out.println();
        System.out.println("--- POLYMORPHISM RUNTIME ---");
        Animal[] animals = {dog, cat, elephant};

        for (Animal animal : animals) {
            animal.makeSound();
        }
    }

    private static void showSpecificMethods(Dog dog, Cat cat, Elephant elephant) {
        System.out.println();
        System.out.println("--- PHUONG THUC RIENG CUA TUNG LOAI ---");
        dog.fetchBall();
        cat.climbTree();
        elephant.sprayWater();
    }
}
