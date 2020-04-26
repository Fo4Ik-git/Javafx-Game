package com.fo4ik;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Files {

    public static void WriteFile(String login, String password) {
        try {
            File file = new File(login + ".btb");
            FileWriter myWriter = new FileWriter(login + ".btb");
            myWriter.write(login + "\n");
            myWriter.write(password + "\n");
            myWriter.write(10 + "\n");
            myWriter.write(0 + "\n");
            myWriter.close();

        } catch (IOException e) {

        }
    }


}
