package com.example.practicabd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1700, 1000);
        stage.setTitle("Hello!");
        stage.setMinWidth(1270);
        stage.setMinHeight(920);
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
        scene.getStylesheets().add
                (HelloApplication.class.getResource("css/styles.css").toExternalForm());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}