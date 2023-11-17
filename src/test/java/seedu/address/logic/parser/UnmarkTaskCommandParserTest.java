package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnmarkTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnmarkTaskCommandParserTest {

    private final UnmarkTaskCommandParser parser = new UnmarkTaskCommandParser();

    @Test
    public void parse_validArgs_returnsMarkTaskCommand() throws ParseException {
        // Integers only
        UnmarkTaskCommand expectedUnmarkTaskCommand = new UnmarkTaskCommand(
                Index.fromOneBased(1),
                Index.fromOneBased(1), Index.fromOneBased(2));
        assertTrue(parser.parse("1 1 2").equals(expectedUnmarkTaskCommand));

        // Integers with leading and trailing spaces
        assertTrue(parser.parse("  1 1 2  ").equals(expectedUnmarkTaskCommand));
        assertTrue(parser.parse("  1  1  2  ").equals(expectedUnmarkTaskCommand));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Animal index is not a number
        assertThrows(ParseException.class, () -> parser.parse("a 1 2"));

        // Task index is not a number
        assertThrows(ParseException.class, () -> parser.parse("1 a 2"));

        // Animal index contains non-numeric characters
        assertThrows(ParseException.class, () -> parser.parse("1a 1 2"));
        assertThrows(ParseException.class, () -> parser.parse("1.0 1 2"));

        // Task index contains non-numeric characters
        assertThrows(ParseException.class, () -> parser.parse("1 1a 2"));
        assertThrows(ParseException.class, () -> parser.parse("1 1.0 2"));
    }

    @Test
    public void parse_negativeIndex_throwsParseException() {
        // Animal index is negative
        assertThrows(ParseException.class, () -> parser.parse("-1 1 2"));

        // Task index is negative
        assertThrows(ParseException.class, () -> parser.parse("1 -1 2"));
    }

    @Test
    public void parse_zeroIndex_throwsParseException() {
        // Animal index is zero
        assertThrows(ParseException.class, () -> parser.parse("0 1 2"));

        // Task index is zero
        assertThrows(ParseException.class, () -> parser.parse("1 0 2"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        // Empty string
        assertThrows(ParseException.class, () -> parser.parse(""));

        // Only Animal index
        assertThrows(ParseException.class, () -> parser.parse("1"));
    }
}
