package com.lab12;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

/**
 * @author Hia Al Saleh
 * @date March 17th, 2025

 * Lab 12 - High Scores
 * Have a text field in the center of a BorderPane
 * When the user enters a number and hits the enter key the value should be written to a file named highscore.txt
 * Use either spaces or new lines as delimiters for the values
 * The program should then navigate to a new scene that shows the word "High Scores"
 * The program should then display all the contents of the file sorted in descending order.
 */

public class Lab12 extends Application {

    // File name to store the high scores
    private static final String FILE_NAME = "highscore.txt";

    @Override
    public void start(Stage stage) {
        // --- Input Scene UI ---
        VBox inputBox = new VBox(15);
        inputBox.setPadding(new Insets(30));
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setStyle("-fx-background-color: linear-gradient(to bottom, #e0f7fa, #80deea);");

        TextField inputField = new TextField();
        inputField.setPromptText("Enter a number and press Enter");
        inputField.setFont(Font.font(16));
        inputField.setMaxWidth(250);

        Button enterButton = new Button("Enter");
        enterButton.setStyle("-fx-background-color:  #00e4ff; -fx-font-size: 14px;");
        enterButton.setPrefWidth(100);

        inputBox.getChildren().addAll(inputField, enterButton);
        Scene inputScene = new Scene(inputBox, 400, 200);

        // High Score layout and scene
        VBox scoreLayout = new VBox(10);
        scoreLayout.setPadding(new Insets(20));
        scoreLayout.setAlignment(Pos.CENTER);
        scoreLayout.setStyle("-fx-background-color: #f0f4c3;");

        Label title = new Label("High Scores");
        title.setFont(Font.font("Verdana", 24));

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #aed581; -fx-border-color: black;");

        Scene scoreScene = new Scene(scoreLayout, 400, 300);


        // Custom button animation
        ScaleTransition button = new ScaleTransition(Duration.millis(150), enterButton);
        enterButton.setOnMouseEntered(e -> {
            button.setToX(1.1);
            button.setToY(1.1);
            button.playFromStart();
        });
        enterButton.setOnMouseExited(e -> {
            button.setToX(1.0);
            button.setToY(1.0);
            button.playFromStart();
        });

        // Enter Button Action
        enterButton.setOnAction(e -> {
            handleScoreInput(inputField, scoreLayout, scoreScene, stage, title, backButton);
        });

        // Back Button Action - Clear the text field and navigate back to the input scene
        backButton.setOnAction(e -> {
            inputField.clear();
            stage.setScene(inputScene);
        });

        stage.setTitle("Lab 12");
        stage.setScene(inputScene);
        stage.show();
    }

    private void handleScoreInput(TextField inputField, VBox scoreLayout, Scene scoreScene, Stage stage, Label title, Button backButton) {
        String text = inputField.getText().trim();
        try {
            int number = Integer.parseInt(text);

            List<Integer> existingScores = loadScoresFromFile();

            if (existingScores.contains(number)) {
                inputField.setText("Number already exists.");
                return;
            }

            saveToFile(number);
            inputField.clear();

            // Refresh high scores
            scoreLayout.getChildren().setAll(title, backButton);
            existingScores.add(number);
            existingScores.sort(Collections.reverseOrder());
            for (int score : existingScores) {
                Label scoreLabel = new Label("Score: " + score);
                scoreLabel.setFont(Font.font(16));
                scoreLabel.setTextFill(Color.DARKBLUE);
                scoreLayout.getChildren().add(scoreLabel);
            }

            stage.setScene(scoreScene);
        } catch (NumberFormatException e) {
            inputField.setText("Invalid input. Enter a number.");
        }
    }


    // Save the score to the file
    private void saveToFile(int number) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.print(number + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load the scores from the file
    private List<Integer> loadScoresFromFile() {
        List<Integer> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextInt()) {
                scores.add(scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scores.sort(Collections.reverseOrder());
        return scores;
    }

    public static void main(String[] args) {
        launch();
    }
}

/* default
package com.lab12;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Lab12 extends Application {

    private static final String FILE_NAME = "highscore.txt";

    @Override
    public void start(Stage stage) {
        // --- Input Scene ---
        VBox inputBox = new VBox(10);
        inputBox.setPrefSize(400, 200);
        TextField inputField = new TextField();
        inputField.setPromptText("Enter a number");

        Button enterButton = new Button("Enter");

        inputBox.getChildren().addAll(inputField, enterButton);
        Scene inputScene = new Scene(inputBox);

        // --- Score Scene ---
        VBox scoreLayout = new VBox(10);
        Label title = new Label("High Scores");
        Button backButton = new Button("Back");

        Scene scoreScene = new Scene(scoreLayout, 400, 300);

        // --- Enter Button Action ---
        enterButton.setOnAction(e -> {
            String text = inputField.getText().trim();
            try {
                int number = Integer.parseInt(text);
                saveToFile(number);
                inputField.clear();

                // Load and display scores
                scoreLayout.getChildren().clear();
                scoreLayout.getChildren().add(title);
                scoreLayout.getChildren().add(backButton);

                List<Integer> scores = loadScoresFromFile();
                for (int score : scores) {
                    scoreLayout.getChildren().add(new Label("Score: " + score));
                }

                stage.setScene(scoreScene);
            } catch (NumberFormatException ex) {
                inputField.setText("Invalid number!");
            }
        });

        // --- Back Button Action ---
        backButton.setOnAction(e -> {
            inputField.clear();
            stage.setScene(inputScene);
        });

        // --- Show the Stage ---
        stage.setTitle("Score App");
        stage.setScene(inputScene);
        stage.show();
    }

    // Save score to file
    private void saveToFile(int number) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.print(number + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load and sort scores
    private List<Integer> loadScoresFromFile() {
        List<Integer> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextInt()) {
                scores.add(scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scores.sort(Collections.reverseOrder());
        return scores;
    }

    public static void main(String[] args) {
        launch();
    }
}

 */


/*
the real one
package com.lab12;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Lab12 extends Application {

    private static final String FILE_NAME = "highscore.txt";

    @Override
    public void start(Stage stage) {
        // --- Input Scene ---
        VBox inputBox = new VBox(10);
        inputBox.setPrefSize(400, 200);
        inputBox.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        inputField.setPromptText("Enter a number");

        Button enterButton = new Button("Enter");

        inputBox.getChildren().addAll(inputField, enterButton);
        Scene inputScene = new Scene(inputBox);

        // --- Score Scene ---
        VBox scoreLayout = new VBox(10);
        scoreLayout.setAlignment(Pos.CENTER);  // Centering the content
        Label title = new Label("High Scores");
        Button backButton = new Button("Back");

        scoreLayout.getChildren().addAll(title, backButton);
        Scene scoreScene = new Scene(scoreLayout, 400, 300);

        // --- Enter Button Action ---
        enterButton.setOnAction(e -> {
            String text = inputField.getText().trim();
            try {
                int number = Integer.parseInt(text);
                saveToFile(number);
                inputField.clear();

                // Load and display scores
                scoreLayout.getChildren().clear();
                scoreLayout.getChildren().addAll(title, backButton);

                List<Integer> scores = loadScoresFromFile();
                for (int score : scores) {
                    scoreLayout.getChildren().add(new Label("Score: " + score));
                }

                stage.setScene(scoreScene);
            } catch (NumberFormatException ex) {
                inputField.setText("Invalid number!");
            }
        });

        // --- Back Button Action ---
        backButton.setOnAction(e -> {
            inputField.clear();
            stage.setScene(inputScene);
        });

        // --- Show the Stage ---
        stage.setTitle("Lab 12");
        stage.setScene(inputScene);
        stage.show();
    }

    // Save score to file
    private void saveToFile(int number) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.print(number + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load and sort scores
    private List<Integer> loadScoresFromFile() {
        List<Integer> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextInt()) {
                scores.add(scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scores.sort(Collections.reverseOrder());
        return scores;
    }

    public static void main(String[] args) {
        launch();
    }
}

*/
/*
if the number already exists in the file
package com.lab12;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Lab12 extends Application {

    private static final String FILE_NAME = "highscore.txt";

    @Override
    public void start(Stage stage) {
        // --- Input Scene ---
        VBox inputBox = new VBox(10);
        inputBox.setPrefSize(400, 200);
        inputBox.setAlignment(Pos.CENTER);
        TextField inputField = new TextField();
        inputField.setPromptText("Enter a number");

        Button enterButton = new Button("Enter");

        inputBox.getChildren().addAll(inputField, enterButton);
        Scene inputScene = new Scene(inputBox);

        // --- Score Scene ---
        VBox scoreLayout = new VBox(10);
        scoreLayout.setAlignment(Pos.CENTER);
        Label title = new Label("High Scores");
        Button backButton = new Button("Back");

        scoreLayout.getChildren().addAll(title, backButton);
        Scene scoreScene = new Scene(scoreLayout, 400, 300);

        // --- Enter Button Action ---
        enterButton.setOnAction(e -> {
            String text = inputField.getText().trim();
            try {
                int number = Integer.parseInt(text);
                if (isNumberAlreadyExist(number)) {
                    inputField.setText("Number already exists!");
                } else {
                    saveToFile(number);
                    inputField.clear();

                    // Load and display scores
                    scoreLayout.getChildren().clear();
                    scoreLayout.getChildren().addAll(title, backButton);

                    List<Integer> scores = loadScoresFromFile();
                    for (int score : scores) {
                        scoreLayout.getChildren().add(new Label("Score: " + score));
                    }

                    stage.setScene(scoreScene);
                }
            } catch (NumberFormatException ex) {
                inputField.setText("Invalid number!");
            }
        });

        // --- Back Button Action ---
        backButton.setOnAction(e -> {
            inputField.clear();
            stage.setScene(inputScene);
        });

        // --- Show the Stage ---
        stage.setTitle("Score App");
        stage.setScene(inputScene);
        stage.show();
    }

    private void saveToFile(int number) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.print(number + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> loadScoresFromFile() {
        List<Integer> scores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextInt()) {
                scores.add(scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scores.sort(Collections.reverseOrder());
        return scores;
    }

    // Check if the number already exists in the file
    private boolean isNumberAlreadyExist(int number) {
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextInt()) {
                if (scanner.nextInt() == number) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        launch();
    }
}
*/
