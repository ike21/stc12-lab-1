package ru.innopolis.stc12.lab;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsSearcher {
    final static Logger LOGGER = Logger.getLogger(WordsSearcher.class);
    private Pattern pattern;

    WordsSearcher() {
        this.pattern = Pattern.compile("[.!?]");
    }
    public String getSentence(String str, String[] words) {
        StringBuilder sentenceList = new StringBuilder();
        StringBuilder buffer = new StringBuilder();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new URL(str).openStream(), StandardCharsets.UTF_8))) {
            while (bf.ready()) {
                int ch = bf.read();
                buffer.append((char) ch);
                if (isSentenceEnd((char) ch)) {
                    Arrays.stream(words)
                            .filter(Objects::nonNull)
                            .filter(word -> buffer.toString().contains(word))
                            .forEach(x -> sentenceList.append(buffer.toString().trim()).append("\n"));
                    buffer.delete(0, buffer.length());
                }
            }
        } catch (IOException e) {
            LOGGER.error("Exception: " + e);
        }
        return sentenceList.toString();
    }

    private boolean isSentenceEnd(char ch) {
        Matcher m = pattern.matcher(String.valueOf(ch));
        return m.find();
    }
}