package model;

import java.util.Optional;
import java.util.Locale;

public class Reservation {

    private final String id;
    private final Guest guest;
    private final Room room;
    private final int guestCount;
    private final int nightCount;
    private ReservationStatus status;
    private Receipt receipt;

    Reservation(String id, Guest guest, Room room, int guestCount, int nightCount) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Reservation id cannot be empty.");
        }
        if (guest == null || room == null) {
            throw new IllegalArgumentException("Guest and room are required.");
        }
        room.calculateTotalCost(guestCount, nightCount);
        this.id = id.trim();
        this.guest = guest;
        this.room = room;
        this.guestCount = guestCount;
        this.nightCount = nightCount;
        this.status = ReservationStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public int getNightCount() {
        return nightCount;
    }

    public double getTotalCost() {
        return room.calculateTotalCost(guestCount, nightCount);
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public Optional<Receipt> getReceipt() {
        return Optional.ofNullable(receipt);
    }

    Receipt confirm(String receiptNumber) {
        if (status != ReservationStatus.PENDING) {
            throw new IllegalStateException("Only pending reservations can be confirmed.");
        }
        Receipt generatedReceipt = new Receipt(
                receiptNumber,
                id,
                guest.getFullName(),
                room.getRoomNumber(),
                nightCount,
                getTotalCost());
        receipt = generatedReceipt;
        status = ReservationStatus.CONFIRMED;
        return generatedReceipt;
    }

    void cancel() {
        if (status == ReservationStatus.CANCELLED) {
            throw new IllegalStateException("Reservation is already cancelled.");
        }
        status = ReservationStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return String.format(
                Locale.US,
                "Reservation [id=%s, guest=%s, room=%d, guests=%d, nights=%d, status=%s, total=%,.0f COP]",
                id, guest.getFullName(), room.getRoomNumber(), guestCount,
                nightCount, status, getTotalCost());
    }
}
