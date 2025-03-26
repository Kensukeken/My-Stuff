package com.uno;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;

public class PauseScreen {
    private Stage stage;
    private Scene previousScene;
    private AudioClip backgroundMusic;

    public PauseScreen(Stage stage, Scene previousScene, AudioClip backgroundMusic) {
        this.stage = stage;
        this.previousScene = previousScene;
        this.backgroundMusic = backgroundMusic;
    }

    public void show() {
        // Main pause screen elements
        Label title = new Label("Game Paused");
        title.getStyleClass().add("pause-title");

        // Main buttons
        Button resumeButton = createStyledButton("Resume", "resume-button", this::handleResume);
        Button settingsButton = createStyledButton("Settings", "settings-button", this::showSettings);
        Button rulesButton = createStyledButton("Uno Rules", "rules-button", this::showRules);
        Button quitButton = createStyledButton("Quit to Menu", "quit-button", this::handleQuit);

        VBox mainLayout = new VBox(20, title, resumeButton, settingsButton, rulesButton, quitButton);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        Scene pauseScene = new Scene(mainLayout, 700, 500);
        pauseScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(pauseScene);
    }

    private Button createStyledButton(String text, String styleClass, Runnable action) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        button.setOnAction(e -> action.run());
        return button;
    }

    private void handleResume() {
        stage.setScene(previousScene);
    }

    private void showSettings() {
        // Settings submenu
        Label settingsTitle = new Label("Settings");
        settingsTitle.getStyleClass().add("settings-title");

        // Audio controls
        Label volumeLabel = new Label("Volume");
        Slider volumeSlider = new Slider(0, 100, backgroundMusic.getVolume() * 100);
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            backgroundMusic.setVolume(newVal.doubleValue() / 100);
        });

        // Sound effects toggle
        Label sfxLabel = new Label("Sound Effects");
        ToggleSwitch sfxToggle = new ToggleSwitch();
        sfxToggle.setSelected(true);

        // Back button
        Button backButton = createStyledButton("Back", "back-button", this::show);

        VBox settingsLayout = new VBox(20,
                settingsTitle,
                new VBox(10, volumeLabel, volumeSlider),
                new HBox(10, sfxLabel, sfxToggle),
                backButton
        );
        settingsLayout.setAlignment(Pos.CENTER);
        settingsLayout.setPadding(new Insets(20));

        Scene settingsScene = new Scene(settingsLayout, 700, 500);
        settingsScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(settingsScene);
    }

    private void showRules() {
        // Rules display
        Label rulesTitle = new Label("Uno Rules");
        rulesTitle.getStyleClass().add("rules-title");

        Text rulesText = new Text(
                "UNO RULES:\n\n" +
                        "1. Match cards by color or number/symbol\n" +
                        "2. Special cards have unique effects:\n" +
                        "   - Skip: Next player misses a turn\n" +
                        "   - Reverse: Changes direction\n" +
                        "   - Draw 2: Next player draws 2 cards\n" +
                        "   - Wild: Choose next color\n" +
                        "   - Wild Draw 4: Choose color + next player draws 4\n" +
                        "3. Say 'UNO' when you have 1 card left\n" +
                        "4. First player to empty their hand wins!"
        );
        rulesText.setWrappingWidth(600);

        Button backButton = createStyledButton("Back", "back-button", this::show);

        VBox rulesLayout = new VBox(20, rulesTitle, rulesText, backButton);
        rulesLayout.setAlignment(Pos.CENTER);
        rulesLayout.setPadding(new Insets(20));

        Scene rulesScene = new Scene(rulesLayout, 700, 500);
        rulesScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(rulesScene);
    }

    private void handleQuit() {
        try {
            backgroundMusic.stop();
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        } catch (Exception e) {
            System.err.println("Error returning to main menu: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to return to main menu");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    // Custom toggle switch control for settings
    private static class ToggleSwitch extends HBox {
        private final Button button;
        private boolean selected;

        public ToggleSwitch() {
            button = new Button();
            button.setMinWidth(40);
            button.setPrefWidth(40);
            button.setMaxWidth(40);
            button.getStyleClass().add("toggle-button");

            setAlignment(Pos.CENTER_LEFT);
            getChildren().add(button);
            setSelected(false);

            button.setOnAction(e -> setSelected(!selected));
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
            if (selected) {
                button.getStyleClass().remove("toggle-off");
                button.getStyleClass().add("toggle-on");
                button.setText("ON");
            } else {
                button.getStyleClass().remove("toggle-on");
                button.getStyleClass().add("toggle-off");
                button.setText("OFF");
            }
        }

        public boolean isSelected() {
            return selected;
        }
    }
}
