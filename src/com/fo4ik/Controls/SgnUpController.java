package com.fo4ik.Controls;

import com.fo4ik.Files;
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
    private Button signup_button;

    @FXML
    private PasswordField pswd_input2;

    @FXML
    private Label errors;

    @FXML
    void initialize() {


        signup_button.setOnAction(event -> {
            if (!login_input.getText().equals("") || pswd_input.getText().equals("") || pswd_input2.getText().equals("")) {
                if (pswd_input.getText().equals(pswd_input2.getText())) {
                    String login = login_input.getText().trim();
                    String pswd = pswd_input.getText().trim();
                    Files.WriteFile(login,pswd);
                    try {
                        ToMain(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    errors.setText("Incorrect passwords");
                }
            } else {
                errors.setText("Empty input");
            }

        });


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
