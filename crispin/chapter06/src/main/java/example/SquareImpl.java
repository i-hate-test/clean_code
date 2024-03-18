package example;

public class SquareImpl implements Shape {
    private Point topLeft;
    private double side;

    @Override
    public double area() {
        return side * side;
    }
}
