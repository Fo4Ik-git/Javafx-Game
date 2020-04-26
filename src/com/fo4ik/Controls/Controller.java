package com.fo4ik.Controls;

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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;


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
            Timer timer = new Timer();
            String login = login_input.getText().trim();
            String pswd = psw_input.getText().trim();
            ReadFiles(login);

            File file = new File("tmp.btb");
            try {
                FileWriter writer = new FileWriter("tmp.btb");
                writer.write(list.get(0) + "\n");
                writer.write(list.get(1));
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            String lg = list.get(0);
            String pwd = list.get(1);


            if (pswd.equals(pwd)) {
                try {
                    GameController gcm = new GameController();
                    gcm.ReadFiles(login);
                    ToGame(event);
                    gcm.ReadFiles(login);
                    file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                errors.setText("Incorrect password");
            }
        });
    }

    public void startReader(){
        System.out.println("Login2 " + login_input.getText());
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

    ArrayList<String> list = new ArrayList<String>();

    public void ReadFiles(String login) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(login + ".btb"));
            String str;

            while ((str = reader.readLine()) != null) {
                if (!str.isEmpty()) {
                    list.add(str);
                }
            }
        } catch (IOException e) {
            errors.setText("User is not found");
        }
    }


}
