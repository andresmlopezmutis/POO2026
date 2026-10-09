package domain;

public class Train extends TransportService {

    private double distance;
    private double farePerKm;
    private double railwayCharge;

    public Train(int id, double distance, double farePerKm, double railwayCharge) {
        super(id);
        setDistance(distance);
        setFarePerKm(farePerKm);
        setRailwayCharge(railwayCharge);
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

    public double getRailwayCharge() {
        return railwayCharge;
    }

    public void setRailwayCharge(double railwayCharge) {
        requireNonNegative(railwayCharge, "Railway charge");
        this.railwayCharge = railwayCharge;
    }

    @Override
    public double calculateFare() {
        return distance * farePerKm + railwayCharge;
    }

    @Override
    public String toString() {
        return String.format(
                "Train [id=%d, distance=%.2f km, farePerKm=%.2f, railwayCharge=%.2f, fare=%.2f]",
                getId(), distance, farePerKm, railwayCharge, calculateFare());
    }
}
