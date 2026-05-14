package session11.ex2.models;

public abstract class Payment {
    private double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void pay();

    public void printAmount() {
        System.out.println("Amount: " + amount);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
