package ru.innopolis.stc12.lab;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Comparison {
    void getOccurencies(String[] sources, String[] words, String res) throws IOException;
}
