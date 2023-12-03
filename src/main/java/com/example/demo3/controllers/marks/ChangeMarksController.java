package com.example.demo3.controllers.marks;

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

public class ChangeMarksController {
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
    protected void onChangeClick() {
        if (idTextField.getText().trim().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Не указан id!"));
            return;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Marks marks = session.get(Marks.class, Integer.parseInt(idTextField.getText()));
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
            if (!datePicker.getValue().toString().isEmpty()) {
                marks.setDate(datePicker.getValue());
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
        Stage stage = (Stage) idTextField.getScene().getWindow();
        stage.close();
    }
}
