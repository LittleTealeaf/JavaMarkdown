package test;

import org.junit.jupiter.params.provider.Arguments;
import org.tealeaf.javamarkdown.IllegalContentsException;
import org.tealeaf.javamarkdown.lists.BulletList;
import org.tealeaf.javamarkdown.markup.Bold;
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

    public static String randomWord() {
        return WORDS[RANDOM.nextInt(WORDS.length)];
    }

    public static String randomSentence(int count) {
        return Stream.generate(Tests::randomWord).limit(count).collect(Collectors.joining(" "));
    }

    public static String randomSentence(int min, int max) {
        return randomSentence(randomRange(min,max));
    }

    public static String randomSentence() {
        return randomSentence(15,20);
    }

    public static int randomRange(int low, int high) {
        return low + RANDOM.nextInt(high - low);
    }

    public static Predicate<Object> filterClasses(boolean include, Class<?>... classes) {
        return item -> {
            for(Class<?> type : classes) {
                if(type.isInstance(item)) {
                    return include;
                }
            }
            return !include;
        };
    }


    public static Stream<Object> provideObjects(Predicate<Object> predicate) throws IllegalContentsException {
        return Stream.of(randomWord(),bold(), italic(), strikethrough(), bulletList()).filter(predicate).map(Object.class::cast);
    }

    public static Stream<Arguments> provideArguments(Predicate<Object> predicate) throws IllegalContentsException {
        return provideObjects(predicate).map(Arguments::of);
    }

    public static Bold bold() throws IllegalContentsException {
        return new Bold(randomSentence(1,10));
    }

    public static Italic italic() throws IllegalContentsException {
        return new Italic(randomSentence(1,10));
    }

    public static Strikethrough strikethrough() throws IllegalContentsException {
        return new Strikethrough(randomSentence(1,10));
    }

    public static BulletList bulletList() throws IllegalContentsException {
        return (BulletList) new BulletList().addItem(randomSentence(1,10)).addItem(randomSentence(1,10));
    }
}
