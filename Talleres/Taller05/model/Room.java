package model;

public abstract class Room {

    private final Hotel hotel;
    private final int roomNumber;
    private final int floor;
    private final boolean seaView;
    private final boolean premiumTv;
    private boolean operational;

    protected Room(
            Hotel hotel, int roomNumber, int floor, boolean seaView, boolean premiumTv) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null.");
        }
        if (roomNumber <= 0) {
            throw new IllegalArgumentException("Room number must be greater than zero.");
        }
        if (floor < 1 || floor > hotel.getTotalFloors()) {
            throw new IllegalArgumentException("Floor must be within the hotel's floors.");
        }

        this.hotel = hotel;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.seaView = seaView;
        this.premiumTv = premiumTv;
        this.operational = true;
    }

    public abstract int getMaximumCapacity();

    protected abstract double calculateBedCharge(int guestCount);

    public final double calculateNightlyCost(int guestCount) {
        validateGuestCount(guestCount);
        double bedCharge = requireFinite(calculateBedCharge(guestCount), "Bed charge");
        double additionalCharge = requireFinite(
                getAdditionalNightlyCharge(), "Additional nightly charge");
        double total = getAdjustedBaseRate()
                + bedCharge
                + additionalCharge;
        return requireFinite(total, "Nightly cost");
    }

    public final double calculateTotalCost(int guestCount, int nightCount) {
        if (nightCount <= 0) {
            throw new IllegalArgumentException("Night count must be greater than zero.");
        }
        return requireFinite(calculateNightlyCost(guestCount) * nightCount, "Total cost");
    }

    public final double getAdjustedBaseRate() {
        double seaViewSurcharge = seaView
                ? hotel.getBaseRoomRate() * hotel.getSeaViewSurchargePercent() / 100
                : 0;
        double floorSurchargePercent = seaView
                ? requireFinite(
                        hotel.getFloorSurchargePolicy().getSurchargePercent(floor),
                        "Floor surcharge percent")
                : 0;
        double floorSurcharge = seaView ? seaViewSurcharge * floorSurchargePercent / 100 : 0;
        double premiumTvCharge = premiumTv ? hotel.getPremiumTvSurcharge() : 0;
        return requireFinite(
                hotel.getBaseRoomRate() + seaViewSurcharge + floorSurcharge + premiumTvCharge,
                "Adjusted base rate");
    }

    protected double getAdditionalNightlyCharge() {
        return 0;
    }

    public final int getRoomNumber() {
        return roomNumber;
    }

    public final int getFloor() {
        return floor;
    }

    public final Hotel getHotel() {
        return hotel;
    }

    public final boolean isOperational() {
        return operational;
    }

    public final boolean isSeaView() {
        return seaView;
    }

    public final boolean hasPremiumTv() {
        return premiumTv;
    }

    final void setOperational(boolean operational) {
        this.operational = operational;
    }

    protected final void validateGuestCount(int guestCount) {
        if (guestCount < 1 || guestCount > getMaximumCapacity()) {
            throw new IllegalArgumentException(
                    "Guest count must be between 1 and " + getMaximumCapacity() + ".");
        }
    }

    protected final double getSingleBedRate() {
        return hotel.getSingleBedRate();
    }

    protected final double getDoubleBedRateForOneGuest() {
        return getSingleBedRate() * 1.45;
    }

    protected final double getDoubleBedRateForTwoGuests() {
        return getSingleBedRate() * 1.90;
    }

    protected final double getAdditionalGuestRate() {
        return getSingleBedRate() * 0.70;
    }

    protected static double requireFinite(double amount, String fieldName) {
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount < 0) {
            throw new IllegalArgumentException(fieldName + " must be a finite non-negative amount.");
        }
        return amount;
    }
}
