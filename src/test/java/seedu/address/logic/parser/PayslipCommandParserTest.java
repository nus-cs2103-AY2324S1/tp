package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PayslipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

class PayslipCommandParserTest {

    private final PayslipCommandParser parser = new PayslipCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() throws Exception {
        // null -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid input -> throws ParseException
        assertThrows(ParseException.class, () -> parser.parse("a")); // non-numeric
        assertThrows(ParseException.class, () -> parser.parse("1.1")); // non-integer
        assertThrows(ParseException.class, () -> parser.parse("-1")); // negative integer
        assertThrows(ParseException.class, () -> parser.parse("0")); // zero
        assertThrows(ParseException.class, () -> parser.parse("1 1")); // multiple arguments
        assertThrows(ParseException.class, () -> parser.parse("1 a")); // non-numeric argument
        assertThrows(ParseException.class, () -> parser.parse("")); // empty string
        assertThrows(ParseException.class, () -> parser.parse(" ")); // whitespace

        // index and name both not present
        assertThrows(ParseException.class, () -> parser.parse(" /t 01/01/2020"));

        // invalid date format
        assertThrows(ParseException.class, () -> parser.parse(" 1 /t 01-01-2020"));
    }

    @Test
    public void parse_validArgs_returnsPayslipCommand() throws Exception {
        // valid input -> returns PayslipCommand
        assertEquals(new PayslipCommand(Index.fromZeroBased(0)), parser.parse("1"));
        assertEquals(new PayslipCommand(new NameContainsKeywordsPredicate(Collections.singletonList("Alex"))),
            parser.parse(" /n Alex"));
        assertEquals(new PayslipCommand(Index.fromZeroBased(0), ParserUtil.stringToDate("01/01/2020")),
            parser.parse(" 1 /t 01/01/2020"));
        assertEquals(new PayslipCommand(new NameContainsKeywordsPredicate(Collections.singletonList("Alex")),
            ParserUtil.stringToDate("01/01/2020")),
            parser.parse(" /n Alex /t 01/01/2020"));
    }
}
