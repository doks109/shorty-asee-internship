package enterNewLinks.randomGenerator;

public class RandomGenerator {
    public static String getAlphaNumericString() {
        String newString = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            int index = (int)(newString.length() * Math.random());

            sb.append(newString.charAt(index));
        }
        return sb.toString();
    }
}