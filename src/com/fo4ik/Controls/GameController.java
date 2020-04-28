package com.fo4ik.Controls;

import com.fo4ik.DBHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameController {


    @FXML
    private AnchorPane game_scene;

    @FXML
    private Label xp_text;

    @FXML
    private Label lvl_text;

    @FXML
    private Button play_btn;

    @FXML
    private Button settings_btn;

    @FXML
    private Button back_btn;

    @FXML
    private AnchorPane instruction_bg;

    @FXML
    private Label instruction;

    @FXML
    private Button update;

    @FXML
    private Label name_text;

    @FXML
    private Label money_text;


    public String login;
    public String pswd;
    public int xp;
    public int lvl;

    @FXML
    void initialize() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.openDB();
        login = dbHelper.returnLogin();
        ArrayList<String> list = dbHelper.getInfoUser(login);

        //Show stats
        name_text.setText(list.get(1));
        money_text.setText(list.get(5));
        xp_text.setText(list.get(3));
        lvl_text.setText(list.get(4));

        Controller controller = new Controller();
        game_scene.setVisible(false);
        back_btn.setVisible(false);





        settings_btn.setOnAction(event -> {
            try {
                ToSettings(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        play_btn.setOnAction(event -> {
            instruction_bg.setVisible(false);
            settings_btn.setVisible(false);
            game_scene.setVisible(true);
            play_btn.setVisible(false);
            back_btn.setVisible(true);
        });

        back_btn.setOnAction(event -> {
            instruction_bg.setVisible(true);
            settings_btn.setVisible(true);
            game_scene.setVisible(false);
            play_btn.setVisible(true);
            back_btn.setVisible(false);
        });
        dbHelper.close();
    }




    @FXML
    private void ToSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        Scene window = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(window);
        stage.show();

    }


}