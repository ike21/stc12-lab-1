package ru.innopolis.stc12.lab;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.*;

public class OccurrenciesFinder implements Occurrencies {
    final static Logger LOGGER = Logger.getLogger(OccurrenciesFinder.class);
    private List<Future<String>> future = new ArrayList<>();

    OccurrenciesFinder(String[] sources, String[] words, String res) {
        getOccurencies(sources, words, res);
    }

    @Override
    public void getOccurencies(String[] sources, String[] words, String res) {
        LOGGER.info("Start find occurrences");
        long beginTime = System.currentTimeMillis();
        WordsSearcher wordsSearcher = new WordsSearcher();
        OccurrenciesWriter writer = new OccurrenciesWriter();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (String str : sources) {
            if (str != null) {
                future.add(CompletableFuture.supplyAsync(() -> wordsSearcher.getSentence(str, words), threadPool));
            }
        }
        writer.write(future, res);
        threadPool.shutdown();
        long endTime = System.currentTimeMillis() - beginTime;
        LOGGER.info("Spent time: " + endTime + "ms");
    }
}