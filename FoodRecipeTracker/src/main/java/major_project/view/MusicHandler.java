package major_project.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class MusicHandler {

    private final AppWindow view;

    public MusicHandler(AppWindow view) {
        this.view = view;
    }

    /**
     * Plays the music - initially finds file and sets to loop if reaches the end
     */
    public void playMusic() {
        File musicFile = new File("./src/main/java/major_project/model/Music/just-relax-11157.mp3");
        Media media = new Media(musicFile.toURI().toString());
        this.view.setMediaPlayer(new MediaPlayer(media));

        this.view.getMediaPlayer().setAutoPlay(true);
        this.view.getMediaPlayer().setOnEndOfMedia(() ->  this.view.getMediaPlayer().seek(Duration.ZERO));
    }

    /**
     * Will either start or stop the music depending on its state
     * Will always start from beginning
     */
    public void toggleMusic() {
        if (this.view.getMusicPlaying()) {
            this.view.getMediaPlayer().stop();
            this.view.setMusicPlaying(false);
        } else {
            this.view.getMediaPlayer().play();
            this.view.setMusicPlaying(true);
        }
    }
}
