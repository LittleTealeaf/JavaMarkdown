package test;

import org.junit.jupiter.params.provider.Arguments;
import org.tealeaf.javamarkdown.lists.BulletList;
import org.tealeaf.javamarkdown.lists.NumberedList;
import org.tealeaf.javamarkdown.markup.Bold;
import org.tealeaf.javamarkdown.markup.Code;
import org.tealeaf.javamarkdown.markup.Italic;
import org.tealeaf.javamarkdown.markup.Strikethrough;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tests {

    public static final String[] WORDS;
    public static final Random RANDOM;

    static {
        InputStream stream = Objects.requireNonNull(Tests.class.getResourceAsStream("/words.txt"));
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        List<String> words_list = reader.lines().collect(Collectors.toList());
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WORDS = words_list.toArray(new String[0]);

        RANDOM = new Random();
    }

    public static Predicate<Object> filterClasses(boolean include, Class<?>... classes) {
        return item -> {
            for (Class<?> type : classes) {
                if (type.isInstance(item)) {
                    return include;
                }
            }
            return !include;
        };
    }

    public static Stream<Object> provideObjects() {
        return Stream.of(code(), randomWord(), bold(), italic(), strikethrough(), randomSentence(), randomInteger());
    }

    public static Stream<Object> provideObjects(Predicate<Object> predicate) {
        return provideObjects().filter(predicate).map(Object.class::cast);
    }

    public static Stream<Arguments> provideArguments() {
        return provideObjects().map(Arguments::of);
    }

    public static Stream<Arguments> provideArguments(Predicate<Object> predicate) {
        return provideObjects(predicate).map(Arguments::of);
    }

    public static Bold bold() {
        return new Bold(randomSentence());
    }

    public static Italic italic() {
        return new Italic(randomSentence());
    }

    public static Strikethrough strikethrough() {
        return new Strikethrough(randomSentence());
    }

    public static Integer randomInteger() {
        return RANDOM.nextInt();
    }

    public static Integer randomInteger(int range) {
        return RANDOM.nextInt(range);
    }

    public static Code code() {
        return new Code(randomSentence());
    }

    public static String randomURL() {
        return randomSentence().replace(" ", "/");
    }

    public static String randomSentence() {
        return randomSentence(randomInteger(15, 20));
    }

    @Deprecated
    public static String randomSentence(int min, int max) {
        return randomSentence(randomInteger(min, max));
    }

    public static String randomSentence(int count) {
        return randomWordsStream(count).collect(Collectors.joining(" "));
    }

    public static String[] randomWords() {
        return randomWords(randomInteger(1, 10));
    }

    public static String[] randomWords(int count) {
        return randomWordsStream(count).toArray(String[]::new);
    }

    public static Stream<String> randomWordsStream() {
        return randomWordsStream(randomInteger(1, 10));
    }

    public static Stream<String> randomWordsStream(int count) {
        return Stream.generate(Tests::randomWord).limit(count);
    }

    public static Integer randomInteger(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }

    public static String randomWord() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    public static String randomURL(String extension) {
        return String.format("%s.%s", randomSentence().replace(" ", "/"), extension);
    }
}
