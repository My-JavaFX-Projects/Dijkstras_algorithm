package org.example;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.List;

public class BackgroundMusic implements Runnable {
    private static final List<String> mp3List = List.of(
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-music-teste-204327.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/a-109881.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/andando-em-circulos-195703.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-music-150676.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-music-loop-2-144037.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-music-loop-9-145494.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-music-loop-10-145572.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-music-loop-15-153386.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-music-loop-16-153389.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-music-loop-18-153392.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-music-loop-19-153393.mp3",
            "/home/jemsit/Desktop/testApp/app/src/main/resources/game-time-172907.mp3"
    );

    @Override
    public void run() {
        final int randomInt =(int) (Math.random() * mp3List.size()-1);
        final String URL = mp3List.get(randomInt);
        Media media = new Media(new File(URL).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
}
