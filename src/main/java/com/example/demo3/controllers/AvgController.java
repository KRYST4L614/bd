package com.example.demo3.controllers;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.tableData.result.ResultGroupsData;
import com.example.demo3.tableData.result.ResultPeopleData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class AvgController {
    @FXML
    private DatePicker beginPicker;
    @FXML
    private DatePicker endPicker;
    @FXML
    private TextField studentIdField;
    @FXML
    private TextField groupIdField;
    @FXML
    private TextField teacherIdField;
    @FXML
    private TextField subjectIdField;

    private ObservableList<String> items = FXCollections.observableArrayList("Students", "Teachers", "Groups", "Subjects");
    @FXML
    private ComboBox<String> groupComboBox;
    @FXML
    private Label completeLabel;

    @FXML
    private void initialize() {
        groupComboBox.setItems(items);
        groupComboBox.setValue("Students");
        studentIdField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!studentIdField.getText().isEmpty()) {
                    groupComboBox.setValue("Students");
                    teacherIdField.setDisable(true);
                    groupIdField.setDisable(true);
                    subjectIdField.setDisable(true);
                } else {
                    enableAllFields();
                };
            }
        });

        teacherIdField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!teacherIdField.getText().isEmpty()) {
                    groupComboBox.setValue("Teachers");
                    studentIdField.setDisable(true);
                    groupIdField.setDisable(true);
                    subjectIdField.setDisable(true);
                } else {
                    enableAllFields();
                }
            }
        });

        subjectIdField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!subjectIdField.getText().isEmpty()) {
                    groupComboBox.setValue("Subjects");
                    studentIdField.setDisable(true);
                    groupIdField.setDisable(true);
                    teacherIdField.setDisable(true);
                } else {
                    enableAllFields();
                }
            }
        });

        groupIdField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!groupIdField.getText().isEmpty()) {
                    groupComboBox.setValue("Groups");
                    studentIdField.setDisable(true);
                    subjectIdField.setDisable(true);
                    teacherIdField.setDisable(true);
                } else {
                    enableAllFields();
                }
            }
        });
    }

    @FXML
    protected void onFindClick() {
        if (beginPicker.getValue() == null || beginPicker.getValue().toString().trim().isEmpty() ||
                endPicker.getValue() == null || endPicker.getValue().toString().trim().isEmpty()) {
            App.showAlert(new Alert(Alert.AlertType.ERROR, "Выберите промежуток времени!"));
            return;
        }
        String sqlString;
        switch (groupComboBox.getValue()) {
            case "Students":
                sqlString = "SELECT * FROM avgMarksPerStudent('"+ beginPicker.getValue().toString() + "', '" + endPicker.getValue().toString() + "')";
                if (!studentIdField.getText().trim().isEmpty()) {
                    sqlString += " WHERE student_id = " + studentIdField.getText();
                }
                try {
                    buildResultPeople(sqlString).showAndWait();
                }
                catch (Exception e) {
                    return;
                }
                break;
            case "Groups":
                sqlString = "SELECT * FROM avgMarksPerGroups('"+ beginPicker.getValue().toString() + "', '" + endPicker.getValue().toString() + "')";
                if (!groupIdField.getText().trim().isEmpty()) {
                    sqlString += " WHERE group_id = " + groupIdField.getText();
                }
                try {
                    buildResultGroups(sqlString).showAndWait();
                }
                catch (Exception e) {
                    return;
                }
                break;
            case "Teachers":
                sqlString = "SELECT * FROM avgMarksPerTeacher('"+ beginPicker.getValue().toString() + "', '" + endPicker.getValue().toString() + "')";
                if (!teacherIdField.getText().trim().isEmpty()) {
                    sqlString += " WHERE teacher_id = " + teacherIdField.getText();
                }
                try {
                    buildResultPeople(sqlString).showAndWait();
                }
                catch (Exception e) {
                    return;
                }
                break;
            case "Subjects":
                sqlString = "SELECT * FROM avgMarksPerSubjects('"+ beginPicker.getValue().toString() + "', '" + endPicker.getValue().toString() + "')";
                if (!subjectIdField.getText().trim().isEmpty()) {
                    sqlString += " WHERE subject_id = " + subjectIdField.getText();
                }
                try {
                    buildResultGroups(sqlString).showAndWait();
                } catch (Exception e) {
                    return;
                }
                break;

        }
    }

    private void enableAllFields() {
        teacherIdField.setDisable(false);
        studentIdField.setDisable(false);
        subjectIdField.setDisable(false);
        groupIdField.setDisable(false);
    }

    private Stage buildResultPeople(String sqlString) {
        Stage stage = new Stage();
        ObservableList<ResultPeopleData> peopleData = FXCollections.observableArrayList();
        TableView<ResultPeopleData> tablePeople = new TableView<ResultPeopleData>(peopleData);
        TableColumn<ResultPeopleData, Integer> peopleIdColumn = new TableColumn<ResultPeopleData, Integer>("id");
        peopleIdColumn.setCellValueFactory(new PropertyValueFactory<ResultPeopleData, Integer>("studentId"));
        tablePeople.getColumns().add(peopleIdColumn);
        TableColumn<ResultPeopleData, String> firstNameColumn = new TableColumn<ResultPeopleData, String>("firstName");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<ResultPeopleData, String>("firstName"));
        tablePeople.getColumns().add(firstNameColumn);
        TableColumn<ResultPeopleData, String> lastNameColumn = new TableColumn<ResultPeopleData, String>("lastName");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<ResultPeopleData, String>("lastName"));
        tablePeople.getColumns().add(lastNameColumn);
        TableColumn<ResultPeopleData, Double> avgColumn = new TableColumn<ResultPeopleData, Double>("avg");
        avgColumn.setCellValueFactory(new PropertyValueFactory<ResultPeopleData, Double>("avg"));
        tablePeople.getColumns().add(avgColumn);
        FlowPane root = new FlowPane(tablePeople);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        List<Object[]> resList = makeQuery(sqlString);
        for (Object[] item : resList) {
            ResultPeopleData resultStudentData = new ResultPeopleData((Integer)item[0], (String)item[1], (String)item[2], ((BigDecimal)item[3]).doubleValue());
            peopleData.add(resultStudentData);
        }
        return stage;
    }

    private Stage buildResultGroups(String sqlString) {
        Stage stage = new Stage();
        ObservableList<ResultGroupsData> dataGroups = FXCollections.observableArrayList();
        TableView<ResultGroupsData> tableStudent = new TableView<ResultGroupsData>(dataGroups);
        TableColumn<ResultGroupsData, Integer> groupIdColumn = new TableColumn<ResultGroupsData, Integer>("id");
        groupIdColumn.setCellValueFactory(new PropertyValueFactory<ResultGroupsData, Integer>("groupId"));
        tableStudent.getColumns().add(groupIdColumn);
        TableColumn<ResultGroupsData, String> groupNameColumn = new TableColumn<ResultGroupsData, String>("name");
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<ResultGroupsData, String>("name"));
        tableStudent.getColumns().add(groupNameColumn);
        TableColumn<ResultGroupsData, Double> avgColumn = new TableColumn<ResultGroupsData, Double>("avg");
        avgColumn.setCellValueFactory(new PropertyValueFactory<ResultGroupsData, Double>("avg"));
        tableStudent.getColumns().add(avgColumn);
        FlowPane root = new FlowPane(tableStudent);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        List<Object[]> resList = makeQuery(sqlString);
        for (Object[] item : resList) {
            ResultGroupsData resultGroupsData = new ResultGroupsData((Integer)item[0], (String)item[1], ((BigDecimal)item[2]).doubleValue());
            dataGroups.add(resultGroupsData);
        }
        return stage;
    }

    private List<Object[]> makeQuery(String sqlString) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createSQLQuery(sqlString);
            List<Object[]> res = query.getResultList();
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
            return res;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            App.showAlert(new Alert(Alert.AlertType.ERROR, e.getMessage()));
            throw e;
        }
    }
    @FXML
    protected void onCancelClick() {
        Stage stage = (Stage) completeLabel.getScene().getWindow();
        stage.close();
    }
}
