package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }


    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsPhraseIgnoreCase("typical sentence", null));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Phrase parameter cannot be empty", ()
            -> StringUtil.containsPhraseIgnoreCase("typical sentence", "  "));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.containsPhraseIgnoreCase(null, "abc"));
    }

    /*
     * Valid equivalence partitions for word/phrase:
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
        assertFalse(StringUtil.containsPhraseIgnoreCase("", "abc")); // Boundary case
        assertFalse(StringUtil.containsPhraseIgnoreCase("    ", "123"));

        // Matches a partial word only
        assertFalse(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc", "bb")); // Sentence word bigger than query word
        assertFalse(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc", "bbbb")); // Query word bigger than sentence word

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bBb ccc", "Bbb")); // First word (boundary case)
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bBb ccc@1", "CCc@1")); // Last word (boundary case)
        assertTrue(StringUtil.containsPhraseIgnoreCase("  AAA   bBb   ccc  ", "aaa")); // Sentence has extra spaces
        assertTrue(StringUtil.containsPhraseIgnoreCase("Aaa", "aaa")); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc", "  ccc  ")); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.containsPhraseIgnoreCase("AAA bBb ccc  bbb", "bbB"));

        //Matches partial phrase in sentence
        assertFalse(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc bb",
                "bbb cc")); //Sentence phrase bigger then query phrase
        assertFalse(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc bb",
                "cccc bb")); //Query phrase bigger then sentence phrase
        assertFalse(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc bb",
                "bb ccc")); //Query phrase is part of sentence phrase
        assertFalse(StringUtil.containsPhraseIgnoreCase("bb",
                "bb cc")); //Sentence phrase is shorter than query phrase

        // Matches phrase in sentence
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bbb! ccc bb", "aAa BBB!")); //First phrase
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bbb c1c bb", "C1C bB")); //Last phrase
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa    bbb    ccc  bb",
                "aaa bbb ccc bb")); //Sentence has extra spaces
        assertTrue(StringUtil.containsPhraseIgnoreCase(
                "aaa bbb ccc bb", "aaa bbb ccc bb")); //Only one phrase in sentence
        assertTrue(StringUtil.containsPhraseIgnoreCase("aaa bbb ccc bb", "   bb  ")); //Leading/trailing spaces

        //Matches multiple phrases in sentence
        assertTrue(StringUtil.containsPhraseIgnoreCase("AAA bbb ccc  bbb   CCC   ", "bbB cCc"));

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

    @Test
    public void toTitleCase_standardString_titleCased() {
        String input = "hello world";
        String expected = "Hello World";
        assertEquals(expected, StringUtil.toTitleCase(input));
    }

    @Test
    public void toTitleCase_emptyString_emptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, StringUtil.toTitleCase(input));
    }

    @Test
    public void toTitleCase_singleCharacter_titleCased() {
        String input = "a";
        String expected = "A";
        assertEquals(expected, StringUtil.toTitleCase(input));
    }

    @Test
    public void toTitleCase_stringWithPunctuation_titleCased() {
        String input = "it's a wonderful world!";
        String expected = "It's A Wonderful World!";
        assertEquals(expected, StringUtil.toTitleCase(input));
    }

    @Test
    public void toTitleCase_nullInput_nullOutput() {
        assertNull(StringUtil.toTitleCase(null));
    }

    @Test
    public void toTitleCase_allCaps_normalTitleCase() {
        String input = "HELLO WORLD";
        String expected = "Hello World";
        assertEquals(expected, StringUtil.toTitleCase(input));
    }

    @Test
    public void toTitleCase_mixedCase_correctedTitleCase() {
        String input = "hELLo WoRLD";
        String expected = "Hello World";
        assertEquals(expected, StringUtil.toTitleCase(input));
    }
}
