package domain;

public class Bus extends TransportService {

    private double farePerPassenger;
    private int passengers;

    public Bus(int id, double farePerPassenger, int passengers) {
        super(id);
        setFarePerPassenger(farePerPassenger);
        setPassengers(passengers);
    }

    public double getFarePerPassenger() {
        return farePerPassenger;
    }

    public void setFarePerPassenger(double farePerPassenger) {
        requireNonNegative(farePerPassenger, "Fare per passenger");
        this.farePerPassenger = farePerPassenger;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        if (passengers <= 0) {
            throw new IllegalArgumentException("Passengers must be greater than zero.");
        }
        this.passengers = passengers;
    }

    @Override
    public double calculateFare() {
        return farePerPassenger * passengers;
    }

    @Override
    public String toString() {
        return String.format(
                "Bus [id=%d, farePerPassenger=%.2f, passengers=%d, fare=%.2f]",
                getId(), farePerPassenger, passengers, calculateFare());
    }
}
