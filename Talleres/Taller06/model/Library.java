package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {

    private final String name;
    private final List<Book> books;
    private final List<Author> authors;
    private final List<Reservation> reservations;
    private final List<Customer> customers;

    public Library(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre de la biblioteca no puede estar vacio");
        }
        this.name = name;
        books = new ArrayList<>();
        authors = new ArrayList<>();
        reservations = new ArrayList<>();
        customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public List<Author> getAuthors() {
        return Collections.unmodifiableList(authors);
    }

    public List<Reservation> getReservations() {
        return Collections.unmodifiableList(reservations);
    }

    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public boolean registerBook(Book book) {
        if (book == null || books.contains(book)) {
            return false;
        }
        return books.add(book);
    }

    public boolean updateBook(Book book, String title, Author author) {
        if (book == null || !books.contains(book) || !authors.contains(author)) {
            return false;
        }
        return book.updateDetails(title, author);
    }

    public boolean removeBook(Book book) {
        if (book == null || !books.contains(book) || isBookReserved(book)) {
            return false;
        }
        return books.remove(book);
    }

    public boolean removeAuthor(Author author) {
        if (author == null || !authors.contains(author) || isAuthorAssigned(author)) {
            return false;
        }
        return authors.remove(author);
    }

    public boolean updateAuthor(Author author, String name) {
        if (author == null || !authors.contains(author)) {
            return false;
        }
        return author.updateName(name);
    }

    public boolean registerAuthor(Author author) {
        if (author == null || authors.contains(author)) {
            return false;
        }
        return authors.add(author);
    }

    public boolean removeCustomer(Customer customer) {
        if (customer == null || !customers.contains(customer)
                || isCustomerAssociatedWithReservation(customer)) {
            return false;
        }
        return customers.remove(customer);
    }

    public boolean updateCustomer(Customer customer, String name) {
        if (customer == null || !customers.contains(customer)) {
            return false;
        }
        return customer.updateName(name);
    }

    public boolean registerCustomer(Customer customer) {
        if (customer == null || customers.contains(customer)) {
            return false;
        }
        return customers.add(customer);
    }

    public boolean removeReservation(Reservation reservation) {
        if (reservation == null || !reservations.contains(reservation)) {
            return false;
        }
        return reservations.remove(reservation);
    }

    public boolean updateReservation(
            Reservation reservation, Book book, Customer customer) {
        if (reservation == null || !reservations.contains(reservation)
                || !books.contains(book) || !customers.contains(customer)) {
            return false;
        }
        return reservation.updateDetails(book, customer);
    }

    public boolean registerReservation(Reservation reservation) {
        if (reservation == null
                || !books.contains(reservation.getBook())
                || !customers.contains(reservation.getCustomer())
                || reservations.contains(reservation)) {
            return false;
        }
        return reservations.add(reservation);
    }

    private boolean isBookReserved(Book book) {
        for (Reservation reservation : reservations) {
            if (reservation.getBook() == book) {
                return true;
            }
        }
        return false;
    }

    private boolean isAuthorAssigned(Author author) {
        for (Book book : books) {
            if (book.getAuthor() == author) {
                return true;
            }
        }
        return false;
    }

    private boolean isCustomerAssociatedWithReservation(Customer customer) {
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer() == customer) {
                return true;
            }
        }
        return false;
    }
}
