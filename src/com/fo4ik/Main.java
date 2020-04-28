package com.fo4ik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String DB_URL = "jdbc:h2:C:/Users//Fo4Ik/JavaProjects/Game/src/com/fo4ik/db/db_btb";
    public static final String DB_Driver = "org.h2.Driver";

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Controls/main.fxml"));
        primaryStage.setTitle("BtB");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
