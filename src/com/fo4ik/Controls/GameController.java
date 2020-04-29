package com.fo4ik.Controls;

import com.fo4ik.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class GameController {


    public String login;
    String money = "money";
    String xp = "xp";
    String lvl = "lvl";
    ArrayList<String> list;

    ResourceBundle bundle = ResourceBundle.getBundle("Language");
    @FXML
    private Button play_btn;
    @FXML
    private Button settings_btn;
    @FXML
    private Button shop_btn_main;
    @FXML
    private Label xp_text;
    @FXML
    private Label lvl_text;
    @FXML
    private AnchorPane instruction_bg;
    @FXML
    private Label instruction;
    @FXML
    private Label name_text;
    @FXML
    private Label money_text;
    @FXML
    private AnchorPane game_scene;
    @FXML
    private ImageView enemy_image;
    @FXML
    private Label enemy_text;
    @FXML
    private Label enemy_xp;
    @FXML
    private Button back_btn;
    @FXML
    private Button fight_btn;

    @FXML
    void initialize() {
        DBHelper dbHelper = new DBHelper();
        dbHelper.openDB();
        login = dbHelper.returnLogin();
        list = dbHelper.getInfoUser(login);
        System.out.println(list.get(7));
        bundle = ResourceBundle.getBundle("Language", new Locale(list.get(6), list.get(7)));
        lang();

        //Show stats
        name_text.setText(list.get(1));
        money_text.setText(list.get(5));
        xp_text.setText(list.get(3));
        lvl_text.setText(list.get(4));

        Controller controller = new Controller();
        game_scene.setVisible(false);
        back_btn.setVisible(false);


        settings_btn.setOnAction(this::ToSettings);

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
            try {
                dbHelper.openDB();
                list = dbHelper.getInfoUser(login);
                int money_int = Integer.parseInt(list.get(5));
                if (money_int >= 10) {
                    int send_money = money_int - 10;
                    dbHelper.openDB();
                    dbHelper.update(money, Integer.toString(send_money), Integer.parseInt(list.get(0)));
                    dbHelper.close();
                } else {
                    dbHelper.openDB();
                    dbHelper.update(money, "0", Integer.parseInt(list.get(0)));
                    dbHelper.close();
                }
                initialize();
                dbHelper.close();
            } catch (Exception e) {
                e.getMessage();
            }
        });
        dbHelper.close();
    }

    public void lang() {
        play_btn.setText(bundle.getString("game_btn_play"));
        settings_btn.setText(bundle.getString("game_btn_settings"));
        shop_btn_main.setText(bundle.getString("game_shop_btn_main"));


        //settings_btn.setText(en.text("game_btn_settings"));
        //shop_btn_main.setText(en.text("game_btn_shop"));
    }


    @FXML
    private void ToSettings(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("settings.fxml"));
            Scene window = new Scene(root);
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(window);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}