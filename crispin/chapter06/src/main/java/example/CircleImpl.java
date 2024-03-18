package example;

public class CircleImpl implements Shape {
    private static final double PI = 3.141592653589793;
    private Point center;
    private double radius;

    @Override
    public double area() {
        return PI * radius * radius;
    }
}
