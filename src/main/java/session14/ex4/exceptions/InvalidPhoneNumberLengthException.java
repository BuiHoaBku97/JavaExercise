package session14.ex4.exceptions;

public class InvalidPhoneNumberLengthException extends Exception {
    public InvalidPhoneNumberLengthException(String message) {
        super(message);
    }

    public static void validate(String phoneNumber) throws InvalidPhoneNumberLengthException {
        if (containsWhitespace(phoneNumber)) {
            throw new InvalidPhoneNumberLengthException("Không được chứa khoảng trắng");
        }

        if (phoneNumber.length() != 10) {
            throw new InvalidPhoneNumberLengthException("Sai độ dài");
        }

        if (!phoneNumber.matches("\\d+")) {
            throw new InvalidPhoneNumberLengthException("Chứa ký tự không hợp lệ");
        }
    }

    private static boolean containsWhitespace(String phoneNumber) {
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (Character.isWhitespace(phoneNumber.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
