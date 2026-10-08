import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {

    private String name;
    private final List<Player> players;

    public Team(String name) {
        if (!setName(name)) {
            throw new IllegalArgumentException("El nombre del equipo no puede estar vacio");
        }
        players = new ArrayList<>();
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

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public boolean addPlayer(Player player) {
        if (player == null) {
            return false;
        }
        return players.add(player);
    }
}
