package Plants;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Music extends Thread {
    public void run() {
        try {

            FileInputStream fil1 =
                    new FileInputStream("src/Plants/Music/Crazy Davy.mp3");
            BufferedInputStream bis1 =
                    new BufferedInputStream(fil1);

            Player p = new Player(bis1);
            p.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}