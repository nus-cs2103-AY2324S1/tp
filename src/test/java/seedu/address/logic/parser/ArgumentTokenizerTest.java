package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.AnimalMessages;
import seedu.address.logic.parser.exceptions.ParseException;

public class ArgumentTokenizerTest {

    private final Prefix unknownPrefix = new Prefix("--u");
    private final Prefix pSlash = new Prefix("p/");
    private final Prefix dashT = new Prefix("-t");
    private final Prefix hatQ = new Prefix("^Q");

    @Test
    public void tokenize_emptyArgsString_noValues() {
        String argsString = "  ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);

        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, pSlash);
    }

    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }

    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }

    /**
     * Asserts all the arguments in {@code argMultimap} with {@code prefix} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Prefix)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, Prefix prefix, String... expectedValues) {

        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefix).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefix).size());

        // Verify all values returned are as expected and in order
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(prefix).get(i));
        }
    }

    private void assertArgumentAbsent(ArgumentMultimap argMultimap, Prefix prefix) {
        assertFalse(argMultimap.getValue(prefix).isPresent());
    }

    @Test
    public void tokenize_noPrefixes_allTakenAsPreamble() {
        String argsString = "  some random string /t tag with leading and trailing spaces ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString);

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());

    }

    @Test
    public void tokenize_oneArgument() {
        // Preamble present
        String argsString = "  Some preamble string p/ Argument value ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        assertPreamblePresent(argMultimap, "Some preamble string");
        assertArgumentPresent(argMultimap, pSlash, "Argument value");

        // No preamble
        argsString = " p/   Argument value ";
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, pSlash, "Argument value");

    }

    @Test
    public void tokenize_multipleArguments() {
        // Only two arguments are present
        String argsString = "SomePreambleString -t dashT-Value p/pSlash value";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentPresent(argMultimap, pSlash, "pSlash value");
        assertArgumentPresent(argMultimap, dashT, "dashT-Value");
        assertArgumentAbsent(argMultimap, hatQ);

        // All three arguments are present
        argsString = "Different Preamble String ^Q111 -t dashT-Value p/pSlash value";
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        assertPreamblePresent(argMultimap, "Different Preamble String");
        assertArgumentPresent(argMultimap, pSlash, "pSlash value");
        assertArgumentPresent(argMultimap, dashT, "dashT-Value");
        assertArgumentPresent(argMultimap, hatQ, "111");

        /* Also covers: Reusing of the tokenizer multiple times */

        // Reuse tokenizer on an empty string to ensure ArgumentMultimap is correctly reset
        // (i.e. no stale values from the previous tokenizing remain)
        argsString = "";
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, pSlash);

        /* Also covers: testing for prefixes not specified as a prefix */

        // Prefixes not previously given to the tokenizer should not return any values
        argsString = unknownPrefix + "some value";
        argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        assertArgumentAbsent(argMultimap, unknownPrefix);
        assertPreamblePresent(argMultimap, argsString); // Unknown prefix is taken as part of preamble
    }

    @Test
    public void tokenize_multipleArgumentsWithRepeats() {
        // Two arguments repeated, some have empty values
        String argsString = "SomePreambleString -t dashT-Value ^Q ^Q -t another dashT value p/ pSlash value -t";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        assertPreamblePresent(argMultimap, "SomePreambleString");
        assertArgumentPresent(argMultimap, pSlash, "pSlash value");
        assertArgumentPresent(argMultimap, dashT, "dashT-Value", "another dashT value", "");
        assertArgumentPresent(argMultimap, hatQ, "", "");
    }

    @Test
    public void tokenize_multipleArgumentsJoined() {
        String argsString = "SomePreambleStringp/ pSlash joined-tjoined -t not joined^Qjoined";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);
        assertPreamblePresent(argMultimap, "SomePreambleStringp/ pSlash joined-tjoined");
        assertArgumentAbsent(argMultimap, pSlash);
        assertArgumentPresent(argMultimap, dashT, "not joined^Qjoined");
        assertArgumentAbsent(argMultimap, hatQ);
    }

    @Test
    public void verifyNoDuplicatePrefixForPSlash_withDuplicatePrefix_throwsParseException() {
        String argsString = "SomePreambleString p/pSlash p/pSlash";
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash);


        ParseException exception = assertThrows(ParseException.class, () ->
            argMultiMap.verifyNoDuplicatePrefixesFor(pSlash));

        // Store the expected error message
        String expectedErrorMessage = AnimalMessages.MESSAGE_DUPLICATE_FIELDS + "p/";

        // Check if the error message matches the expected error message
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void arePrefixesPresent_withPresentPrefixes_shouldReturnTrue() {
        String argsString = "SomePreambleString p/pSlash -t dashT ^Q hatQ";
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);

        assertTrue(ArgumentMultimap.arePrefixesPresent(argMultiMap, pSlash, dashT, hatQ));
    }

    @Test
    public void arePrefixesPresent_withMissingPrefixes_shouldReturnFalse() {
        // No pSlash, dashT prefixes.
        String argsString = "SomePreambleString ^Q hatQ";
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);

        assertFalse(ArgumentMultimap.arePrefixesPresent(argMultiMap, pSlash, dashT, hatQ));
    }

    @Test
    public void getMissingPrefixes_withNoMissingPrefixes_returnsEmptyList() {
        String argsString = "SomePreambleString p/pSlash -t dashT ^Q hatQ";
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);

        // empty list, since no missing prefixes.
        List<Prefix> expected = List.of();
        List<Prefix> actual = ArgumentMultimap.getMissingPrefixes(argMultiMap, pSlash, dashT, hatQ);

        assertEquals(expected, actual);
    }

    @Test
    public void getMissingPrefixes_withMissingPrefixes_returnsListOfMissingPrefixes() {
        // pSlash and dashT prefixes missing
        String argsString = "SomePreambleString ^Q hatQ";
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);

        // missing prefixes should only be pSlash and dashT
        List<Prefix> expected = List.of(pSlash, dashT);
        List<Prefix> actual = ArgumentMultimap.getMissingPrefixes(argMultiMap, pSlash, dashT, hatQ);

        assertEquals(expected, actual);
    }

    @Test
    public void areSomePrefixesPresent_withPresentPrefixes_returnTrue() {
        // All prefixes present
        String argsString = "SomePreambleString p/pSlash -t dashT ^Q hatQ";
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);

        assertTrue(ArgumentMultimap.areAnyPrefixesPresent(argMultiMap, pSlash, dashT, hatQ));

        // Some prefixes present
        argsString = "SomePreambleString p/pSlash ^Q hatQ";
        argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);

        assertTrue(ArgumentMultimap.areAnyPrefixesPresent(argMultiMap, pSlash, dashT, hatQ));

        // Only one prefix present
        argsString = "SomePreambleString p/pSlash";
        argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);

        assertTrue(ArgumentMultimap.areAnyPrefixesPresent(argMultiMap, pSlash, dashT, hatQ));
    }

    @Test
    public void areSomePrefixesPresent_missingPrefix_returnFalse() {
        // No prefixes present
        String argsString = "SomePreambleString";
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);

        assertFalse(ArgumentMultimap.areAnyPrefixesPresent(argMultiMap, pSlash, dashT, hatQ));

        // Only unknown prefix present
        argsString = "SomePreambleString --u";
        argMultiMap = ArgumentTokenizer.tokenize(argsString, pSlash, dashT, hatQ);

        assertFalse(ArgumentMultimap.areAnyPrefixesPresent(argMultiMap, pSlash, dashT, hatQ));
    }


    @Test
    public void equalsMethod() {
        Prefix aaa = new Prefix("aaa");

        assertEquals(aaa, aaa);
        assertEquals(aaa, new Prefix("aaa"));

        assertNotEquals(aaa, "aaa");
        assertNotEquals(aaa, new Prefix("aab"));
    }

}
