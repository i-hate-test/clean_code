package example;

/**
 * 추상적인 Point 클래스
 */
public interface PointInterface {

    double getX();
    double getY();
    void setCartesian(double x, double y);
    double getR();
    double getTheta();
    void setPolar(double r, double theta);
}
