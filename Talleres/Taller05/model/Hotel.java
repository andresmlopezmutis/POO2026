package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Hotel {

    private final String id;
    private final String name;
    private final String address;
    private final int totalFloors;
    private final double baseRoomRate;
    private final double singleBedRate;
    private final double minibarRate;
    private final double seaViewSurchargePercent;
    private final double premiumTvSurcharge;
    private final FloorSurchargePolicy floorSurchargePolicy;
    private final Map<Integer, Room> rooms;
    private final Map<String, Guest> guests;
    private final Map<String, Reservation> reservations;
    private final Map<String, Receipt> receiptsByNumber;
    private int nextReceiptNumber;

    public Hotel(
            String id,
            String name,
            String address,
            int totalFloors,
            double baseRoomRate,
            double singleBedRate,
            double minibarRate,
            double seaViewSurchargePercent,
            double premiumTvSurcharge,
            FloorSurchargePolicy floorSurchargePolicy) {
        this.id = requireText(id, "Hotel id");
        this.name = requireText(name, "Hotel name");
        this.address = requireText(address, "Hotel address");
        if (totalFloors < 8) {
            throw new IllegalArgumentException("Hotel must have at least eight floors.");
        }
        this.totalFloors = totalFloors;
        this.baseRoomRate = requirePositive(baseRoomRate, "Base room rate");
        this.singleBedRate = requirePositive(singleBedRate, "Single bed rate");
        this.minibarRate = requirePositive(minibarRate, "Minibar rate");
        this.seaViewSurchargePercent = requireNonNegative(
                seaViewSurchargePercent, "Sea-view surcharge percent");
        this.premiumTvSurcharge = requireNonNegative(
                premiumTvSurcharge, "Premium-TV surcharge");
        if (floorSurchargePolicy == null) {
            throw new IllegalArgumentException("Floor surcharge policy cannot be null.");
        }
        this.floorSurchargePolicy = floorSurchargePolicy;
        this.rooms = new LinkedHashMap<Integer, Room>();
        this.guests = new LinkedHashMap<String, Guest>();
        this.reservations = new LinkedHashMap<String, Reservation>();
        this.receiptsByNumber = new LinkedHashMap<String, Receipt>();
        this.nextReceiptNumber = 1;
    }

    public void registerRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        if (room.getHotel() != this) {
            throw new IllegalArgumentException("Room must belong to this hotel.");
        }
        if (rooms.containsKey(room.getRoomNumber())) {
            throw new IllegalArgumentException("Room number is already registered.");
        }
        rooms.put(room.getRoomNumber(), room);
    }

    public void registerGuest(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException("Guest cannot be null.");
        }
        if (guests.containsKey(guest.getId())) {
            throw new IllegalArgumentException("Guest id is already registered.");
        }
        for (Guest registeredGuest : guests.values()) {
            if (registeredGuest.getIdentityDocument().equals(guest.getIdentityDocument())) {
                throw new IllegalArgumentException("Identity document is already registered.");
            }
        }
        guests.put(guest.getId(), guest);
    }

    public Reservation createReservation(
            String id,
            String guestId,
            int roomNumber,
            int guestCount,
            int nightCount,
            boolean requiresPremiumTv) {
        String validatedId = requireText(id, "Reservation id");
        if (reservations.containsKey(validatedId)) {
            throw new IllegalArgumentException("Reservation id is already registered.");
        }

        Guest guest = guests.get(guestId);
        if (guest == null) {
            throw new IllegalArgumentException("Guest is not registered in this hotel.");
        }
        Room room = rooms.get(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room is not registered in this hotel.");
        }
        if (requiresPremiumTv && !room.hasPremiumTv()) {
            throw new IllegalArgumentException("The selected room does not have premium TV.");
        }
        if (!room.isOperational()) {
            throw new IllegalStateException("Room is out of service.");
        }
        if (isRoomOccupied(room)) {
            throw new IllegalStateException("Room already has a pending or confirmed reservation.");
        }

        Reservation reservation = new Reservation(
                validatedId, guest, room, guestCount, nightCount);
        reservations.put(validatedId, reservation);
        guest.addReservation(reservation);
        return reservation;
    }

    public Receipt confirmReservation(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        String receiptNumber = String.format("REC-%06d", nextReceiptNumber);
        Receipt receipt = reservation.confirm(receiptNumber);
        receiptsByNumber.put(receiptNumber, receipt);
        nextReceiptNumber++;
        return receipt;
    }

    public void cancelReservation(String reservationId) {
        getReservation(reservationId).cancel();
    }

    public void setRoomOperational(int roomNumber, boolean operational) {
        Room room = rooms.get(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room is not registered in this hotel.");
        }
        room.setOperational(operational);
    }

    public Receipt findReceipt(String receiptNumber) {
        Receipt receipt = receiptsByNumber.get(receiptNumber);
        if (receipt == null) {
            throw new IllegalArgumentException("No receipt exists with that number.");
        }
        return receipt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public double getBaseRoomRate() {
        return baseRoomRate;
    }

    public double getSingleBedRate() {
        return singleBedRate;
    }

    public double getMinibarRate() {
        return minibarRate;
    }

    public double getSeaViewSurchargePercent() {
        return seaViewSurchargePercent;
    }

    public double getPremiumTvSurcharge() {
        return premiumTvSurcharge;
    }

    public FloorSurchargePolicy getFloorSurchargePolicy() {
        return floorSurchargePolicy;
    }

    public List<Room> getRooms() {
        return Collections.unmodifiableList(new ArrayList<Room>(rooms.values()));
    }

    public List<Guest> getGuests() {
        return Collections.unmodifiableList(new ArrayList<Guest>(guests.values()));
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(
                new ArrayList<Reservation>(reservations.values()));
    }

    private Reservation getReservation(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation is not registered in this hotel.");
        }
        return reservation;
    }

    private boolean isRoomOccupied(Room room) {
        for (Reservation reservation : reservations.values()) {
            if (reservation.getRoom() == room
                    && reservation.getStatus() != ReservationStatus.CANCELLED) {
                return true;
            }
        }
        return false;
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    private static double requirePositive(double value, String fieldName) {
        if (Double.isNaN(value) || Double.isInfinite(value) || value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be a positive finite number.");
        }
        return value;
    }

    private static double requireNonNegative(double value, String fieldName) {
        if (Double.isNaN(value) || Double.isInfinite(value) || value < 0) {
            throw new IllegalArgumentException(
                    fieldName + " must be a non-negative finite number.");
        }
        return value;
    }
}
