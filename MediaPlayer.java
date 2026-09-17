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
    public boolean increase_volume(){
        if(volume + 5 <= 100){
            volume = volume + 5;
            return true;
        }else {
            return false;
        }
    }
    public boolean decrease_volume(){
        if(volume - 5 >= 0){
            volume = volume - 5;
            return true;
        }else {
            return false;
        }
    }
    public boolean play(){
        playback_state = "playing";
        return true;
    }
    public boolean stop(){
        playback_state = "stopped";
        return true;
    }
    public int getVolume(){
        return volume;
    }
    public String getPlaybackState(){
        return playback_state;
    }   
}
