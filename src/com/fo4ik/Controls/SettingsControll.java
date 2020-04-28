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
    private Button del_user;

    @FXML
    private TextField new_pass;

    @FXML
    private TextField new_pass2;

    @FXML
    private Label errors;

    @FXML
    private TextField old_pass;

    public String login;
    ArrayList<String> list;

    @FXML
    void initialize() {
        DBHelper dbHelper = new DBHelper();
        login = dbHelper.returnLogin();
        list = dbHelper.getInfoUser(login);

        back.setOnAction(event -> {
            try {
                ToGame(event);
            } catch (IOException e) {
                e.getMessage();
            }
        });

        del_user.setOnAction(event -> {
            try {
                dbHelper.openDB();
                list = dbHelper.getInfoUser(login);
                dbHelper.delUser(Integer.parseInt(list.get(0)));
                ToLogin(event);
                dbHelper.close();
            } catch (Exception e) {
            }
        });


        apply.setOnAction(event -> {
            dbHelper.openDB();
            list = dbHelper.getInfoUser(login);
            dbHelper.close();

            if (!new_login.getText().isEmpty()) {
                if (old_pass.getText().trim().equals(list.get(2))) {
                    dbHelper.openDB();
                    dbHelper.updateLogin(Integer.valueOf(list.get(0)), new_login.getText().trim());
                    dbHelper.close();
                    ToLogin(event);
                } else {
                    errors.setText("Старый пароль не совпадает ");
                }
            }
            if (!new_pass.getText().isEmpty()) {
                if (new_pass.getText().equals(new_pass2.getText())) {
                    if (old_pass.getText().equals(list.get(2))) {
                        errors.setText("меняем пароль");
                        dbHelper.openDB();
                        dbHelper.updatePswd(Integer.parseInt(list.get(0)), new_pass.getText().trim());
                        dbHelper.close();
                        ToLogin(event);
                    } else {
                        errors.setText("Старый пароль не совпадает ");
                    }
                } else {
                    errors.setText("Новые пароли не совпадают ");
                }
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
    private void ToLogin(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene window = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(window);
            stage.show();
        } catch (IOException e) {

        }

    }
}
