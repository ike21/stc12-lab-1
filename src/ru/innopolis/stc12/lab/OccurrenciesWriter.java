package ru.innopolis.stc12.lab;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import java.util.concurrent.*;

public class OccurrenciesWriter {
    final static Logger LOGGER = Logger.getLogger(OccurrenciesWriter.class);
    public void write(List<Future<String>> future, String res) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(res))) {
            for (Future<String> e : future) {
                writer.write(e.get());
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            LOGGER.error("Exception: " + e);
        }
    }
}