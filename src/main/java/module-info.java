module com.example.practicabd {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;
    requires java.sql;

    opens com.example.practicabd to javafx.fxml;
    exports com.example.practicabd;

    exports  com.example.practicabd.modulos;

    exports com.example.practicabd.dao;
}