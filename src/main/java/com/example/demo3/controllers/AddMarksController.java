package com.example.demo3.controllers;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.entity.Marks;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

public class AddMarksController {
    @FXML
    private TextField idTextField;
    @FXML
    private TextField studentIdField;
    @FXML
    private TextField subjectIdField;
    @FXML
    private TextField teacherIdField;
    @FXML
    private TextField groupIdField;
    @FXML
    private TextField valueField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label completeLabel;

    @FXML
    protected void onAddClick() {
        if (idTextField.getText().trim().isEmpty() || studentIdField.getText().trim().isEmpty() || subjectIdField.getText().trim().isEmpty() ||
        teacherIdField.getText().trim().isEmpty() || groupIdField.getText().trim().isEmpty() || valueField.getText().trim().isEmpty() ||
                datePicker.getValue() == null || datePicker.getValue().toString().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Не все поля заполнены!"));
            return;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Marks marks = new Marks(Integer.parseInt(idTextField.getText()), Integer.parseInt(studentIdField.getText()),
                    Integer.parseInt(subjectIdField.getText()), Integer.parseInt(teacherIdField.getText()),
                    Integer.parseInt(groupIdField.getText()), Integer.parseInt(valueField.getText()),
                    datePicker.getValue());
            session.save(marks);
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
