package com.example.demo3.controllers.groups;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.Roles;
import com.example.demo3.controllers.ControllerBD;
import com.example.demo3.entity.Groups;
import com.example.demo3.tableData.GroupsData;
import com.example.demo3.tableData.SubjectsData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class GroupsController extends ControllerBD {
    @FXML
    private TableView<GroupsData> table;
    private ObservableList<GroupsData> groupsData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<GroupsData, Integer> id;
    @FXML
    private TableColumn<GroupsData, String> name;

    @FXML
    private void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<GroupsData, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<GroupsData, String>("name"));
        table.setItems(groupsData);
        refresh();
    }

    @Override
    public void refresh() {
        groupsData.clear();
        List<Groups> resList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Groups ");
            resList = query.getResultList();
            for (Groups item : resList) {
                groupsData.add(new GroupsData(item.getId(), item.getName()));
            }
        }
    }


    @FXML
    protected void onChangeNode() {
        if (!App.checkRole()) {
            return;
        }
        super.showAndWaitScene("change-groups-view.fxml");
    }


    @FXML
    protected void onAddNode() {
        if (!App.checkRole()) {
            return;
        }
        showAndWaitScene("add-groups-view.fxml");
    }


    @FXML
    protected void onDeleteNode() {
        if (!App.checkRole()) {
            return;
        }
        showAndWaitScene("delete-groups-view.fxml");
    }
}
