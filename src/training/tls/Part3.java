package training.tls;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Comparator.reverseOrder;

public class Part3 {

    public static void answerPart3(int targetFrequency) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("SampleText.txt")));

        Pattern pattern = Pattern.compile("\\w(?=(\\w\\w))", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        Map<String, Integer> frequenciesOfSequences = new HashMap<>();

        while (matcher.find()) {
            String tls = (matcher.group() + matcher.group(1)).toLowerCase();
            frequenciesOfSequences.putIfAbsent(tls, 0);
            frequenciesOfSequences.compute(tls, (key, count) -> count + 1);
        }

        System.out.println(String.format("There are %d instances of 'tra' in the text, according to my dictionary", frequenciesOfSequences.get("tra")));
        System.out.println();

        frequenciesOfSequences.entrySet().stream().filter(entry -> entry.getValue() == targetFrequency).forEach(entry ->
                System.out.println(String.format("There are %d instances of %s", targetFrequency, entry.getKey()))
        );
        System.out.println();

        System.out.println("Most frequent TLSs:");

        frequenciesOfSequences.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue, reverseOrder())).limit(10).forEach(entry ->
                System.out.println(String.format("TLS: %s, Frequency: %d", entry.getKey(), entry.getValue()))
        );
    }
}
