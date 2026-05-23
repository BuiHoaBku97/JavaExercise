package session14.ex4.models;

public class InvalidPhoneNumber {
    private final String value;
    private final String reason;

    public InvalidPhoneNumber(String value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public String getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }
}
