package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    static private Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("enter-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Деканат");
        stage.setScene(scene);
        stage.show();
        primaryStage = stage;
    }

    static public void changeScene(Scene scene) {
        primaryStage.setScene(scene);
    }

    static public void showDialog(Stage stage) {
        stage.showAndWait();
    }
    static public void showAlert(Alert alert) {
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}