package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Guest {

    private final String id;
    private final String fullName;
    private final String identityDocument;
    private final String phoneNumber;
    private final String email;
    private final List<Reservation> reservations;

    public Guest(
            String id,
            String fullName,
            String identityDocument,
            String phoneNumber,
            String email) {
        this.id = requireText(id, "Guest id");
        this.fullName = requireText(fullName, "Full name");
        this.identityDocument = requireText(identityDocument, "Identity document");
        this.phoneNumber = requireText(phoneNumber, "Phone number");
        this.email = requireText(email, "Email");
        this.reservations = new ArrayList<Reservation>();
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(new ArrayList<Reservation>(reservations));
    }

    void addReservation(Reservation reservation) {
        if (reservation == null || reservation.getGuest() != this) {
            throw new IllegalArgumentException("Reservation must belong to this guest.");
        }
        reservations.add(reservation);
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }
}
