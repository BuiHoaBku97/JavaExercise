package session13.ex3.models;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InvoiceManager implements Manage<Invoice> {
    private List<Invoice> invoices = new ArrayList<>();

    @Override
    public void add(Invoice item) {
        invoices.add(item);
    }

    @Override
    public void update(int index, Invoice item) {
        invoices.set(index, item);
    }

    @Override
    public void delete(int index) {
        invoices.remove(index);
    }

    @Override
    public void display() {
        if (invoices.isEmpty()) {
            System.out.println("Danh sách hóa đơn trống!");
            return;
        }

        for (int i = 0; i < invoices.size(); i++) {
            Invoice invoice = invoices.get(i);
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.of("vi"));

            System.out.printf("%d. ID : %d , Mã hóa đơn: %s, Số tiền: %s\n",
                    i + 1, i + 1, invoice.getCode(), nf.format(invoice.getAmount()));
        }
    }

    public boolean isValidIndex(int index) {
        return index >= 0 && index < invoices.size();
    }

    public boolean isEmpty() {
        return invoices.isEmpty();
    }
}
