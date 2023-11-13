package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.Messages.MESSAGE_TOO_MANY_INDEXES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMeetingContactCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations outside the AddMeetingContactCommand code. For example, inputs "1"
 * and "1 abc" take the same path through the AddMeetingContactCommand, and
 * therefore we test only one of them. The path variation for those two cases
 * occur inside the ParserUtil, and therefore should be covered by the
 * ParserUtilTest.
 */
public class AddMeetingContactCommandParserTest {

    private AddMeetingContactCommandParser parser = new AddMeetingContactCommandParser();

    @Test
    public void parse_validArgs_returnsAddMeetingContactCommand() {
        assertParseSuccess(parser, "1 1", new AddMeetingContactCommand(INDEX_FIRST_MEETING, INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid args
        assertParseFailure(parser, "a 1", MESSAGE_INVALID_INDEX_FORMAT + "\n" + AddMeetingContactCommand.MESSAGE_USAGE);

        // too many args
        assertParseFailure(parser, "1 1 2", MESSAGE_TOO_MANY_INDEXES + "\n" + AddMeetingContactCommand.MESSAGE_USAGE);

        // too few args
        assertParseFailure(parser, "2", MESSAGE_MISSING_INDEX + "\n" + AddMeetingContactCommand.MESSAGE_USAGE);
    }
}
