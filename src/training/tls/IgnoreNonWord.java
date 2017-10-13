package training.tls;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Comparator.reverseOrder;

public class IgnoreNonWord {

    public static void answerStretchGoalFromDisk(int targetFrequency, boolean ignoreNonWordCharacters) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("SampleText.txt")));
        answerStretchGoal(input, targetFrequency, ignoreNonWordCharacters);
    }

    public static void answerStretchGoalFromTheInternet(int targetFrequency, boolean ignoreNonWordCharacters) throws IOException {
        try (InputStream stream = new URL("https://en.wikipedia.org/wiki/Three-letter_acronym").openStream()) {
            String input = new Scanner(stream).useDelimiter("\\A").next();
            answerStretchGoal(input, targetFrequency, ignoreNonWordCharacters);
        }
    }

    private static void answerStretchGoal(String input, int targetFrequency, boolean ignoreNonWordCharacters) {
        Map<String, Integer> dictionary = buildTlsMap(input, ignoreNonWordCharacters);

        printFrequencyOfTra(dictionary);
        printTLSsOfFrequency(targetFrequency, dictionary);
        PrintMostFrequentTLSs(dictionary);
    }

    private static Map<String, Integer> buildTlsMap(String input, boolean ignoreNonWordCharacters) {
        Map<String, Integer> map = new HashMap<>();
        Pattern pattern = Pattern.compile(ignoreNonWordCharacters
                ? "\\w(?=\\W*(\\w)\\W*(\\w))"
                : "\\w(?=(\\w)(\\w))");

        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String tls = (matcher.group(0) + matcher.group(1) + matcher.group(2)).toLowerCase();
            addOrIncrement(map, tls);
        }

        return map;
    }

    private static <T> void addOrIncrement(Map<T, Integer> map, T key) {
        map.putIfAbsent(key, 0);
        map.computeIfPresent(key, (k, value) -> value + 1);
    }

    private static void printFrequencyOfTra(Map<String, Integer> map) {
        System.out.println(String.format("There are %d instances of 'tra' in the text, according to my dictionary", map.get("tra")));
    }

    private static void printTLSsOfFrequency(int targetFrequency, Map<String, Integer> map) {
        System.out.println();

        map.entrySet().stream().filter(entry -> entry.getValue() == targetFrequency).forEach(entry ->
                System.out.println(String.format("There are %d instances of %s", targetFrequency, entry.getKey()))
        );
    }

    private static void PrintMostFrequentTLSs(Map<String, Integer> map) {
        System.out.println();
        System.out.println("Most frequent TLSs:");

        map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, reverseOrder())).limit(10).forEach(entry ->
                System.out.println(String.format("TLS: %s, Frequency: %d", entry.getKey(), entry.getValue()))
        );
    }
}
