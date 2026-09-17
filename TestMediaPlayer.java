public class TestMediaPlayer {

    public static void main(String[] args) {

        MediaPlayer player = new MediaPlayer(40);

        System.out.println("Volumen: " + player.getVolume());
        System.out.println("Reproduciendo: " + player.getPlaybackState());

        player.play();

        System.out.println("Reproduciendo: " + player.getPlaybackState());

        // Aumentar volumen
        for (int i = 0; i < 12; i++) {
            player.increase_volume();

            System.out.println(
                    "Volumen: " + player.getVolume()
            );
        }

        // Disminuir volumen
        for (int i = 0; i < 22; i++) {
            player.decrease_volume();

            System.out.println(
                    "Volumen: " + player.getVolume()
            );
        }

        player.stop();

        System.out.println(
                "Reproduciendo: " + player.getPlaybackState()
        );
    }
}