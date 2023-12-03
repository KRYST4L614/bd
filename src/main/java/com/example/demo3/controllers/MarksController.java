package com.example.demo3.controllers;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.tableData.MarksData;
import com.example.demo3.entity.Marks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;

import javax.persistence.Query;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MarksController extends ControllerBD {
    @FXML
    private TableView<MarksData> table;
    private ObservableList<MarksData> marksData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<MarksData, Integer> id;
    @FXML
    private TableColumn<MarksData, Integer> studentId;

    @FXML
    private TableColumn<MarksData, Integer> subjectId;
    @FXML
    private TableColumn<MarksData, Integer> teacherId;
    @FXML
    private TableColumn<MarksData, Integer> groupId;
    @FXML
    private TableColumn<MarksData, Integer> mark;
    @FXML
    private TableColumn<MarksData, LocalDate> date;

    @FXML
    private void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<MarksData, Integer>("id"));
        studentId.setCellValueFactory(new PropertyValueFactory<MarksData, Integer>("studentId"));
        subjectId.setCellValueFactory(new PropertyValueFactory<MarksData, Integer>("subjectId"));
        teacherId.setCellValueFactory(new PropertyValueFactory<MarksData, Integer>("teacherId"));
        groupId.setCellValueFactory(new PropertyValueFactory<MarksData, Integer>("groupId"));
        mark.setCellValueFactory(new PropertyValueFactory<MarksData, Integer>("mark"));
        date.setCellValueFactory(new PropertyValueFactory<MarksData, LocalDate>("date"));
        table.setItems(marksData);
        List<Marks> resList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Marks");
            resList = query.getResultList();
            for (Marks item : resList) {
                marksData.add(new MarksData(item.getId(), item.getStudentId(), item.getSubjectId(),
                        item.getTeacherId(), item.getGroupId(), item.getMark(), item.getDate()));
            }
        }
    }

    @Override
    public void refresh() {
        marksData.clear();
        List<Marks> resList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Marks");
            resList = query.getResultList();
            for (Marks item : resList) {
                marksData.add(new MarksData(item.getId(), item.getStudentId(), item.getSubjectId(),
                        item.getTeacherId(), item.getGroupId(), item.getMark(), item.getDate()));
            }
        }
    }

    @FXML
    protected void onChangeNode() {
        super.showAndWaitScene("change-marks-view.fxml");
    }


    @FXML
    protected void onAddNode() {
        showAndWaitScene("add-marks-view.fxml");
    }


    @FXML
    protected void onDeleteNode() {
        showAndWaitScene("delete-marks-view.fxml");
    }
}
