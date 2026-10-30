package model;

public class SingleRoom extends Room {

    public SingleRoom(
            Hotel hotel, int roomNumber, int floor, boolean seaView, boolean premiumTv) {
        super(hotel, roomNumber, floor, seaView, premiumTv);
    }

    @Override
    public int getMaximumCapacity() {
        return 1;
    }

    @Override
    protected double calculateBedCharge(int guestCount) {
        validateGuestCount(guestCount);
        return getSingleBedRate();
    }
}
