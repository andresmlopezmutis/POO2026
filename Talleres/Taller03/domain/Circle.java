package domain;

public class Circle extends Shape {

    private double radius1;

    public Circle(double x, double y, double radius1) {
        super(x, y);
        requirePositive(radius1, "Radius");
        this.radius1 = radius1;
    }

    public double getRadius1() {
        return radius1;
    }

    public boolean setRadius1(double radius1) {
        if (!isFinite(radius1) || radius1 <= 0) {
            return false;
        }
        this.radius1 = radius1;
        return true;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius1 * radius1;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius1;
    }

    @Override
    public String getType() {
        return "Circle";
    }

    @Override
    public String getDimensions() {
        return String.format("radius=%.2f", radius1);
    }
}
