package seedu.address.logic.parser.events;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.DateTimeParser.parseDateTimeInstance;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.events.FilterEventTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventTimeBeforePredicate;


public class FilterEventTimeCommandParserTest {

    private FilterEventTimeCommandParser parser = new FilterEventTimeCommandParser();

    @Test
    public void parse_validArgs_returnsFilterEventTimeCommand() throws ParseException {
        String args = " 01-01-2023";
        String hourMinArgs = args.trim() + " 00:00";
        LocalDateTime dateTime = parseDateTimeInstance(hourMinArgs);
        FilterEventTimeCommand expectedCommand = new FilterEventTimeCommand(new EventTimeBeforePredicate(dateTime));
        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        String args = "";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterEventTimeCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }

    @Test
    public void parse_whitespaceArgs_throwsParseException() {
        String args = "    ";
        String expectedErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterEventTimeCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, () -> parser.parse(args), expectedErrorMessage);
    }
}

