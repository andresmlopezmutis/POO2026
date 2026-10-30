package model;

public class DoubleRoom extends Room {

    public DoubleRoom(
            Hotel hotel, int roomNumber, int floor, boolean seaView, boolean premiumTv) {
        super(hotel, roomNumber, floor, seaView, premiumTv);
    }

    @Override
    public int getMaximumCapacity() {
        return 2;
    }

    @Override
    protected double calculateBedCharge(int guestCount) {
        validateGuestCount(guestCount);
        return guestCount == 1
                ? getDoubleBedRateForOneGuest()
                : getDoubleBedRateForTwoGuests();
    }
}
