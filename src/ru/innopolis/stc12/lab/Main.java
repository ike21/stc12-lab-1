package ru.innopolis.stc12.lab;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] sourceList = {"e://text.txt"};
        String[] wordsList = {"Алиса"};


        Reader reader = new Reader();
        reader.getOccurencies(sourceList,wordsList, "test.txt");

    }
}
