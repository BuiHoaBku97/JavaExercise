package session12.ex3.models;

public class Coffee extends Drink {
    public Coffee(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public void prepare() {
        System.out.println("Pha bằng máy");
    }
}
