package training.tls;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void answerPart2() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("SampleText.txt")));

        Pattern pattern = Pattern.compile("tra", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        int counter = 0;

        while (matcher.find()) {
            counter++;
        }

        System.out.println(String.format("There are %d instances of 'tra' in the text, based on the regular expression", counter));
    }

    public static void surprisinglyNotWorking() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("SampleText.txt")));

        Pattern pattern = Pattern.compile("\\w\\w\\w", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        int counter = 0;

        while (matcher.find()) {
            if (matcher.group().toLowerCase().equals("tra")) {
                counter++;
            }
        }

        System.out.println(String.format("There are %d instances of 'tra' in the text, based on my incorrect regular expression", counter));
    }

    public static void complicatedButWorking() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("SampleText.txt")));

        Pattern pattern = Pattern.compile("\\w(?=(\\w\\w))", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        int counter = 0;

        while (matcher.find()) {
            if ((matcher.group() + matcher.group(1)).toLowerCase().equals("tra")) {
                counter++;
            }
        }

        System.out.println(String.format("There are %d instances of 'tra' in the text, based on my complicated regular expression", counter));
    }
}
