package session10.ex5.models;

public class Elephant extends Mammal {
    public Elephant(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Pawoooooo!");
    }

    public void sprayWater() {
        System.out.println(name + " is spraying water!");
    }
}
