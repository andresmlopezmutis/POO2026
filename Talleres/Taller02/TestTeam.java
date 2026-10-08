public class TestTeam {

    public static void main(String[] args) {
        Team team = new Team("Los Halcones");
        team.addPlayer(new Player("Carlos Ramirez", "Delantero"));
        team.addPlayer(new Player("Daniel Torres", "Defensa"));
        team.addPlayer(new Player("Juan Herrera", "Portero"));

        System.out.println("Equipo: " + team.getName());
        System.out.println("Jugadores:");
        for (Player player : team.getPlayers()) {
            System.out.println("- " + player.getName());
        }
    }
}
