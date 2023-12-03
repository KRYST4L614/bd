package com.example.demo3.controllers.people;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.entity.People;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

public class DeletePeopleController {
    @FXML
    private TextField idTextField;

    @FXML
    private Label completeLabel;

    @FXML
    protected void onDeleteClick() {
        if (idTextField.getText().trim().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Не указан id!"));
            return;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(People.class, Integer.parseInt(idTextField.getText())));
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
        Stage stage = (Stage) idTextField.getScene().getWindow();
        stage.close();
    }
}
