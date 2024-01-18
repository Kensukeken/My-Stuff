/* Sound.java
 * - Class representing in-game sounds. 
 * All sounds listed in `sounds` folder.
 * I've learned this in https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html in "Playing Sound Effects for Java Games" section.
 */

package src;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public enum Sound {
    ExplodeSound("sounds/explode.wav"), // Explode soundEffect file.
    MusicSound("sounds/StereoMadness.wav"); // StereoMadness file.

    // Nested class for specifying volume
    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    // Each sound effect has its own clip, loaded with its own sound file.
    private Clip clip;

    // Constructor to construct each element of the enum with its own sound file.
    Sound(String soundFileName) {
        try {
            URL url = this.getClass().getClassLoader().getResource(soundFileName);
            if (url == null) {
                System.err.println("Error: Unable to load resource for sound file: " + soundFileName);
                return;
            }
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Play the sound effect
    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning()) {
                clip.stop(); // Stop the player if it is still running
            }
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start(); // Start playing
        }
    }

    // Stop the sound effect
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    // Play the explode sound effect
    public static void playExplodeSound() {
        ExplodeSound.play();
    }

    // Optional static method to pre-load all the sound files.
    static void init() {
        values(); // Calls the constructor for all the elements
    }
}
