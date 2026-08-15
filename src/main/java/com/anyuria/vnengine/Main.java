package com.anyuria.vnengine;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        stage.setTitle("Java Visual Novel Engine");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}