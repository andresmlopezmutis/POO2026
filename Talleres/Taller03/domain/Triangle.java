package domain;

public class Triangle extends Rectangle {

    private double side3;

    public Triangle(double x, double y, double side1, double side2, double side3) {
        super(x, y, side1, side2);
        if (!formsTriangle(side1, side2, side3)) {
            throw new IllegalArgumentException("The three side lengths must satisfy the triangle inequality.");
        }
        this.side3 = side3;
    }

    public double getSide3() {
        return side3;
    }

    @Override
    public boolean setSide1(double side1) {
        if (!formsTriangle(side1, getSide2(), side3)) {
            return false;
        }
        return super.setSide1(side1);
    }

    @Override
    public boolean setSide2(double side2) {
        if (!formsTriangle(getSide1(), side2, side3)) {
            return false;
        }
        return super.setSide2(side2);
    }

    public boolean setSide3(double side3) {
        if (!formsTriangle(getSide1(), getSide2(), side3)) {
            return false;
        }
        this.side3 = side3;
        return true;
    }

    @Override
    public double calculateArea() {
        double semiPerimeter = calculatePerimeter() / 2;
        return Math.sqrt(semiPerimeter
                * (semiPerimeter - getSide1())
                * (semiPerimeter - getSide2())
                * (semiPerimeter - side3));
    }

    @Override
    public double calculatePerimeter() {
        return getSide1() + getSide2() + side3;
    }

    @Override
    public String getType() {
        return "Triangle";
    }

    @Override
    public String getDimensions() {
        return String.format("side1=%.2f, side2=%.2f, side3=%.2f", getSide1(), getSide2(), side3);
    }

    private static boolean formsTriangle(double side1, double side2, double side3) {
        return isFinite(side1) && isFinite(side2) && isFinite(side3)
                && side1 > 0 && side2 > 0 && side3 > 0
                && side1 + side2 > side3
                && side1 + side3 > side2
                && side2 + side3 > side1;
    }
}
