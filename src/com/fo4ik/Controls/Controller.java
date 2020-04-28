package com.fo4ik.Controls;

import com.fo4ik.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class Controller {
    private Stage stage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_input;

    @FXML
    private PasswordField psw_input;

    @FXML
    private Button login_btn;

    @FXML
    private Button go_to_signup_button;

    @FXML
    private Label errors;


    @FXML
    void initialize() {
        login_btn.setOnAction(event -> {
            DBHelper dbHelper = new DBHelper();
            try{
                dbHelper.openDB();
                ArrayList<String> list = dbHelper.getInfoUser(login_input.getText().trim());
                errors.setText(list.get(1));
                if(login_input.getText().trim().equals(list.get(1)) && psw_input.getText().trim().equals(list.get(2))){
                    File file = new File("tmp.btb");
                    FileWriter fr = new FileWriter("tmp.btb");
                    fr.write(list.get(1));
                    fr.close();
                    ToGame(event);
                } else {
                    errors.setText("Неверные данные");
                }
                dbHelper.close();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
    }


    @FXML
    private void ToSignUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Scene window = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(window);
        stage.show();
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
