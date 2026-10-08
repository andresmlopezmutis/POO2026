package domain;

public class Ellipse extends Circle {

    private double radius2;

    public Ellipse(double x, double y, double radius1, double radius2) {
        super(x, y, radius1);
        requirePositive(radius2, "Radius");
        this.radius2 = radius2;
    }

    public double getRadius2() {
        return radius2;
    }

    public boolean setRadius2(double radius2) {
        if (!isFinite(radius2) || radius2 <= 0) {
            return false;
        }
        this.radius2 = radius2;
        return true;
    }

    @Override
    public double calculateArea() {
        return Math.PI * getRadius1() * radius2;
    }

    @Override
    public double calculatePerimeter() {
        double a = Math.max(getRadius1(), radius2);
        double b = Math.min(getRadius1(), radius2);
        double h = Math.pow(a - b, 2) / Math.pow(a + b, 2);
        return Math.PI * (a + b) * (1 + (3 * h) / (10 + Math.sqrt(4 - 3 * h)));
    }

    @Override
    public String getType() {
        return "Ellipse";
    }

    @Override
    public String getDimensions() {
        return String.format("radius1=%.2f, radius2=%.2f", getRadius1(), radius2);
    }
}
