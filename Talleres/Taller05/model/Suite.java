package model;

public class Suite extends Room {

    public Suite(Hotel hotel, int roomNumber, int floor, boolean seaView) {
        super(hotel, roomNumber, floor, seaView, true);
        if (floor < hotel.getTotalFloors() - 1) {
            throw new IllegalArgumentException("Suites must be on one of the hotel's top two floors.");
        }
    }

    @Override
    public int getMaximumCapacity() {
        return 2;
    }

    @Override
    protected double calculateBedCharge(int guestCount) {
        validateGuestCount(guestCount);
        return getDoubleBedRateForTwoGuests();
    }

    @Override
    protected double getAdditionalNightlyCharge() {
        return getHotel().getMinibarRate();
    }
}
