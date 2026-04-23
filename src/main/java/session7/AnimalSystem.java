package session7;

class Animal {
    String name;
    int age;

    void makeSound() {
        System.out.println("Animal sound");
    }
}

class Cat extends Animal {
    String breed;

    void bark() {
        System.out.println("Cat bark");
    }
}

class Dog extends Animal {
    String color;

    void meow() {
        System.out.println("Dog meow");
    }
}