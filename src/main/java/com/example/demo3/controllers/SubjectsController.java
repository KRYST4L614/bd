package com.example.demo3.controllers;

import com.example.demo3.HibernateUtil;
import com.example.demo3.entity.Subjects;
import com.example.demo3.tableData.SubjectsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class SubjectsController extends ControllerBD {
    @FXML
    private TableView<SubjectsData> table;
    private ObservableList<SubjectsData> subjectsData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<SubjectsData, Integer> id;
    @FXML
    private TableColumn<SubjectsData, String> name;

    @FXML
    private void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<SubjectsData, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<SubjectsData, String>("name"));
        table.setItems(subjectsData);
        List<Subjects> resList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Subjects ");
            resList = query.getResultList();
            for (Subjects item : resList) {
                subjectsData.add(new SubjectsData(item.getId(), item.getName()));
            }
        }
    }

    @Override
    public void refresh() {
        subjectsData.clear();
        List<Subjects> resList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Subjects ");
            resList = query.getResultList();
            for (Subjects item : resList) {
                subjectsData.add(new SubjectsData(item.getId(), item.getName()));
            }
        }
    }


    @FXML
    protected void onChangeNode() {
        super.showAndWaitScene("change-subjects-view.fxml");
    }


    @FXML
    protected void onAddNode() {
        showAndWaitScene("add-subjects-view.fxml");
    }


    @FXML
    protected void onDeleteNode() {
        showAndWaitScene("delete-subjects-view.fxml");
    }
}
