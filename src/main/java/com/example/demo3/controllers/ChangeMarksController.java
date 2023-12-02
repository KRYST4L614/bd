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

public class ChangeMarksController {
    @FXML
    private TextField id_text_field;
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
    private TextField dateField;
    @FXML
    private Label completeLabel;

    @FXML
    protected void onChangeClick() {
        if (id_text_field.getText().trim().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Не указан id!"));
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Marks marks = session.get(Marks.class, Integer.parseInt(id_text_field.getText()));
            if (!studentIdField.getText().trim().isEmpty()) {
                marks.setStudentId(Integer.parseInt(studentIdField.getText()));
            }
            if (!subjectIdField.getText().trim().isEmpty()) {
                marks.setSubjectId(Integer.parseInt(subjectIdField.getText()));
            }
            if (!teacherIdField.getText().trim().isEmpty()) {
                marks.setTeacherId(Integer.parseInt(teacherIdField.getText()));
            }
            if (!groupIdField.getText().trim().isEmpty()) {
                marks.setGroupId(Integer.parseInt(teacherIdField.getText()));
            }
            if (!valueField.getText().trim().isEmpty()) {
                marks.setMark(Integer.parseInt(valueField.getText()));
            }
            if (!dateField.getText().trim().isEmpty()) {
                marks.setDate(LocalDate.parse(dateField.getText()));
            }
            session.save(marks);
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
