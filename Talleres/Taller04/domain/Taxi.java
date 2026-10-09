package domain;

public class Taxi extends TransportService {

    private double baseFare;
    private double distance;
    private double farePerKm;

    public Taxi(int id, double baseFare, double distance, double farePerKm) {
        super(id);
        setBaseFare(baseFare);
        setDistance(distance);
        setFarePerKm(farePerKm);
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        requireNonNegative(baseFare, "Base fare");
        this.baseFare = baseFare;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        requireNonNegative(distance, "Distance");
        this.distance = distance;
    }

    public double getFarePerKm() {
        return farePerKm;
    }

    public void setFarePerKm(double farePerKm) {
        requireNonNegative(farePerKm, "Fare per kilometer");
        this.farePerKm = farePerKm;
    }

    @Override
    public double calculateFare() {
        return baseFare + distance * farePerKm;
    }

    @Override
    public String toString() {
        return String.format(
                "Taxi [id=%d, baseFare=%.2f, distance=%.2f km, farePerKm=%.2f, fare=%.2f]",
                getId(), baseFare, distance, farePerKm, calculateFare());
    }
}
