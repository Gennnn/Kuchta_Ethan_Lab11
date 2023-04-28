package me.ethan.lister;

import java.io.File;
import java.nio.file.Path;

public class DirectoryListing {

    public static void listFilesAndDirectories(File directory) {
        if (directory.isDirectory()) {
            ListerFrame.displayText.setText(ListerFrame.displayText.getText() + "Directory: " + directory.getAbsolutePath() + "\n");
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        listFilesAndDirectories(file);
                    } else {
                        ListerFrame.displayText.setText(ListerFrame.displayText.getText() + "File: " + file.getAbsolutePath() + "\n");
                    }
                }
            }
        } else {
            ListerFrame.displayText.setText(ListerFrame.displayText.getText() + "File " + directory.getAbsolutePath() + " is not a directory.\n");
        }
    }


}
