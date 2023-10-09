package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveMeetingContactCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path
 * variations
 * outside of the RemoveMeetingContactCommand code. For example, inputs "1" and
 * "1 abc" take
 * the
 * same path through the RemoveMeetingContactCommand, and therefore we test only
 * one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RemoveMeetingContactCommandParserTest {

    private RemoveMeetingContactCommandParser parser = new RemoveMeetingContactCommandParser();

    @Test
    public void parse_validArgs_returnsRemoveMeetingContactCommand() {
        assertParseSuccess(parser, "1 1", new RemoveMeetingContactCommand(INDEX_FIRST_MEETING, INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // invalid args
        assertParseFailure(parser, "a 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingContactCommand.MESSAGE_USAGE));

        // too many args
        assertParseFailure(parser, "1 1 2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingContactCommand.MESSAGE_USAGE));

        // too few args
        assertParseFailure(parser, "2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveMeetingContactCommand.MESSAGE_USAGE));
    }
}
