package com.example.demo3.controllers;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.entity.Marks;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.time.LocalDate;

public class DeleteMarksController {
    @FXML
    private TextField id_text_field;

    @FXML
    private Label completeLabel;

    @FXML
    protected void onDeleteClick() {
        if (id_text_field.getText().trim().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Не указан id!"));
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(Marks.class, Integer.parseInt(id_text_field.getText())));
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
        Stage stage = (Stage) id_text_field.getScene().getWindow();
        stage.close();
    }
}
