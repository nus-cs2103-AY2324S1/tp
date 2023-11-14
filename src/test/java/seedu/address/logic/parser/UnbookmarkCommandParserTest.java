package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnbookmarkCommand;

public class UnbookmarkCommandParserTest {

    private UnbookmarkCommandParser parser = new UnbookmarkCommandParser();

    @Test
    public void parse_validArgs_returnsUnbookmarkCommand() {
        assertParseSuccess(parser, "1", new UnbookmarkCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnbookmarkCommand.MESSAGE_USAGE));
    }
}
