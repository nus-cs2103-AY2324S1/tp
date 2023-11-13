package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteEventCommand;

class DeleteEventCommandParserTest {
    private static final DeleteEventCommandParser parser = new DeleteEventCommandParser();
    private static final String sampleString = "2024-01-01 12:00";

    @Test
    public void parse_success() {
        LocalDateTime expectedDateTime = LocalDateTime.parse(sampleString, DATE_TIME_STRING_FORMATTER);
        String validUserInput = " " + sampleString;
        assertParseSuccess(parser, validUserInput, new DeleteEventCommand(expectedDateTime));
    }

    @Test
    public void parse_invalidValue_failure() {
        String invalidUserInput = " 123";
        assertParseFailure(parser, invalidUserInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE));
    }
}
