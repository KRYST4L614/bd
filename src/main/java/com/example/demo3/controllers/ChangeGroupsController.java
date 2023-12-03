package com.example.demo3.controllers;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.entity.Groups;
import com.example.demo3.entity.Subjects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import org.hibernate.Session;

public class ChangeGroupsController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private Label completeLabel;

    @FXML
    protected void onChangeClick() {
        if (idField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Не все поля заполнены!"));
            return;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Groups groups = session.get(Groups.class, Integer.parseInt(idField.getText()));
            if (!nameField.getText().trim().isEmpty()) {
                groups.setName(nameField.getText());
            }
            session.save(groups);
            session.getTransaction().commit();
            Thread thread = new Thread(() -> {
                completeLabel.setVisible(true);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                completeLabel.setVisible(false);
            });
            thread.start();
        } catch (Exception e) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, e.getMessage()));
        }
    }

    @FXML
    protected void onCancelClick() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}
