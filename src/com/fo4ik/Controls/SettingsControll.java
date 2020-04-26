package com.fo4ik.Controls;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    void initialize() {
        back.setOnAction(event -> {
            try {
                ToGame(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        apply.setOnAction(event -> {
            if(new_login.getText().isEmpty()){
               errors.setText("Empty Login");
            } else {
            }
        });
    }

    @FXML
    private void ToGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
        Scene window = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(window);
        stage.show();
    }
}
