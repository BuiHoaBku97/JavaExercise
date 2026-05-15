package session12.ex3.presentation;

import session12.ex3.models.Coffee;
import session12.ex3.models.Drink;
import session12.ex3.models.FruitTea;
import session12.ex3.models.IPromotion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeShopManagement {
    private static List<Drink> drinks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            printMainMenu();
            int option = getIntInput("Choose your option: ");

            switch (option) {
                case 1:
                    addDrink();
                    break;
                case 2:
                    displayMenu();
                    break;
                case 3:
                    applyPromotion();
                    break;
                case 4:
                    deleteDrink();
                    break;
                case 5:
                    displayStatistics();
                    break;
                case 6:
                    System.out.println("Exit application!");
                    return;
                default:
                    System.out.println("Please choose an option from 1 to 6.");
            }
        } while (true);
    }

    private static void printMainMenu() {
        System.out.println("=========== Coffee Shop Management ===========");
        System.out.println("1. Add drink");
        System.out.println("2. Display menu");
        System.out.println("3. Apply promotion code");
        System.out.println("4. Delete drink");
        System.out.println("5. Statistics");
        System.out.println("6. Exit");
    }

    private static void addDrink() {
        int type;
        do {
            type = getIntInput("Choose drink type: 1-Coffee, 2-Fruit tea: ");
            if (type == 1 || type == 2) {
                break;
            }
            System.out.println("Please choose 1 or 2.");
        } while (true);

        int id = getIntInput("Enter drink id: ");
        String name = getNextInput("Enter drink name: ");
        double price = getDoubleInput("Enter drink price: ");

        if (type == 1) {
            drinks.add(new Coffee(id, name, price));
            return;
        }

        drinks.add(new FruitTea(id, name, price));
    }

    private static void displayMenu() {
        if (drinks.isEmpty()) {
            System.out.println("Drink menu is empty!");
            return;
        }

        System.out.println("Drink menu:");
        for (Drink drink : drinks) {
            System.out.println("ID: " + drink.getId());
            System.out.println("Name: " + drink.getName());
            System.out.print("Preparation: ");
            drink.prepare();
            System.out.printf("Price: %.2f%n", drink.getPrice());
        }
    }

    private static void applyPromotion() {
        if (drinks.isEmpty()) {
            System.out.println("Drink menu is empty!");
            return;
        }

        double percentage = getDoubleInput("Enter discount percentage: ");
        for (IPromotion drink : drinks) {
            drink.applyDiscount(percentage);
        }

        System.out.println("Promotion applied successfully!");
    }

    private static void deleteDrink() {
        if (drinks.isEmpty()) {
            System.out.println("Drink menu is empty!");
            return;
        }

        int id = getIntInput("Enter drink id: ");
        Drink drink = findById(id);

        if (drink == null) {
            System.out.println("Drink not found!");
            return;
        }

        drinks.remove(drink);
        System.out.println("Drink deleted successfully!");
    }

    private static void displayStatistics() {
        if (drinks.isEmpty()) {
            System.out.println("Drink menu is empty!");
            return;
        }

        double totalPrice = 0;
        for (Drink drink : drinks) {
            totalPrice += drink.getPrice();
        }

        System.out.printf("Average drink price: %.2f%n", totalPrice / drinks.size());
    }

    private static Drink findById(int id) {
        for (Drink drink : drinks) {
            if (drink.getId() == id) {
                return drink;
            }
        }

        return null;
    }

    private static String getNextInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String message) {
        do {
            try {
                int value = Integer.parseInt(getNextInput(message));
                if (value < 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        } while (true);
    }

    private static double getDoubleInput(String message) {
        do {
            try {
                double value = Double.parseDouble(getNextInput(message));
                if (value < 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } while (true);
    }
}
