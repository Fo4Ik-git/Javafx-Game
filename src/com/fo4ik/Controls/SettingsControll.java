package com.fo4ik.Controls;

import com.fo4ik.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SettingsControll {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private TextField new_login;

    @FXML
    private Button apply;

    @FXML
    private TextField new_pass;

    @FXML
    private TextField new_pass2;

    @FXML
    private Label errors;

    @FXML
    private TextField old_pass;

    public String login;

    @FXML
    void initialize() {
        DBHelper dbHelper = new DBHelper();

        back.setOnAction(event -> {
            try {
                ToGame(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        apply.setOnAction(event -> {
            if (new_login.getText().isEmpty()) {
                errors.setText("Empty Login");
            } else {
                dbHelper.openDB();
                login = dbHelper.returnLogin();
                ArrayList<String> list = dbHelper.getInfoUser(login);
                dbHelper.close();
                if(old_pass.getText().trim().equals(list.get(2))){
                    dbHelper.openDB();
                    dbHelper.updateLogin(Integer.valueOf(list.get(0)), new_login.getText().trim());
                    dbHelper.close();
                    try {
                        ToLogin(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                errors.setText("old pass input " + old_pass.getText().trim() + "\n" +
                        "pass " + list.get(0));

            }
        });
        dbHelper.close();
    }

    @FXML
    private void ToGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene window = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(window);
        stage.show();
    }
    @FXML
    private void ToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene window = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(window);
        stage.show();
    }
}
