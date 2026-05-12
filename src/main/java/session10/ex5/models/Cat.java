package session10.ex5.models;

public class Cat extends Mammal {
    public Cat(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow meow!");
    }

    public void climbTree() {
        System.out.println(name + " is climbing a tree.");
    }
}
