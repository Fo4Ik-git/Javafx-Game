package com.fo4ik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBHelper {


    Connection connection;
    public Statement statement;

    public void createDB() throws SQLException {
        File file = new File("btb.db");

        String CRAETE = "CREATE TABLE users (\n" +
                "    id       INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    login    VARCHAR (30) NOT NULL,\n" +
                "    password VARCHAR (30) NOT NULL,\n" +
                "    xp       INT          NOT NULL,\n" +
                "    lvl      INT          NOT NULL,\n" +
                "    money    INT          NOT NULL\n" +
                ")";
        statement = connection.createStatement();
        statement.execute(CRAETE);


    }

    public void openDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:btb.db");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUser(String log, String pswd, int xp, int lvl, int money) {
        try {
            String INSERT = "INSERT INTO users (login, password, xp, lvl, money) " +
                    "VALUES ('" + log + "', '" + pswd + "', '" + xp + "', '" + lvl + "', '" + money + "');";
            statement = connection.createStatement();
            statement.executeUpdate(INSERT);

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }

    }

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
                    "       login,\n" +
                    "       password,\n" +
                    "       xp,\n" +
                    "       lvl,\n" +
                    "       money\n" +
                    "  FROM users WHERE login = '" + login + "';";

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_INFO_USER);
            while (rs.next()) {
                list.add(rs.getString("id"));
                list.add(rs.getString("login"));
                list.add(rs.getString("password"));
                int xp = rs.getInt("xp");
                int lvl = rs.getInt("lvl");
                int money = rs.getInt("money");
                list.add(Integer.toString(xp));
                list.add(Integer.toString(lvl));
                list.add(Integer.toString(money));
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

    public void update(String whatUpdate, int new_count, int id) {
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
