public class Order {

    private final int orderNumber;
    private String description;
    private Customer customer;

    public Order(int orderNumber, String description, Customer customer) {
        if (orderNumber <= 0 || description == null || description.isBlank() || customer == null) {
            throw new IllegalArgumentException("Datos invalidos para el pedido");
        }
        this.orderNumber = orderNumber;
        this.description = description;
        this.customer = customer;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public boolean setDescription(String description) {
        if (description == null || description.isBlank()) {
            return false;
        }
        this.description = description;
        return true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean setCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }
        this.customer = customer;
        return true;
    }
}
