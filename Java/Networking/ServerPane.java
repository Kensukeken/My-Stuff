package com.lab17;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import java.net.*;

public class ServerPane extends VBox {
    private final TextArea log = new TextArea();

    public ServerPane() {
        Button startBtn = new Button("Start Server");
        startBtn.setOnAction(e -> startServer());

        log.setEditable(false);
        getChildren().addAll(new Label("Server Log:"), log, startBtn);
        setSpacing(10);
    }

    // Handles all server operations in a background thread
    private void startServer() {
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(5555)) {
                log("Server started");

                try (Socket client = server.accept();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(client.getInputStream()))) {

                    log("Client connected");
                    String message;
                    while ((message = in.readLine()) != null) {
                        log("Got: " + message);
                    }
                }
                log("Server stopped");
            } catch (IOException e) {
                log("Error: " + e.getMessage());
            }
        }).start();
    }

    // Updates UI thread safely
    private void log(String text) {
        javafx.application.Platform.runLater(() ->
                log.appendText(text + "\n")
        );
    }
}