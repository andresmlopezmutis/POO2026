public class Student {

    private String name;
    private String code;

    public Student(String name, String code) {
        if (!setName(name) || !setCode(code)) {
            throw new IllegalArgumentException("Datos invalidos para el estudiante");
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

    public String getCode() {
        return code;
    }

    public boolean setCode(String code) {
        if (code == null || code.isBlank()) {
            return false;
        }
        this.code = code;
        return true;
    }
}
