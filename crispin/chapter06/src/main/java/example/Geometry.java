package example;

public class Geometry {
    public static final double PI = 3.141592653589793;

    public double area(Object shape) throws Exception {

        if (shape instanceof Square s) {
            return s.side * s.side;
        } else if (shape instanceof Rectangle r) {
            return r.height * r.width;
        } else if (shape instanceof Circle c) {
            return PI * c.radius * c.radius;
        }
        throw new Exception();
    }
}
