package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMeetingContactCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations outside the AddMeetingContactCommand code.
 * For example, inputs "1" and "1 abc" take the same path through the AddMeetingContactCommand,
 * and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class AddMeetingContactCommandParserTest {

    private AddMeetingContactCommandParser parser = new AddMeetingContactCommandParser();

    @Test
    public void parse_validArgs_returnsAddMeetingContactCommand() {
        assertParseSuccess(parser, "1 1", new AddMeetingContactCommand(INDEX_FIRST_MEETING, INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingContactCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1 1 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingContactCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingContactCommand.MESSAGE_USAGE));
    }
}

