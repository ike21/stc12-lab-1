package ru.innopolis.stc12.lab;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] sourceList = getFileNames("e://test");
        String[] wordsList = {"starter", "smarter"};

        new OccurrenciesFinder(sourceList, wordsList, "test.txt");
    }

    public static String[] getFileNames(String pathname) {
        File folder = new File(pathname);
        File[] listOfFiles = folder.listFiles();
        List<String> results = new ArrayList<>();
        Arrays.stream(listOfFiles).forEach(file -> {
            if (file.isFile()) {
                results.add("file:///" + pathname + "//" + file.getName());
            }
        });

        return results.toArray(new String[0]);
    }
}
