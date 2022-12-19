package render;

import static libs.Constants.DEBUG;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;

/*
* If you want to add a new sound, just put a new line below "public enum Sound { "
* Exemple : TEST ("/path/to/sound/<name>.wav"),
* 
* To play a sound in another class, just import render.Sound and call playSound()
* Example : Sound.TEST.playSound();
*/

public enum Sound {
    
    COLLIDE ("/data/sounds/ball_collision.wav"),
    CUE_LOW ("/data/sounds/cue_collision_strong.wav"),
    CUE_HIGH ("/data/sounds/cue_collision_weak.wav"),
    POCKET ("/data/sounds/pocket.wav");

    // Get a sound clip resource.
    Clip clip;

    private Sound(String soundName){
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + soundName).getAbsoluteFile());
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
          } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          } catch (LineUnavailableException e) {
            e.printStackTrace();
          }
          if(DEBUG)System.out.println("just open clip : "+this.name());
    }

    public void playSound(){
        if(clip.isRunning())clip.stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void init(){
        //do nothing to call constructor enum classes
    }
    
}

