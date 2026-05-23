package session14.ex6.exceptions;

public class ProductNotFoundInCartException extends Exception {
    public ProductNotFoundInCartException(String message) {
        super(message);
    }
}
