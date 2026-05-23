package session14.ex4.presentation;

import session14.ex4.exceptions.InvalidPhoneNumberLengthException;
import session14.ex4.models.InvalidPhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneNumberValidationApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> validPhoneNumbers = new ArrayList<>();
        List<InvalidPhoneNumber> invalidPhoneNumbers = new ArrayList<>();

        System.out.print("Nhập danh sách số điện thoại, phân tách bằng dấu phẩy: ");
        String input = scanner.nextLine();

        String[] phoneNumbers = input.split(",");
        for (String phoneNumber : phoneNumbers) {
            try {
                InvalidPhoneNumberLengthException.validate(phoneNumber);
                validPhoneNumbers.add(phoneNumber);
            } catch (InvalidPhoneNumberLengthException e) {
                invalidPhoneNumbers.add(new InvalidPhoneNumber(phoneNumber, e.getMessage()));
            }
        }

        displayValidPhoneNumbers(validPhoneNumbers);
        displayInvalidPhoneNumbers(invalidPhoneNumbers);

        scanner.close();
    }

    private static void displayValidPhoneNumbers(List<String> phoneNumbers) {
        System.out.println("Số điện thoại hợp lệ:");
        for (String phoneNumber : phoneNumbers) {
            System.out.println("- " + phoneNumber);
        }
    }

    private static void displayInvalidPhoneNumbers(List<InvalidPhoneNumber> phoneNumbers) {
        System.out.println();
        System.out.println("Số điện thoại không hợp lệ:");
        for (InvalidPhoneNumber phoneNumber : phoneNumbers) {
            System.out.println("- " + phoneNumber.getValue() + " : " + phoneNumber.getReason());
        }
    }
}
