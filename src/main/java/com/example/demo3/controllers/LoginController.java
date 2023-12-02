package com.example.demo3.controllers;

import com.example.demo3.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class LoginController {
    @FXML
    private Label login_label;
    @FXML
    private Label password_label;
    @FXML
    private TextField login_text_field;
    @FXML
    private PasswordField password_field;

    @FXML
    protected void onLoginClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("marks-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            if (!new LoginChecker().logIn(login_text_field.getText(), password_field.getText())) {
                App.showAlert(new Alert(Alert.AlertType.ERROR, "Неверный логин или пароль!"));
                return;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        App.changeScene(scene);
    }
}