package domain;

public class Rectangle extends Square {

    private double side2;

    public Rectangle(double x, double y, double side1, double side2) {
        super(x, y, side1);
        requirePositive(side2, "Side");
        this.side2 = side2;
    }

    public double getSide2() {
        return side2;
    }

    public boolean setSide2(double side2) {
        if (!isFinite(side2) || side2 <= 0) {
            return false;
        }
        this.side2 = side2;
        return true;
    }

    @Override
    public double calculateArea() {
        return getSide1() * side2;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (getSide1() + side2);
    }

    @Override
    public String getType() {
        return "Rectangle";
    }

    @Override
    public String getDimensions() {
        return String.format("side1=%.2f, side2=%.2f", getSide1(), side2);
    }
}
