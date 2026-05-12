package session10.ex5.models;

public class Dog extends Mammal {
    public Dog(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof woof!");
    }

    public void fetchBall() {
        System.out.println(name + " is fetching the ball.");
    }
}
