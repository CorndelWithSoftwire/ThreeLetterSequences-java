package training.tls;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Part1 {

    public static void answerPart1() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get("SampleText.txt"))).toLowerCase();

        int counter = 0;

        for (int i = 0; i < input.length() - 2; i++) {
            if (input.charAt(i) == 't' && input.charAt(i + 1) == 'r' && input.charAt(i + 2) == 'a') {
                counter++;
            }
        }

        System.out.println(String.format("There are %d instances of 'tra' in the text", counter));
    }
}
