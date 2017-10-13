package training.tls;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int targetFrequency = promptForTargetFrequency();

        IgnoreNonWord.answerStretchGoalFromDisk(targetFrequency, false);
    }

    private static int promptForTargetFrequency() {
        while (true) {
            System.out.println("What frequency of TLS would you like to look for?");
            String frequencyText = new Scanner(System.in).nextLine();

            try {
                return Integer.parseInt(frequencyText);
            } catch (NumberFormatException ignored) {
            }
        }
    }
}
