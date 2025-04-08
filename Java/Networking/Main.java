package com.lab16;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class HelloApplication extends Application {
    private static final int PORT = 5555;

    @Override
    public void start(Stage stage) {
        Button serverBtn = new Button("Start Server"); // Starts server
        Button clientBtn = new Button("Send Date");    // Sends date

        serverBtn.setOnAction(e -> startServer());
        clientBtn.setOnAction(e -> startClient());

        VBox root = new VBox(10, serverBtn, clientBtn);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        stage.setScene(new Scene(root, 300, 150));
        stage.show();
    }

    // Server logic
    public static void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server started. Waiting for a client...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String receivedDate = in.readLine();
                System.out.println("Received date: " + receivedDate);

                in.close();
                clientSocket.close();
                System.out.println("Server done.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Client logic
    public static void startClient() {
        new Thread(() -> {
            try (Socket socket = new Socket("localhost", PORT)) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String currentDate = LocalDateTime.now().toString();
                out.println(currentDate);
                System.out.println("Date sent: " + currentDate);
                out.close();
            } catch (IOException e) {
                System.out.println("Client error: " + e.getMessage());
            }
        }).start();
    }

    // Main Method
    public static void main(String[] args) {
        launch();
    }
}
