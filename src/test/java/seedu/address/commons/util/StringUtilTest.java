package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertThrows(NumberFormatException.class, () -> StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertThrows(NumberFormatException.class, () -> StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertThrows(NumberFormatException.class, () -> StringUtil.isNonZeroUnsignedInteger("a"));
        assertThrows(NumberFormatException.class, () -> StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertThrows(NumberFormatException.class, () ->
                StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertThrows(NumberFormatException.class, () ->
                StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }


    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_multipleWords_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter should be a single word", ()
            -> StringUtil.containsWordIgnoreCase("typical sentence", "aaa BBB"));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsWordIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.containsWordIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsWordIgnoreCase("    ", "123"));

        // Matches a partial word only
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsWordIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsWordIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsWordIgnoreCase("AAA bBb ccc  bbb", "bbB"));
    }

    //---------------- Tests for containsSubstringIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for substring: null, empty
     * Invalid equivalence partitions for stringToCheck: null
     * The three test cases below test one invalid input at a time.
     */

    @Test
    public void containsSubstringIgnoreCase_nullSubstring_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsSubstringIgnoreCase("typical string", null));
    }

    @Test
    public void containsSubstringIgnoreCase_emptySubstring_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Substring parameter cannot be empty", ()
            -> StringUtil.containsSubstringIgnoreCase("typical string", "  "));
    }

    @Test
    public void containsSubstringIgnoreCase_nullStringToCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsSubstringIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for substring:
     *   - any substring
     *   - substring containing symbols/numbers
     *   - substring with leading/trailing spaces
     *
     * Valid equivalence partitions for stringToCheck:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - string with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in stringToCheck
     *   - last word in stringToCheck
     *   - middle word in stringToCheck
     *   - matches multiple words
     *   - query substring matches part of a stringToCheck word
     *
     * Possible scenarios returning false:
     *   - stringToCheck word matches part of the query substring
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsSubstringIgnoreCase_validInputs_correctResult() {

        // Empty stringToCheck
        assertFalse(StringUtil.containsSubstringIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsSubstringIgnoreCase("    ", "123"));

        // Matches a partial substring
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bbb ccc",
                "bb")); // stringToCheck word bigger than query substring
        assertFalse(StringUtil.containsSubstringIgnoreCase("aaa bbb ccc",
                "bbbb")); // Query substring bigger than stringToCheck word

        // Matches substring in the stringToCheck, different upper/lower case letters
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bBb ccc",
                "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bBb ccc@1",
                "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsSubstringIgnoreCase("  AAA   bBb   ccc  ",
                "aaa")); // stringToCheck has extra spaces
        assertTrue(StringUtil.containsSubstringIgnoreCase("Aaa",
                "aaa")); // Only one word in stringToCheck (boundary case)
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bbb ccc",
                "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in stringToCheck
        assertTrue(StringUtil.containsSubstringIgnoreCase("AAA bBb ccc  bbb", "bbB"));

        // Matches part of a stringToCheck word
        assertTrue(StringUtil.containsSubstringIgnoreCase("aaa bbb ccc", "bb"));
    }

    //---------------- Tests for containsStringIgnoreCaseInSet --------------------------------------

    /*
     * Invalid equivalence partitions for keyword: null, empty
     * Invalid equivalence partitions for set: null, empty set
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsStringIgnoreCaseInSet_nullKeyword_throwsNullPointerException() {
        Set<String> set = new HashSet<>(Arrays.asList("abc", "def"));
        assertThrows(NullPointerException.class, () -> StringUtil.containsStringIgnoreCaseInSet(set, null));
    }

    @Test
    public void containsStringIgnoreCaseInSet_emptyKeyword_throwsIllegalArgumentException() {
        Set<String> set = new HashSet<>(Arrays.asList("abc", "def"));
        assertThrows(IllegalArgumentException.class, "Search string parameter cannot be empty", ()
            -> StringUtil.containsStringIgnoreCaseInSet(set, "  "));
    }

    @Test
    public void containsStringIgnoreCaseInSet_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsStringIgnoreCaseInSet(null, "abc"));
    }

    @Test
    public void containsStringIgnoreCaseInSet_emptySet_returnsFalse() {
        Set<String> set = new HashSet<>();
        assertFalse(StringUtil.containsStringIgnoreCaseInSet(set, "abc"));
    }

    /*
     * Valid equivalence partitions for keyword:
     *   - any keyword
     *   - keyword containing symbols/numbers
     *   - keyword with leading/trailing spaces
     *
     * Valid equivalence partitions for set:
     *   - set with one string
     *   - set with multiple strings
     *   - set with mixed case strings
     *
     * Possible scenarios returning true:
     *   - keyword matches a string in the set, regardless of case
     *   - keyword matches multiple strings in the set
     *
     * Possible scenarios returning false:
     *   - keyword does not match any string in the set
     *   - keyword matches part of a string in the set
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsStringIgnoreCaseInSet_validInputs_correctResult() {
        // Set with mixed case strings
        Set<String> set = new HashSet<>(Arrays.asList("aBc", "DeF"));

        // Same case: true
        assertTrue(StringUtil.containsStringIgnoreCaseInSet(set, "aBc"));

        // Different case: true
        assertTrue(StringUtil.containsStringIgnoreCaseInSet(set, "ABC"));

        // Partial match: false
        assertFalse(StringUtil.containsStringIgnoreCaseInSet(set, "a"));

        // No match: false
        assertFalse(StringUtil.containsStringIgnoreCaseInSet(set, "ghi"));

        // Multiple matches: true
        assertTrue(StringUtil.containsStringIgnoreCaseInSet(set, "def"));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

}
