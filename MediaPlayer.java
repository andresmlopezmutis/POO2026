public class MediaPlayer {
    
    private int volume;
    private String playback_state;

    //constructor
    public MediaPlayer(int volume){
        if(volume >= 0 && volume <=100){
            this.volume = volume;
        } else {
            this.volume = 50;
        }
        this.playback_state = "stopped";
    }
    public void increase_volume(){
        if(volume + 5 <= 100){
            volume = volume + 5;
            System.out.println(volume);
        }else {
            System.out.println("El volumen no puede ser superior a 100");
        }
    }
    public void decrease_volume(){
        if(volume - 5 >= 0){
            volume = volume - 5;
            System.out.println(volume);
        }else {
            System.out.println("El volumen no puede ser inferior a 0");
        }
    }
    public void play(){
        playback_state = "playing";
        System.out.println("Reproduciendo");
    }
    public void stop(){
        playback_state = "stopped";
        System.out.println("Reproducción detenida");
    }
    public int getVolume(){
        return volume;
    }
    public String getPlaybackState(){
        return playback_state;
    }   
}
