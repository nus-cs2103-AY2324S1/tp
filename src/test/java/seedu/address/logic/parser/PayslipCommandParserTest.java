package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PayslipCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class PayslipCommandParserTest {

    private PayslipCommandParser parser = new PayslipCommandParser();

    @Test
    public void parse() throws Exception {
        // null -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> parser.parse(null));

        // invalid input -> throws ParseException
        assertThrows(ParseException.class, () -> parser.parse("a")); // non-numeric
        assertThrows(ParseException.class, () -> parser.parse("1.1")); // non-integer
        assertThrows(ParseException.class, () -> parser.parse("-1")); // negative integer
        assertThrows(ParseException.class, () -> parser.parse("0")); // zero
        assertThrows(ParseException.class, () -> parser.parse("1 1")); // multiple arguments
        assertThrows(ParseException.class, () -> parser.parse("1 a")); // non-numeric argument
        assertThrows(ParseException.class, () -> parser.parse("")); // empty string
        assertThrows(ParseException.class, () -> parser.parse(" ")); // whitespace

        // valid input -> returns PayslipCommand
        assertEquals(new PayslipCommand(Index.fromZeroBased(0)), parser.parse("1"));
    }
}
