package com.fo4ik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Controls/main.fxml"));
        primaryStage.setTitle("BtB");
        primaryStage.setScene(new Scene(root, 600, 400));
        //primaryStage.setResizable(false);
        primaryStage.show();


    }
    // Создание сцены


    public static void main(String[] args) {

        try {
            DBHelper dbHelper = new DBHelper();
            dbHelper.openDB();
            dbHelper.createDB();
            dbHelper.close();
        } catch (SQLException throwables) {
            throwables.getMessage();
        }

        launch(args);
    }

    //Создание БД
}
