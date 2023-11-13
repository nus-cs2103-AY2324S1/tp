package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INTEGER_OVERFLOW;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.ViewContactEventsCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewContactEventsCommand;

class ViewContactEventsCommandParserTest {
    private ViewContactEventsCommandParser parser = new ViewContactEventsCommandParser();

    @Test
    public void parser_validArgs_returnsViewContactEventsCommand() {
        assertParseSuccess(parser, "1", new ViewContactEventsCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        // int overflow
        assertParseFailure(parser, "2147483648", MESSAGE_INTEGER_OVERFLOW);
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}
