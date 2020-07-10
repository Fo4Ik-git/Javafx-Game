package com.fo4ik.Controls;

import com.fo4ik.DBHelper;
import com.fo4ik.ResourceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class SettingsControll {

    public String login;
    ArrayList<String> list;
    Locale locale = Locale.getDefault();
    DBHelper dbHelper = new DBHelper();
    ResourceFactory resourceFactory = new ResourceFactory();
    ResourceBundle bundle = ResourceBundle.getBundle("Language", locale);
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
    @FXML
    private Button del_user;
    @FXML
    private Label select_lang_text;
    @FXML
    private MenuButton menu_languages;
    private Stage stage;

    @FXML
    void initialize() {
        dbHelper.openDB();
        login = dbHelper.returnLogin();
        list = dbHelper.getInfoUser(login);
        dbHelper.close();
        bundle = ResourceBundle.getBundle("Language", new Locale(list.get(6), list.get(7)));
        lang();
        // При запуске инициализирует изык пользователя и логин


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

    public void lang() {
        menu_languages.setText(bundle.getString("selected_lang"));
        // login_btn.setText(bundle.getString("main_authorize"));
        // main_text.setText(bundle.getString("main_text"));
        //go_to_signup_button.setText(bundle.getString("main_register"));
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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene window = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(window);
            stage.show();
        } catch (IOException e) {

        }

    }

    public void Russian(ActionEvent event) {
        dbHelper.openDB();
        dbHelper.returnLogin();
        dbHelper.getInfoUser(login);
        dbHelper.update("lang", "ru", Integer.parseInt(list.get(0)));
        dbHelper.update("country", "RU", Integer.parseInt(list.get(0)));
        dbHelper.close();
        try {
            ToGame(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void English(ActionEvent event) {
        dbHelper.openDB();
        dbHelper.returnLogin();
        dbHelper.getInfoUser(login);
        dbHelper.update("lang", "en", Integer.parseInt(list.get(0)));
        dbHelper.update("country", "US", Integer.parseInt(list.get(0)));
        dbHelper.close();
        try {
            ToGame(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
