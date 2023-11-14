package seedu.address.logic.parser.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.events.FilterEventClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventClientContainsKeywordsPredicate;




public class FilterEventClientCommandParserTest {

    private FilterEventClientCommandParser parser = new FilterEventClientCommandParser();

    @Test
    public void parse_validArgs_returnsFilterEventClientCommand() throws ParseException {
        String args = "keyword1 keyword2";
        FilterEventClientCommand expectedCommand = new FilterEventClientCommand(
                new EventClientContainsKeywordsPredicate(Arrays.asList(args.split("\\s+"))));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String args = "";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterEventClientCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }

    @Test
    public void parse_whitespaceArgs_throwsParseException() {
        String args = "    ";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterEventClientCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }
}

