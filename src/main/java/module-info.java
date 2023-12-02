module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires org.controlsfx.controls;
    requires lombok;
    requires java.persistence;
    requires java.sql;
    requires net.bytebuddy;
    requires java.xml.bind;
    requires com.sun.xml.bind;
    requires com.fasterxml.classmate;
    requires jdk.unsupported;
    requires org.hibernate.orm.core;

    opens com.example.demo3 to javafx.fxml;
    exports com.example.demo3;
    opens com.example.demo3.entity to org.hibernate.orm.core;
    exports com.example.demo3.controllers;
    opens com.example.demo3.controllers to javafx.fxml;
    exports com.example.demo3.tableData;
    opens com.example.demo3.tableData to javafx.fxml;
}