package ru.innopolis.stc12.lab;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Reader implements Comparison {
    @Override
    public void getOccurencies(String[] sources, String[] words, String res) throws IOException {
        ConcurrentHashMap list = getText(sources);
        CopyOnWriteArrayList<String> list2 = read(list, words);
        write(list2,res);

    }

    private ConcurrentHashMap getText(String[] sources) throws FileNotFoundException {
//        ArrayList<String> list = new ArrayList<>();
        ConcurrentHashMap<String, String> hashMap = new ConcurrentHashMap();
        for (String str : sources) {
            File file = new File(str);
            Scanner scanner = new Scanner(file);
            List<String> strings = new ArrayList<>();
            StringBuilder fullText = new StringBuilder();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
//                list.add(line);
                fullText.append(line);
                fullText.append(" ");
            }
//            list.add(fullText.toString());
            hashMap.put(str, fullText.toString());
        }

        return hashMap;

    }

    private CopyOnWriteArrayList read(ConcurrentHashMap<String, String> list, String[] words) {
        CopyOnWriteArrayList<String> list2 = new CopyOnWriteArrayList<>();
        Pattern p = Pattern.compile("(^|(?<=[.!?]\\s))(\\d+\\.\\s?)*[А-ЯA-Z][^!?]*?[.!?](?=\\s*(\\d+\\.\\s)*[А-ЯA-Z]|$)", Pattern.MULTILINE);
        Matcher m = p.matcher(list.get("e://text.txt"));

        while (m.find()) {
            String ma = m.group();
            if (ma.contains(words[0])) {
                list2.add(ma);
            }
        }
        return list2;
    }

    private void write(CopyOnWriteArrayList<String> list,String res) throws IOException {
        File file = new File(res);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String a : list) {
            writer.write(a);
            writer.write("\n");
        }
        file.deleteOnExit();
    }
}
