package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param phrase cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsPhraseIgnoreCase(String sentence, String phrase) {
        requireNonNull(sentence);
        requireNonNull(phrase);

        String preppedPhrase = phrase.trim();
        checkArgument(!preppedPhrase.isEmpty(), "Phrase parameter cannot be empty");
        String[] wordsInPreppedPhrase = preppedPhrase.split("\\s+");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        // if the word is longer than the sentence, return false
        if (wordsInPreppedPhrase.length > wordsInPreppedSentence.length) {
            return false;
        }

        // if the phrase is one word long, check if the word is in the sentence
        if (wordsInPreppedPhrase.length == 1) {
            return Arrays.stream(wordsInPreppedSentence)
                    .anyMatch(preppedPhrase::equalsIgnoreCase);
        }

        // iterate through the sentence
        for (int i = 0; i < wordsInPreppedSentence.length; i++) {
            if (wordsInPreppedSentence[i].equalsIgnoreCase(wordsInPreppedPhrase[0])) {
                for (int j = 1; j < wordsInPreppedPhrase.length; j++) {

                    // if the sentence ends before the word does, return false
                    if (i + j >= wordsInPreppedSentence.length) {
                        break;
                    }

                    // check if the subsequent words match
                    if (!wordsInPreppedSentence[i + j].equalsIgnoreCase(wordsInPreppedPhrase[j])) {
                        break;
                    }

                    // if the last word matches, return true
                    if (j == wordsInPreppedPhrase.length - 1
                            && wordsInPreppedSentence[i + j].equalsIgnoreCase(wordsInPreppedPhrase[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Converts all the words in the given string to title case, meaning the first letter of each word is
     * capitalized, and the rest are in lowercase. Words are assumed to be separated by spaces.
     *
     * @param toTitleCase the string to convert to title case
     * @return a string with each word converted to title case
     */
    public static String toTitleCase(String toTitleCase) {
        // Check for null or empty string.
        if (toTitleCase == null || toTitleCase.isEmpty()) {
            return toTitleCase;
        }

        // Split the string into words.
        String[] words = toTitleCase.split("\\s+");

        // Capitalize the first letter of each word and make the rest lowercase.
        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
            }
        }

        // Join the words back into a single string.
        return String.join(" ", words);
    }
}
