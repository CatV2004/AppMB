module com.nmc.fxenglishapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.nmc.fxenglishapp to javafx.fxml;
    exports com.nmc.fxenglishapp;
    exports com.nmc.pojo;
    exports com.nmc.services;
}
