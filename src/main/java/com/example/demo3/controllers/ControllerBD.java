package com.example.demo3.controllers;

import com.example.demo3.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class ControllerBD {

    abstract public void refresh();

    protected void showAndWaitScene(String uri) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(uri));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.showAndWait();
        refresh();
    }

    @FXML
    protected void showSubjects() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("subjects-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        App.changeScene(scene);
    }

    @FXML
    protected void showGroups() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("groups-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        App.changeScene(scene);
    }

    @FXML
    protected void showMarks() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("marks-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        App.changeScene(scene);
    }

    @FXML
    protected void showPeople() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("people-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        App.changeScene(scene);
    }

    @FXML
    protected void showAvg() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("avg-view.fxml"));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.showAndWait();
    }
}
