package com.example.demo3.controllers.subjects;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.entity.Marks;
import com.example.demo3.entity.Subjects;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.time.LocalDate;

public class AddSubjectsController {
    @FXML
    private TextField nameTextField;
    @FXML
    private Label completeLabel;

    @FXML
    protected void onAddClick() {
        if (nameTextField.getText().trim().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Не все поля заполнены!"));
            return;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Subjects subjects = new Subjects(1, nameTextField.getText());
            session.save(subjects);
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
        Stage stage = (Stage) nameTextField.getScene().getWindow();
        stage.close();
    }
}
