package session11.ex2.models;

public class EWalletPayment extends Payment implements Refundable {
    public EWalletPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.println("Paying by e-wallet.");
    }

    @Override
    public void refund() {
        System.out.println("Refunding to e-wallet.");
    }
}
