package com.example.demo3.controllers;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.entity.Marks;
import com.example.demo3.entity.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;

public class AddPeopleController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField patherNameField;
    @FXML
    private TextField groupIdField;
    private ObservableList<String> types = FXCollections.observableArrayList("S(Student)", "T(Teacher)");
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private Label completeLabel;

    @FXML
    protected void initialize() {
        typeComboBox.setItems(types);
        typeComboBox.setValue("S(Student)");
    }

    @FXML
    protected void onAddClick() {
        if (firstNameField.getText().trim().isEmpty() || lastNameField.getText().trim().isEmpty() ||
                patherNameField.getText().trim().isEmpty() || groupIdField.getText().trim().isEmpty() || typeComboBox.getValue().toString().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Не все поля заполнены!"));
            return;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            People people = new People(1, firstNameField.getText(),
                    lastNameField.getText(), patherNameField.getText(),
                    Integer.parseInt(groupIdField.getText()), typeComboBox.getValue().toString());
            session.save(people);
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
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
