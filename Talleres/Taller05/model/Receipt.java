package model;

import java.util.Locale;

public final class Receipt {

    private final String number;
    private final String reservationId;
    private final String guestName;
    private final int roomNumber;
    private final int nightCount;
    private final double totalCost;

    Receipt(
            String number,
            String reservationId,
            String guestName,
            int roomNumber,
            int nightCount,
            double totalCost) {
        this.number = requireText(number, "Receipt number");
        this.reservationId = requireText(reservationId, "Reservation id");
        this.guestName = requireText(guestName, "Guest name");
        if (roomNumber <= 0 || nightCount <= 0) {
            throw new IllegalArgumentException("Room number and night count must be positive.");
        }
        this.roomNumber = roomNumber;
        this.nightCount = nightCount;
        this.totalCost = Room.requireFinite(totalCost, "Receipt total");
    }

    public String getNumber() {
        return number;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getNightCount() {
        return nightCount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return String.format(
                Locale.US,
                "Receipt [number=%s, reservation=%s, guest=%s, room=%d, nights=%d, total=%,.0f COP]",
                number, reservationId, guestName, roomNumber, nightCount, totalCost);
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }
}
