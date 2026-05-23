package session15.ex3.services;

import session15.ex3.exceptions.InvalidProductPriceException;
import session15.ex3.exceptions.ProductNotFoundException;
import session15.ex3.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManager<T extends Product> {
    private final List<T> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    public void add(T product) throws InvalidProductPriceException {
        validatePrice(product.getPrice());
        products.add(product);
    }

    public void removeById(int id) throws ProductNotFoundException {
        T product = findById(id);
        products.remove(product);
    }

    public T findById(int id) throws ProductNotFoundException {
        for (T product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new ProductNotFoundException("Không tìm thấy sản phẩm.");
    }

    public void display() {
        System.out.println("Danh sách sản phẩm:");

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống.");
            return;
        }

        System.out.println("Duyệt bằng lambda:");
        products.forEach(System.out::println);
    }

    public List<T> getAll() {
        return products;
    }

    private void validatePrice(double price) throws InvalidProductPriceException {
        if (price <= 0) {
            throw new InvalidProductPriceException("Giá sản phẩm phải lớn hơn 0.");
        }
    }
}
