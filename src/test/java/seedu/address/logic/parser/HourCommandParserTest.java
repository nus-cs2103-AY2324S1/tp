package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HourCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HourCommandParserTest {

    private HourCommandParser parser = new HourCommandParser();

    @Test
    public void parse_validDuration_returnsHourCommand() throws ParseException {
        String input = "10";
        HourCommand expectedCommand = new HourCommand(10);
        HourCommand parsedCommand = parser.parse(input);
        assertEquals(expectedCommand, parsedCommand);
    }

    @Test
    public void parse_invalidDuration_throwsParseException() {
        // Test with non-integer input
        String stringInput = "abc";
        assertThrows(ParseException.class, () -> parser.parse(stringInput));
    }
}
