package session14.ex6.models;

import session14.ex6.exceptions.ProductNotFoundInCartException;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void addToCart(Product product, int quantity) {
        CartItem existingItem = findCartItemByProductId(product.getId());
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            return;
        }

        items.add(new CartItem(product, quantity));
    }

    public void removeFromCart(String productId) throws ProductNotFoundInCartException {
        CartItem item = findCartItemByProductId(productId);
        if (item == null) {
            throw new ProductNotFoundInCartException("Không tìm thấy sản phẩm trong giỏ hàng!");
        }

        items.remove(item);
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Giỏ hàng đang trống.");
            return;
        }

        System.out.println("Danh sách sản phẩm trong giỏ hàng:");
        for (CartItem item : items) {
            System.out.println("- " + item);
        }
    }

    public double checkout() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }

        System.out.printf("Tổng tiền cần thanh toán: %,.0f đồng%n", total);
        return total;
    }

    private CartItem findCartItemByProductId(String productId) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equalsIgnoreCase(productId)) {
                return item;
            }
        }
        return null;
    }
}
