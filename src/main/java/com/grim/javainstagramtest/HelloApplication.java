package com.grim.javainstagramtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(root);

        stage.show();
        stage.setWidth(420);
        stage.setHeight(460);
        stage.setResizable(false);
        stage.setTitle("JavaFX Test");
        stage.setScene(scene);
    }
}