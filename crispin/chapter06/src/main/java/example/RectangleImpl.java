package example;

public class RectangleImpl implements Shape {
    private Point topLeft;
    private double height;
    private double width;

    @Override
    public double area() {
        return height * width;
    }
}
