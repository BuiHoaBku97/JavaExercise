package session9.ex1;

public class TestRectangle {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5.0, 3.0);

        rectangle.printInfo();
        System.out.println("Area: " + rectangle.getArea() + " - Perimeter: " + rectangle.getPerimeter());
    }
}
