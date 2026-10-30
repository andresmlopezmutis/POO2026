package model;

public class Customer {

    private String name;

    public Customer(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean updateName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        this.name = name;
        return true;
    }
}
