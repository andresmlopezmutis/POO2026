package domain;

public class Airplane extends TransportService {

    private double baseFare;
    private double luggageWeight;
    private double farePerKg;

    public Airplane(int id, double baseFare, double luggageWeight, double farePerKg) {
        super(id);
        setBaseFare(baseFare);
        setLuggageWeight(luggageWeight);
        setFarePerKg(farePerKg);
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        requireNonNegative(baseFare, "Base fare");
        this.baseFare = baseFare;
    }

    public double getLuggageWeight() {
        return luggageWeight;
    }

    public void setLuggageWeight(double luggageWeight) {
        requireNonNegative(luggageWeight, "Luggage weight");
        this.luggageWeight = luggageWeight;
    }

    public double getFarePerKg() {
        return farePerKg;
    }

    public void setFarePerKg(double farePerKg) {
        requireNonNegative(farePerKg, "Fare per kilogram");
        this.farePerKg = farePerKg;
    }

    @Override
    public double calculateFare() {
        return baseFare + luggageWeight * farePerKg;
    }

    @Override
    public String toString() {
        return String.format(
                "Airplane [id=%d, baseFare=%.2f, luggageWeight=%.2f kg, farePerKg=%.2f, fare=%.2f]",
                getId(), baseFare, luggageWeight, farePerKg, calculateFare());
    }
}
