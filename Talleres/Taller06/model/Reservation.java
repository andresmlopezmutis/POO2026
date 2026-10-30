package model;

public class Reservation {

    private Book book;
    private Customer customer;

    public Reservation(Book book, Customer customer) {
        if (book == null) {
            throw new IllegalArgumentException("La reserva debe corresponder a un libro");
        }
        if (customer == null) {
            throw new IllegalArgumentException("La reserva debe corresponder a un cliente");
        }
        this.book = book;
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean updateDetails(Book book, Customer customer) {
        if (book == null || customer == null) {
            return false;
        }
        this.book = book;
        this.customer = customer;
        return true;
    }
}
