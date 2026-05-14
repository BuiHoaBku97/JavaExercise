package session11.ex2.models;

public class CreditCardPayment extends Payment implements Refundable {
    public CreditCardPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.println("Paying by credit card.");
    }

    @Override
    public void refund() {
        System.out.println("Refunding to credit card.");
    }
}
