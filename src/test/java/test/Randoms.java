package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Randoms {
    private static final String[] words;
    public static final Random RANDOM;

    static {
        RANDOM = new Random();

        InputStream stream = Objects.requireNonNull(Randoms.class.getResourceAsStream("/words.txt"));
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        List<String> names_list = reader.lines().collect(Collectors.toList());
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        words = names_list.toArray(new String[0]);
    }

    public static String randomWord() {
        return words[RANDOM.nextInt(words.length)];
    }
}
