package session14.ex6.models;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "Mã: %s | Tên: %s | Đơn giá: %,.0f đồng | Số lượng: %d | Thành tiền: %,.0f đồng",
                product.getId(),
                product.getName(),
                product.getPrice(),
                quantity,
                getTotalPrice()
        );
    }
}
