package com.lab17;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import java.net.*;

public class ClientPane extends VBox {
    private TextArea log = new TextArea();

    public ClientPane() {
        Button sendBtn = new Button("Send Messages");
        sendBtn.setOnAction(e -> sendMessages());

        log.setEditable(false);
        getChildren().addAll(new Label("Client Log:"), log, sendBtn);
        setSpacing(10);
    }

    // Connects to server and sends messages
    private void sendMessages() {
        new Thread(() -> {
            try (Socket s = new Socket("localhost", 5555);
                 PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {

                log("Connected!");

                // Method to send 10 test messages
                for (int i = 1; i <= 10; i++) {
                    out.println("Message " + i);
                    log("Sent: " + i);
                    Thread.sleep(300);
                }

                log("Done!");
            } catch (Exception e) {
                log("Error: " + e.getMessage());
            }
        }).start();
    }

    // Updates UI thread safely
    private void log(String msg) {
        javafx.application.Platform.runLater(() ->
                log.appendText(msg + "\n")
        );
    }
}