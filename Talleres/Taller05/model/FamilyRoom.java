package model;

public class FamilyRoom extends Room {

    private final int singleBedCount;

    public FamilyRoom(
            Hotel hotel,
            int roomNumber,
            int floor,
            boolean seaView,
            boolean premiumTv,
            int singleBedCount) {
        super(hotel, roomNumber, floor, seaView, premiumTv);
        if (singleBedCount < 2 || singleBedCount > 4) {
            throw new IllegalArgumentException("Family rooms require 2 to 4 single beds.");
        }
        this.singleBedCount = singleBedCount;
    }

    @Override
    public int getMaximumCapacity() {
        return 4 + singleBedCount;
    }

    public int getSingleBedCount() {
        return singleBedCount;
    }

    @Override
    protected double calculateBedCharge(int guestCount) {
        validateGuestCount(guestCount);
        double minimumBedCharge = 2 * getDoubleBedRateForTwoGuests()
                + 2 * getSingleBedRate();
        int additionalGuests = Math.max(0, guestCount - 6);
        return minimumBedCharge + additionalGuests * getAdditionalGuestRate();
    }
}
