package com.example.demo3.controllers.subjects;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.controllers.ControllerBD;
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
        refresh();
    }

    @Override
    public void refresh() {
        subjectsData.clear();
        List<Subjects> resList = HibernateUtil.getAll(Subjects.class);
        for (Subjects item : resList) {
            subjectsData.add(new SubjectsData(item.getId(), item.getName()));
        }
    }


    @FXML
    protected void onChangeNode() {
        if (!App.checkRole()) {
            return;
        }
        super.showAndWaitScene("change-subjects-view.fxml");
    }


    @FXML
    protected void onAddNode() {
        if (!App.checkRole()) {
            return;
        }
        showAndWaitScene("add-subjects-view.fxml");
    }


    @FXML
    protected void onDeleteNode() {
        if (!App.checkRole()) {
            return;
        }
        showAndWaitScene("delete-subjects-view.fxml");
    }
}
