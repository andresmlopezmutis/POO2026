public class Player {

    private String name;
    private String position;

    public Player(String name, String position) {
        if (!setName(name) || !setPosition(position)) {
            throw new IllegalArgumentException("Datos invalidos para el jugador");
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

    public String getPosition() {
        return position;
    }

    public boolean setPosition(String position) {
        if (position == null || position.isBlank()) {
            return false;
        }
        this.position = position;
        return true;
    }
}
