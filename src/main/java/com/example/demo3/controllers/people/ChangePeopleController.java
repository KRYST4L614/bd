package com.example.demo3.controllers.people;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.entity.People;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;

public class ChangePeopleController {
    @FXML
    private TextField idTextField;
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
    protected void onChangeClick() {
        if (idTextField.getText().trim().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Не указан id!"));
            return;
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            People people = session.get(People.class, Integer.parseInt(idTextField.getText()));
            if (!firstNameField.getText().trim().isEmpty()) {
                people.setFirstName(firstNameField.getText());
            }
            if (!lastNameField.getText().trim().isEmpty()) {
                people.setLastName(lastNameField.getText());
            }
            if (!patherNameField.getText().trim().isEmpty()) {
                people.setPatherName(patherNameField.getText());
            }
            if (!groupIdField.getText().trim().isEmpty()) {
                people.setGroupId(Integer.parseInt(patherNameField.getText()));
            }
            if (!typeComboBox.getValue().toString().trim().isEmpty()) {
                people.setType(typeComboBox.getValue().toString());
            }
            session.save(people);
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
