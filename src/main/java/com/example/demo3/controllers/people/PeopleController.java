package com.example.demo3.controllers.people;

import com.example.demo3.App;
import com.example.demo3.HibernateUtil;
import com.example.demo3.controllers.ControllerBD;
import com.example.demo3.entity.Marks;
import com.example.demo3.entity.People;
import com.example.demo3.tableData.MarksData;
import com.example.demo3.tableData.PeopleData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class PeopleController extends ControllerBD {
    @FXML
    private TableView<PeopleData> table;
    private ObservableList<PeopleData> peopleData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<PeopleData, Integer> id;
    @FXML
    private TableColumn<PeopleData, String> firstName;

    @FXML
    private TableColumn<PeopleData, String> lastName;
    @FXML
    private TableColumn<PeopleData, String> patherName;
    @FXML
    private TableColumn<PeopleData, Integer> groupId;
    @FXML
    private TableColumn<PeopleData, String> type;

    @FXML
    private void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<PeopleData, Integer>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<PeopleData, String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<PeopleData, String>("lastName"));
        patherName.setCellValueFactory(new PropertyValueFactory<PeopleData, String>("patherName"));
        groupId.setCellValueFactory(new PropertyValueFactory<PeopleData, Integer>("groupId"));
        type.setCellValueFactory(new PropertyValueFactory<PeopleData, String>("type"));
        table.setItems(peopleData);
        refresh();
    }

    @Override
    public void refresh() {
        peopleData.clear();
        List<People> resList = HibernateUtil.getAll(People.class);
        for (People item : resList) {
            peopleData.add(new PeopleData(item.getId(), item.getFirstName(), item.getLastName(),
                    item.getPatherName(), item.getGroupId(), item.getType()));
        }
    }

    @FXML
    protected void onChangeNode() {
        if (!App.checkRole()) {
            return;
        }
        super.showAndWaitScene("change-people-view.fxml");
    }


    @FXML
    protected void onAddNode() {
        if (!App.checkRole()) {
            return;
        }
        showAndWaitScene("add-people-view.fxml");
    }


    @FXML
    protected void onDeleteNode() {
        if (!App.checkRole()) {
            return;
        }
        showAndWaitScene("delete-people-view.fxml");
    }
}
