package domain;

public abstract class Shape {

    private static int nextId = 1;

    private final int id;
    private double x;
    private double y;

    protected Shape(double x, double y) {
        if (!isFinite(x) || !isFinite(y)) {
            throw new IllegalArgumentException("Coordinates must be finite numbers.");
        }
        id = nextId++;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public boolean setX(double x) {
        if (!isFinite(x)) {
            return false;
        }
        this.x = x;
        return true;
    }

    public double getY() {
        return y;
    }

    public boolean setY(double y) {
        if (!isFinite(y)) {
            return false;
        }
        this.y = y;
        return true;
    }

    public abstract double calculateArea();

    public abstract double calculatePerimeter();

    public abstract String getType();

    public abstract String getDimensions();

    protected static boolean isFinite(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value);
    }

    protected static void requirePositive(double value, String dimensionName) {
        if (!isFinite(value) || value <= 0) {
            throw new IllegalArgumentException(dimensionName + " must be a positive finite number.");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "%s [id=%d, center=(%.2f, %.2f), %s, area=%.2f, perimeter=%.2f]",
                getType(), id, x, y, getDimensions(), calculateArea(), calculatePerimeter());
    }
}
