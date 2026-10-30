package model;

public class Author {

    private String name;

    public Author(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre del autor no puede estar vacio");
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
