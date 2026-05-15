package session12.ex2.presentation;

import session12.ex2.models.Asset;
import session12.ex2.models.Computer;
import session12.ex2.models.NetworkDevice;

import java.util.Scanner;

public class AssetManagement {
    private static Asset[] assets = new Asset[100];
    private static int totalAsset = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            printMainMenu();
            int option = getIntInput("Choose your option: ");

            switch (option) {
                case 1:
                    addAsset();
                    break;
                case 2:
                    displayReport();
                    break;
                case 3:
                    searchAsset();
                    break;
                case 4:
                    updatePurchasePrice();
                    break;
                case 5:
                    System.out.println("Exit application!");
                    return;
                default:
                    System.out.println("Please choose an option from 1 to 5.");
            }
        } while (true);
    }

    private static void printMainMenu() {
        System.out.println("=========== Asset Management ===========");
        System.out.println("1. Add asset");
        System.out.println("2. Display report");
        System.out.println("3. Search asset");
        System.out.println("4. Update purchase price");
        System.out.println("5. Exit");
    }

    private static void addAsset() {
        if (totalAsset == assets.length) {
            System.out.println("Asset list is full!");
            return;
        }

        int type;
        do {
            type = getIntInput("Choose asset type: 1-Computer, 2-Network device: ");
            if (type == 1 || type == 2) {
                break;
            }
            System.out.println("Please choose 1 or 2.");
        } while (true);

        String assetCode = inputUniqueAssetCode();
        String name = getNextInput("Enter asset name: ");
        Double purchasePrice = getDoubleInput("Enter purchase price: ");

        if (type == 1) {
            String ram = getNextInput("Enter RAM: ");
            String cpu = getNextInput("Enter CPU: ");
            assets[totalAsset++] = new Computer(assetCode, name, purchasePrice, ram, cpu);
            return;
        }

        int numberOfPorts = getIntInput("Enter number of ports: ");
        assets[totalAsset++] = new NetworkDevice(assetCode, name, purchasePrice, numberOfPorts);
    }

    private static String inputUniqueAssetCode() {
        do {
            String assetCode = getNextInput("Enter asset code: ");
            if (search(assetCode) == null) {
                return assetCode;
            }
            System.out.println("Asset code already exists!");
        } while (true);
    }

    private static void displayReport() {
        if (totalAsset == 0) {
            System.out.println("Asset list is empty!");
            return;
        }

        System.out.println("All assets:");
        for (int i = 0; i < totalAsset; i++) {
            System.out.println(assets[i]);
            Asset.showValue(assets[i]);
        }
    }

    private static void searchAsset() {
        int option;
        do {
            System.out.println("=========== Search Asset ===========");
            System.out.println("1. Search by asset code");
            System.out.println("2. Search by purchase price");
            System.out.println("3. Back");
            option = getIntInput("Choose your option: ");

            switch (option) {
                case 1:
                    searchByAssetCode();
                    return;
                case 2:
                    searchByPurchasePrice();
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Please choose an option from 1 to 3.");
            }
        } while (true);
    }

    private static void searchByAssetCode() {
        String assetCode = getNextInput("Enter asset code: ");
        Asset asset = search(assetCode);

        if (asset == null) {
            System.out.println("Asset not found!");
            return;
        }

        System.out.println(asset);
        Asset.showValue(asset);
    }

    private static void searchByPurchasePrice() {
        Double purchasePrice = getDoubleInput("Enter minimum purchase price: ");
        Asset[] results = search(purchasePrice);

        if (results.length == 0) {
            System.out.println("No assets found!");
            return;
        }

        System.out.println("Search results:");
        for (Asset asset : results) {
            System.out.println(asset);
            Asset.showValue(asset);
        }
    }

    private static Asset search(String assetCode) {
        for (int i = 0; i < totalAsset; i++) {
            if (assets[i].getAssetCode().equalsIgnoreCase(assetCode)) {
                return assets[i];
            }
        }

        return null;
    }

    private static Asset[] search(Double purchasePrice) {
        int count = 0;
        for (int i = 0; i < totalAsset; i++) {
            if (assets[i].getPurchasePrice() > purchasePrice) {
                count++;
            }
        }

        Asset[] results = new Asset[count];
        int index = 0;
        for (int i = 0; i < totalAsset; i++) {
            if (assets[i].getPurchasePrice() > purchasePrice) {
                results[index++] = assets[i];
            }
        }

        return results;
    }

    private static void updatePurchasePrice() {
        String assetCode = getNextInput("Enter asset code: ");
        Asset asset = search(assetCode);

        if (asset == null) {
            System.out.println("Asset not found!");
            return;
        }

        Double newPurchasePrice = getDoubleInput("Enter new purchase price: ");
        asset.setPurchasePrice(newPurchasePrice);
        System.out.println("Purchase price updated successfully!");
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

    private static Double getDoubleInput(String message) {
        do {
            try {
                Double value = Double.parseDouble(getNextInput(message));
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
