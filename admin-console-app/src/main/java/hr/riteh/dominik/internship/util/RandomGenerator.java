package hr.riteh.dominik.internship.util;

import java.util.Random;

public class RandomGenerator {
    public static String getAlphaNumericString() {
        String newString = "0123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {
            sb.append(newString.charAt(random.nextInt(newString.length())));
        }
        return sb.toString();
    }
}
