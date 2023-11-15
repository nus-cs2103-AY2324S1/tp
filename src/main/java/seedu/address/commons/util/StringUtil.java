package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Set;

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
     * Returns true if the {@code stringToCheck} contains the {@code substring}.
     *   Ignores case, and a full word match is not required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == true // not a full word match
     *       </pre>
     * @param stringToCheck cannot be null
     * @param substring cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsSubstringIgnoreCase(String stringToCheck, String substring) {
        requireNonNull(stringToCheck);
        requireNonNull(substring);

        String preppedSubstring = substring.trim();
        checkArgument(!preppedSubstring.isEmpty(), "Substring parameter cannot be empty");


        return stringToCheck.toLowerCase().contains(preppedSubstring.toLowerCase());
    }

    /**
     * Returns true if the {@code setToCheck} contains the {@code searchString}.
     *   Ignores case, and an exact match to any member of the set is required.
     *   <br>examples:<pre>
     *      containsStringIgnoreCaseInSet(new HashSet<>(Arrays.asList("aBc", "DeF")), "aBc") == true
     *      containsStringIgnoreCaseInSet(new HashSet<>(Arrays.asList("aBc", "DeF")), "abc") == true
     *
     *      // not an exact match to a member
     *      containsStringIgnoreCaseInSet(new HashSet<>(Arrays.asList("aBc", "DeF")), "aB") == false
     *      </pre>
     *
     * @param setToCheck cannot be null
     * @return true if the {@code setToCheck} contains the {@code searchString}
     */
    public static boolean containsStringIgnoreCaseInSet(Set<String> setToCheck, String searchString) {
        requireNonNull(setToCheck);
        requireNonNull(searchString);

        String preppedSearchString = searchString.trim();
        checkArgument(!preppedSearchString.isEmpty(), "Search string parameter cannot be empty");

        return setToCheck.stream()
                .anyMatch(preppedSearchString::equalsIgnoreCase);
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
     * @throws NumberFormatException if {@code s} is not a number.
     */
    public static boolean isNonZeroUnsignedInteger(String s) throws NumberFormatException {
        requireNonNull(s);

        int value = Integer.parseInt(s);
        return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
    }
}
