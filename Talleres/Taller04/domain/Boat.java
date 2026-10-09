package domain;

public class Boat extends TransportService {

    private double baseFare;
    private double distance;
    private double farePerKm;
    private double boardingCharge;

    public Boat(int id, double baseFare, double distance, double farePerKm, double boardingCharge) {
        super(id);
        setBaseFare(baseFare);
        setDistance(distance);
        setFarePerKm(farePerKm);
        setBoardingCharge(boardingCharge);
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

    public double getBoardingCharge() {
        return boardingCharge;
    }

    public void setBoardingCharge(double boardingCharge) {
        requireNonNegative(boardingCharge, "Boarding charge");
        this.boardingCharge = boardingCharge;
    }

    @Override
    public double calculateFare() {
        return baseFare + distance * farePerKm + boardingCharge;
    }

    @Override
    public String toString() {
        return String.format(
                "Boat [id=%d, baseFare=%.2f, distance=%.2f km, farePerKm=%.2f, "
                        + "boardingCharge=%.2f, fare=%.2f]",
                getId(), baseFare, distance, farePerKm, boardingCharge, calculateFare());
    }
}
