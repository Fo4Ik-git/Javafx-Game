package com.fo4ik.Controls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GameController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label xp_text;

    @FXML
    private Label lvl_text;

    @FXML
    private Label instruction;

    @FXML
    private Button play_btn;

    @FXML
    private Button settings_btn;

    @FXML
    private Button back_btn;

    @FXML
    private Button update;

    @FXML
    private AnchorPane instruction_bg;

    @FXML
    private AnchorPane game_scene;


    public String login;
    public String pswd;
    public int xp;
    public int lvl;

    @FXML
    void initialize() {
        Controller controller = new Controller();
        game_scene.setVisible(false);
        back_btn.setVisible(false);
        System.out.println(login + " Login");

        WriteFileGame(login, pswd);
        try {
            ReadFiles(login);
        } catch (IOException e) {
            e.printStackTrace();
        }

        xp_text.setText(list.get(2));
        lvl_text.setText(list.get(3));

        update.setOnAction(event -> {
            controller.startReader();

        });

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

    }


    public void WriteFileGame(String login, String pswd) {
        try {

            FileWriter myWriter = new FileWriter(login + ".btb");
            myWriter.write(login + "\n"); //login
            myWriter.write(pswd + "\n"); //login
            myWriter.write(xp + "\n"); //xp
            myWriter.write(lvl + "\n"); //lvl
            myWriter.close();

        } catch (IOException e) {

        }
    }

    ArrayList<String> list = new ArrayList<String>();

    public void ReadFiles(String file_name) throws IOException {
        FileReader fr = new FileReader(file_name + ".btb");
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) {
            list.add(scan.nextLine());
        }
        login = list.get(0);
        pswd = list.get(1);
        xp = Integer.parseInt(list.get(2));
        lvl = Integer.parseInt(list.get(3));

    }

    @FXML
    private void ToSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        Scene window = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(window);
        stage.show();
    }

    /*
    try {
            ArrayList<String> tmp = new ArrayList<>();
            FileReader fr = new FileReader("tmp.btb");
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                tmp.add(scan.nextLine());
            }
            login = tmp.get(0);
            pswd = tmp.get(1);

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
     */
}