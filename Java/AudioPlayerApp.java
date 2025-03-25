package com.lab13;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayerApp extends Application {

    private MediaPlayer musicPlayer; // Background music player
    private MediaPlayer[] sounds = new MediaPlayer[4]; // Sound effects array of 4
    private Label status = new Label("Ready"); // Status label set to "Ready"
    private MediaPlayer currentSound; // Currently playing sound
    private final String[] soundLabels = {"Click Sound Effect", "Success Sound Effect", "Error Sound Effect", "Nature Sound Effect"}; // Sound effect labels

    @Override
    public void start(Stage stage) {
        // This loads the audio files from resources folder
        String bgMusic = loadFile("/Music/background.mp3");
        String[] soundFiles = {
                loadFile("/Music/click.wav"), // Click sound effect
                loadFile("/Music/success.wav"), // Success sound effect
                loadFile("/Music/error.wav"), // Error sound effect
                loadFile("/Music/nature.wav") // Nature sound effect
        };

        // If there's background music, set it up
        if (!bgMusic.isEmpty()) {
            musicPlayer = myPlayer(bgMusic, true);
        }

        // Set up sound effects
        for (int i = 0; i < sounds.length; i++) {
            // If there's a sound file, set it up
            if (!soundFiles[i].isEmpty()) {
                sounds[i] = myPlayer(soundFiles[i], false);
            }
        }

        // Buttons for the Music
        Button playBtn = new Button("Play Background Music");
        Button stopBtn = new Button("Stop Background Music");

        playBtn.setOnAction(e -> playMusic());
        stopBtn.setOnAction(e -> stopMusic());

        // Method for the sound effects to play
        Button[] soundBtns = new Button[4];
        for (int i = 0; i < soundBtns.length; i++) {
            soundBtns[i] = new Button(soundLabels[i]);
            int index = i;
            soundBtns[i].setOnAction(e -> playSound(index));
        }

        VBox root = new VBox(10, status, playBtn, stopBtn);
        root.getChildren().addAll(soundBtns);

        stage.setScene(new Scene(root, 300, 250));
        stage.setTitle("Audio Player");
        stage.show();
    }

    // This method loading the files
    private String loadFile(String path) {
        try {
            return getClass().getResource(path).toExternalForm();
        } catch (Exception e) {
            status.setText("Missing: " + path);
            return "";
        }
    }

    // Method to loop myPlayer
    private MediaPlayer myPlayer(String file, boolean loop) {
        Media media = new Media(file);
        MediaPlayer player = new MediaPlayer(media);
        if (loop) player.setCycleCount(MediaPlayer.INDEFINITE);
        return player;
    }

    // These methods are used for the background music
    // Play music method
    private void playMusic() {
        if (musicPlayer != null) {
            musicPlayer.play();
            status.setText("Music playing");
        }
    }

    // Stop music method
    private void stopMusic() {
        if (musicPlayer != null) {
            musicPlayer.stop();
            status.setText("Music stopped");
        }
    }

    // Method that plays sound effects
    private void playSound(int index) {
        if (sounds[index] == null) return;

        if (currentSound != null) {
            currentSound.stop();
        }
        sounds[index].stop();
        sounds[index].play();
        currentSound = sounds[index];
        status.setText("Playing: " + soundLabels[index]);
    }

    @Override
    public void stop() {
        if (musicPlayer != null) musicPlayer.stop();
        for (MediaPlayer sound : sounds) {
            if (sound != null) sound.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
