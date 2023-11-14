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
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
    }

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, accepts a partial word match.
     *   <br>examples:<pre>
     *       containsIgnoreCase("ABc def", "abc") == true
     *       containsIgnoreCase("ABc def", "DEF") == true
     *       containsIgnoreCase("ABc def", "AB") == true
     *       containsIgnoreCase("ABc def", "abcd") == false
     *       </pre>
     *
     * @param sentence cannot be null
     * @param str cannot be null, cannot be empty, must be a single string
     */
    public static boolean containsIgnoreCase(String sentence, String str) {
        requireNonNull(sentence);
        requireNonNull(str);

        String preppedStr = str.trim();
        checkArgument(!preppedStr.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedStr.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(word -> word.toLowerCase().contains(preppedStr.toLowerCase()));
    }

    /**
     * Returns true if the {@code list} contains the {@code tut}.
     *   A full tutorial match is required.
     *
     * @param list cannot be null
     * @param tut cannot be null, cannot be empty, must be a single number
     */
    public static boolean containsTutorial(String list, String tut) {
        requireNonNull(list);
        requireNonNull(tut);

        String preppedTut = tut.trim();
        checkArgument(!preppedTut.isEmpty(), "Tutorial parameter cannot be empty");
        checkArgument(preppedTut.split("\\s+").length == 1, "Tutorial parameter should be a single number");

        String preppedList = list;
        String[] tutsInPreppedList = preppedList.split("\\s+");

        return Arrays.stream(tutsInPreppedList)
                .anyMatch(preppedTut::contentEquals);
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
}
