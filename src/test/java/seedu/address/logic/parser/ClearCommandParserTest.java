package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

class ClearCommandParserTest {
    private final ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_validArgs_returnsClearCommand() throws ParseException {
        String validArgs = "confirmation";
        ClearCommand expectedClearCommand = new ClearCommand(validArgs);
        ClearCommand clearCommand = parser.parse(validArgs);
        assertEquals(expectedClearCommand, clearCommand);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String emptyArgs = "";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE);
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse(emptyArgs));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
