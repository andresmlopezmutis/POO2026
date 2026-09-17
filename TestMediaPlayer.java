public class TestMediaPlayer {

    public static void main(String[] args) {

        // Datos no válidos para el constructor
        // MediaPlayer invalidPlayer = new MediaPlayer(
        //         150
        // );

        MediaPlayer player = new MediaPlayer(40);

        System.out.println("Volumen: " + player.getVolume());
        System.out.println("Reproduciendo: " + player.getPlaybackState());

        boolean result = player.play();

        System.out.println("Resultado de reproducir: " + result);
        System.out.println("Reproduciendo: " + player.getPlaybackState());

        // Aumentar volumen
        for (int i = 0; i < 12; i++) {
            result = player.increase_volume();

            if (!result) {
                System.out.println("El volumen no puede ser superior a 100");
            }

            System.out.println(
                    "Volumen: " + player.getVolume() +
                    ", resultado: " + result
            );
        }

        // Disminuir volumen
        for (int i = 0; i < 22; i++) {
            result = player.decrease_volume();

            if (!result) {
                System.out.println("El volumen no puede ser inferior a 0");
            }

            System.out.println(
                    "Volumen: " + player.getVolume() +
                    ", resultado: " + result
            );
        }

        result = player.stop();

        System.out.println("Resultado de detener: " + result);
        System.out.println(
                "Reproduciendo: " + player.getPlaybackState()
        );
    }
}