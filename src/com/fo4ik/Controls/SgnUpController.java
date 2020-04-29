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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SgnUpController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_input;

    @FXML
    private PasswordField pswd_input;

    @FXML
    private Label singup_text;

    @FXML
    private Button signup_button;

    @FXML
    private PasswordField pswd_input2;

    @FXML
    private Label errors;

    @FXML
    private Button go_to_login_button;

    ResourceBundle bundleDefault = ResourceBundle.getBundle("Language");


    @FXML
    void initialize() {
        lang();



        signup_button.setOnAction(event -> {
            DBHelper dbHelper = new DBHelper();
            if (!login_input.getText().equals("") || pswd_input.getText().equals("") || pswd_input2.getText().equals("")) {
                if (pswd_input.getText().equals(pswd_input2.getText())) {
                    try {
                        dbHelper.openDB();
                        dbHelper.createUser(login_input.getText().trim(), pswd_input.getText().trim(), "10", "0", "100", "en", "US");
                        dbHelper.close();
                        ToMain(event);
                    } catch (Exception e) {
                        errors.setText("Пользователь уже создан");
                    }
                } else {
                    errors.setText("Incorrect passwords");
                }
            } else {
                errors.setText("Empty input");
            }
        });


    }


    public void lang() {
        signup_button.setText("Sign Up");
        go_to_login_button.setText("Log in");
        singup_text.setText("Register");
    }

    @FXML
    private void ToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene window = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(window);
        stage.show();
    }
}
