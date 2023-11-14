package seedu.address.logic.parser.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.events.FilterEventNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventNameContainsKeywordsPredicate;


public class FilterEventNameCommandParserTest {

    private FilterEventNameCommandParser parser = new FilterEventNameCommandParser();

    @Test
    public void parse_validArgs_returnsFilterEventNameCommand() throws ParseException {
        String args = "keyword1 keyword2";
        FilterEventNameCommand expectedCommand = new FilterEventNameCommand(
                new EventNameContainsKeywordsPredicate(Arrays.asList(args.split("\\s+"))));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String args = "";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterEventNameCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }

    @Test
    public void parse_whitespaceArgs_throwsParseException() {
        String args = "    ";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterEventNameCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }
}

