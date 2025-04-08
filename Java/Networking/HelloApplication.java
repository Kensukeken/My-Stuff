package com.lab17;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        HBox buttonBox = new HBox();

        // Created both panes from ServerPane and ClientPane java classes
        ServerPane serverPane = new ServerPane();
        ClientPane clientPane = new ClientPane();

        Button serverButton = new Button("Server");
        serverButton.setOnAction(e -> {
            root.setCenter(serverPane);
            clientPane.setVisible(false);
        });

        Button clientButton = new Button("Client");
        clientButton.setOnAction(e -> {
            root.setCenter(clientPane);
            serverPane.setVisible(false);
        });

        buttonBox.getChildren().addAll(serverButton, clientButton);
        root.setTop(buttonBox);
        root.setCenter(serverPane);

        Scene scene = new Scene(root, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}