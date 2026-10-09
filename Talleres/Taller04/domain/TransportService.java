package domain;

public abstract class TransportService {

    private int id;

    protected TransportService(int id) {
        setId(id);
    }

    public abstract double calculateFare();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id must be greater than zero.");
        }
        this.id = id;
    }

    protected static void requireNonNegative(double value, String fieldName) {
        if (Double.isNaN(value) || Double.isInfinite(value) || value < 0) {
            throw new IllegalArgumentException(fieldName + " must be a non-negative finite number.");
        }
    }
}
