package domain;

public class Square extends Shape {

    private double side1;

    public Square(double x, double y, double side1) {
        super(x, y);
        requirePositive(side1, "Side");
        this.side1 = side1;
    }

    public double getSide1() {
        return side1;
    }

    public boolean setSide1(double side1) {
        if (!isFinite(side1) || side1 <= 0) {
            return false;
        }
        this.side1 = side1;
        return true;
    }

    @Override
    public double calculateArea() {
        return side1 * side1;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side1;
    }

    @Override
    public String getType() {
        return "Square";
    }

    @Override
    public String getDimensions() {
        return String.format("side=%.2f", side1);
    }
}
