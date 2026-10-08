public class Customer {

    private String name;
    private String email;

    public Customer(String name, String email) {
        if (!setName(name) || !setEmail(email)) {
            throw new IllegalArgumentException("Datos invalidos para el cliente");
        }
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        this.name = name;
        return true;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        this.email = email;
        return true;
    }
}
