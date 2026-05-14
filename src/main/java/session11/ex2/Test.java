package session11.ex2;

import session11.ex2.models.CashPayment;
import session11.ex2.models.CreditCardPayment;
import session11.ex2.models.EWalletPayment;
import session11.ex2.models.Payment;
import session11.ex2.models.Refundable;

public class Test {
    public static void main(String[] args) {
        Payment[] payments = new Payment[3];
        payments[0] = new CashPayment(100000);
        payments[1] = new CreditCardPayment(250000);
        payments[2] = new EWalletPayment(150000);

        for (Payment payment : payments) {
            payment.printAmount();
            payment.pay();

            if (payment instanceof Refundable refundablePayment) {
                refundablePayment.refund();
            }

            System.out.println();
        }
    }
}
