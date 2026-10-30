package view;

import java.util.Locale;

import model.DoubleRoom;
import model.FamilyRoom;
import model.FloorSurchargePolicy;
import model.Guest;
import model.Hotel;
import model.Receipt;
import model.Reservation;
import model.Room;
import model.SingleRoom;
import model.StandardFloorSurchargePolicy;
import model.Suite;

public class Main {

    public static void main(String[] args) {
        Hotel hotel = createHotel();
        Room singleRoom = new SingleRoom(hotel, 101, 1, false, false);
        Room doubleRoom = new DoubleRoom(hotel, 402, 4, true, true);
        FamilyRoom familyRoom = new FamilyRoom(hotel, 706, 7, true, true, 4);
        Room suite = new Suite(hotel, 801, 8, true);

        hotel.registerRoom(singleRoom);
        hotel.registerRoom(doubleRoom);
        hotel.registerRoom(familyRoom);
        hotel.registerRoom(suite);

        Guest guest = new Guest(
                "G-001", "Valentina Rojas", "1001001001",
                "+57 300 111 2233", "valentina@example.com");
        Guest secondGuest = new Guest(
                "G-002", "Mateo Gomez", "1002002002",
                "+57 300 222 3344", "mateo@example.com");
        hotel.registerGuest(guest);
        hotel.registerGuest(secondGuest);

        printHotelAndLayout(hotel);
        demonstrateRates(hotel);
        demonstrateReservations(hotel, guest, secondGuest);
        demonstrateInvalidCases(hotel);
    }

    private static Hotel createHotel() {
        FloorSurchargePolicy floorPolicy = new StandardFloorSurchargePolicy();
        return new Hotel(
                "HOTEL-01",
                "Brisa del Caribe",
                "Cartagena, Colombia",
                8,
                50000,
                40000,
                30000,
                30,
                3500,
                floorPolicy);
    }

    private static void printHotelAndLayout(Hotel hotel) {
        System.out.printf(
                "%n=== %s: %d-floor hotel ===%nAddress: %s%n",
                hotel.getName(), hotel.getTotalFloors(), hotel.getAddress());
        System.out.println("Room plan (S = sea view, I = interior):");
        System.out.println("Floor 8: 801 Suite (S)");
        System.out.println("Floor 7: 706 Family Room, 4 single beds (S)");
        System.out.println("Floor 4: 402 Double Room (S)");
        System.out.println("Floor 1: 101 Single Room (I)");
    }

    private static void demonstrateRates(Hotel hotel) {
        System.out.println("\n=== Nightly rates ===");
        showRate(hotel, 101, 1, 1);
        showRate(hotel, 402, 1, 1);
        showRate(hotel, 402, 2, 1);
        showRate(hotel, 706, 1, 1);
        showRate(hotel, 706, 7, 1);
        showRate(hotel, 706, 8, 1);
        showRate(hotel, 801, 1, 1);
        showRate(hotel, 801, 2, 1);
    }

    private static void showRate(Hotel hotel, int roomNumber, int guests, int nights) {
        Room room = findRoom(hotel, roomNumber);
        System.out.printf(
                "Room %d (%s), %d guest(s), %d night(s): %s%n",
                roomNumber, room.getClass().getSimpleName(), guests, nights,
                money(room.calculateTotalCost(guests, nights)));
    }

    private static void demonstrateReservations(Hotel hotel, Guest guest, Guest secondGuest) {
        System.out.println("\n=== Reservations ===");
        Reservation singleReservation = hotel.createReservation(
                "RES-001", guest.getId(), 101, 1, 2, false);
        Reservation doubleReservation = hotel.createReservation(
                "RES-002", guest.getId(), 402, 2, 3, true);
        Reservation familyReservation = hotel.createReservation(
                "RES-003", secondGuest.getId(), 706, 7, 2, true);
        Reservation suiteReservation = hotel.createReservation(
                "RES-004", secondGuest.getId(), 801, 2, 1, true);

        System.out.println(singleReservation);
        System.out.println(doubleReservation);
        System.out.println(familyReservation);
        System.out.println(suiteReservation);

        Receipt receipt = hotel.confirmReservation(doubleReservation.getId());
        System.out.println("\nConfirmation generated: " + receipt);
        System.out.println("Receipt lookup: " + hotel.findReceipt(receipt.getNumber()));

        runInvalidCase("Requesting premium TV from an occupied room", new Runnable() {
            @Override
            public void run() {
                hotel.createReservation("RES-005", guest.getId(), 402, 1, 1, true);
            }
        });

        hotel.cancelReservation(doubleReservation.getId());
        System.out.println("Cancelled reservation; stored receipt remains: "
                + hotel.findReceipt(receipt.getNumber()));
        runInvalidCase("Reconfirming a cancelled reservation", new Runnable() {
            @Override
            public void run() {
                hotel.confirmReservation(doubleReservation.getId());
            }
        });

        Reservation replacement = hotel.createReservation(
                "RES-005", guest.getId(), 402, 1, 1, false);
        System.out.println("Room released after cancellation: " + replacement);
        hotel.cancelReservation(singleReservation.getId());
        hotel.cancelReservation(familyReservation.getId());
        hotel.cancelReservation(suiteReservation.getId());
        hotel.cancelReservation(replacement.getId());
    }

    private static void demonstrateInvalidCases(final Hotel hotel) {
        System.out.println("\n=== Validation checks ===");
        runInvalidCase("Request premium TV in a room without it", new Runnable() {
            @Override
            public void run() {
                hotel.createReservation("BAD-TV", "G-001", 101, 1, 1, true);
            }
        });
        runInvalidCase("Exceeding room capacity", new Runnable() {
            @Override
            public void run() {
                hotel.createReservation("BAD-CAPACITY", "G-001", 101, 2, 1, false);
            }
        });
        runInvalidCase("Booking with zero nights", new Runnable() {
            @Override
            public void run() {
                hotel.createReservation("BAD-NIGHTS", "G-001", 101, 1, 0, false);
            }
        });
        runInvalidCase("Booking an out-of-service room", new Runnable() {
            @Override
            public void run() {
                hotel.setRoomOperational(101, false);
                hotel.createReservation("BAD-MAINTENANCE", "G-001", 101, 1, 1, false);
            }
        });
        hotel.setRoomOperational(101, true);

        runInvalidCase("Looking up an unknown receipt", new Runnable() {
            @Override
            public void run() {
                hotel.findReceipt("UNKNOWN");
            }
        });
        runInvalidCase("Creating a suite below the top two floors", new Runnable() {
            @Override
            public void run() {
                new Suite(hotel, 601, 6, false);
            }
        });
        runInvalidCase("Creating a family room with too few single beds", new Runnable() {
            @Override
            public void run() {
                new FamilyRoom(hotel, 602, 6, false, false, 1);
            }
        });
        runInvalidCase("Creating a room above the hotel's top floor", new Runnable() {
            @Override
            public void run() {
                new SingleRoom(hotel, 901, 9, false, false);
            }
        });
        runInvalidCase("Creating a hotel with fewer than eight floors", new Runnable() {
            @Override
            public void run() {
                new Hotel(
                        "BAD-HOTEL", "Invalid Hotel", "Nowhere", 7,
                        50000, 40000, 30000, 30, 3500,
                        new StandardFloorSurchargePolicy());
            }
        });
    }

    private static Room findRoom(Hotel hotel, int roomNumber) {
        for (Room room : hotel.getRooms()) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        throw new IllegalArgumentException("Room not found: " + roomNumber);
    }

    private static void runInvalidCase(String description, Runnable operation) {
        try {
            operation.run();
            System.out.println("FAILED: " + description + " was accepted.");
        } catch (IllegalArgumentException | IllegalStateException exception) {
            System.out.println("Rejected as expected (" + description + "): "
                    + exception.getMessage());
        }
    }

    private static String money(double amount) {
        return String.format(Locale.US, "COP %,.0f", amount);
    }
}
