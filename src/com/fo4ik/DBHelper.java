package com.fo4ik;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBHelper {


    Connection connection;

    public void openDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:src/com/fo4ik/db/btb.db");
            //connection = DriverManager.getConnection(
            //         "jdbc:sqlite:btb.db");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUser(String log, String pswd, int xp, int lvl, int money) {
        try {
            String INSERT = "INSERT INTO users (login, password, xp, lvl, money) " +
                    "VALUES ('" + log + "', '" + pswd + "', '" + xp + "', '" + lvl + "', '" + money + "');";
            Statement statment = connection.createStatement();
            statment.executeUpdate(INSERT);

        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }

    }

    public void delUser(String login) {
        try {
            String DELETE_USER = "DELETE FROM users" +
                    "WHERE login = '" + login + "';";
            Statement statment = connection.createStatement();
            statment.executeUpdate(DELETE_USER);

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

            Statement statment = connection.createStatement();
            ResultSet rs = statment.executeQuery(GET_INFO_USER);
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
            Statement statment = connection.createStatement();
            statment.executeUpdate(UPDATE_LOGIN);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
