package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class ClearCommandParserTest {
    private final ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_validArgs_returnsClearCommand() throws ParseException {
        String validArgs = "confirm";
        ClearCommand expectedClearCommand = new ClearCommand(validArgs);
        ClearCommand clearCommand = parser.parse(validArgs);
        assertEquals(expectedClearCommand, clearCommand);
    }

    @Test
    public void parse_emptyArgs_returnsClearCommandWithFirstPrompt() throws ParseException {
        String emptyArgs = "";
        ClearCommand expectedClearCommand = new ClearCommand("reset prompt");
        ClearCommand clearCommand = parser.parse(emptyArgs);
        assertEquals(expectedClearCommand, clearCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String invalidArgs = "invalid confirmation";
        String expectedErrorMessage = String.format(ClearCommand.MESSAGE_USAGE);
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse(invalidArgs));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
