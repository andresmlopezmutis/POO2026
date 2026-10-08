public class Author {

    private String name;
    private String nationality;

    public Author(String name, String nationality) {
        if (!setName(name) || !setNationality(nationality)) {
            throw new IllegalArgumentException("Datos invalidos para el autor");
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

    public String getNationality() {
        return nationality;
    }

    public boolean setNationality(String nationality) {
        if (nationality == null || nationality.isBlank()) {
            return false;
        }
        this.nationality = nationality;
        return true;
    }
}
