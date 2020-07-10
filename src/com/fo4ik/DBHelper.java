package com.fo4ik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBHelper {


    public Statement statement;
    Connection connection;

    public void createDB() throws SQLException {
        File file = new File("btb.db");

        String CRAETE = "CREATE TABLE users (\n" +
                "    id       INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    login    VARCHAR (30) NOT NULL UNIQUE,\n" +
                "    password VARCHAR (30) NOT NULL,\n" +
                "    xp       VARCHAR          NOT NULL,\n" +
                "    lvl      VARCHAR          NOT NULL,\n" +
                "    money    VARCHAR          NOT NULL,\n" +
                "    lang    VARCHAR          NOT NULL,\n" +
                "    country     VARCHAR NOT NULL" +
                ")";
        statement = connection.createStatement();
        statement.execute(CRAETE);


    }
    //Создание данных таблицы в бд

    public void openDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:btb.db");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("error here opendb");
        }
    }
    //Подключение к плагину бд

    public void createUser(String log, String pswd, String xp, String lvl, String money, String lang, String country) {
        try {
            String INSERT = "INSERT INTO users (login, password, xp, lvl, money, lang, country) " +
                    "VALUES (              '" + log + "',\n" +
                    "                      '" + pswd + "',\n" +
                    "                      '" + xp + "',\n" +
                    "                      '" + lvl + "',\n" +
                    "                      '" + money + "',\n" +
                    "                      '" + lang + "',\n" +
                    "                      '" + country + "');";
            statement = connection.createStatement();
            statement.executeUpdate(INSERT);

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            System.out.println("Error here");
        }

    }
    // Создание информации пользователя в таблице

    public void delUser(int id) {
        try {
            String DELETE_USER = "DELETE FROM users" +
                    " WHERE id = " + id + ";";
            statement = connection.createStatement();
            statement.executeUpdate(DELETE_USER);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getInfoUser(String login) {
        ArrayList<String> list = new ArrayList<>();
        try {
            String GET_INFO_USER = "SELECT id,\n" +
                    " login,\n" +
                    " password,\n" +
                    " xp,\n" +
                    " lvl,\n" +
                    " money,\n" +
                    " lang,\n" +
                    " country\n" +
                    " FROM users WHERE login = '" + login + "';";

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_INFO_USER);
            while (rs.next()) {
                list.add(rs.getString("id"));
                list.add(rs.getString("login"));
                list.add(rs.getString("password"));
                list.add(rs.getString("xp"));
                list.add(rs.getString("lvl"));
                list.add(rs.getString("money"));
                list.add(rs.getString("lang"));
                list.add(rs.getString("country"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

        return list;
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String returnLogin() {
        try {
            FileReader fr = new FileReader("tmp.btb");
            Scanner sc = new Scanner(fr);
            return sc.nextLine();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void updateLogin(Integer id, String new_login) {
        try {
            String UPDATE_LOGIN =
                    "UPDATE users" +
                            " SET login = '" + new_login + "'" +
                            " WHERE id = '" + id + "';";
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_LOGIN);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePswd(Integer id, String new_pswd) {
        try {
            String UPDATE_PSWD =
                    "UPDATE users" +
                            " SET password = '" + new_pswd + "'" +
                            " WHERE id = '" + id + "';";
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE_PSWD);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String whatUpdate, String new_count, int id) {
        try {
            String UPDATE =
                    "UPDATE users" +
                            " SET " + whatUpdate + " = '" + new_count + "'" +
                            " WHERE id = '" + id + "';";
            statement = connection.createStatement();
            statement.executeUpdate(UPDATE);
        } catch (Exception e) {
            e.getMessage();
        }
    }


}
