package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_NOTE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveNoteCommand;

public class RemoveNoteCommandParserTest {

    private RemoveNoteCommandParser parser = new RemoveNoteCommandParser();

    @Test
    public void parse_validArgs_returnsRemoveNoteCommand() {
        assertParseSuccess(parser, "1 2", new RemoveNoteCommand(INDEX_FIRST_PERSON, INDEX_SECOND_NOTE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // empty string
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveNoteCommand.MESSAGE_USAGE));

        // single number
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveNoteCommand.MESSAGE_USAGE));

        // two numbers separated by multiple spaces
        assertParseFailure(parser, "1   2", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveNoteCommand.MESSAGE_USAGE));

        // non-numeric characters
        assertParseFailure(parser, "a b", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveNoteCommand.MESSAGE_USAGE));

        // more than two numbers
        assertParseFailure(parser, "1 2 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFormat_throwsParseException() {
        // only one index
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveNoteCommand.MESSAGE_USAGE));

        // no whitespace in between
        assertParseFailure(parser, "12", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveNoteCommand.MESSAGE_USAGE));

        // non-numeric characters
        assertParseFailure(parser, "a b", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveNoteCommand.MESSAGE_USAGE));
    }
}
