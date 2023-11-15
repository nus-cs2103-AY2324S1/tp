package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.model.person.Note;

public class AddNoteCommandParserTest {
    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        AddNoteCommand expectedCommand = new AddNoteCommand(Index.fromZeroBased(0), new Note("Test note"));
        assertParseSuccess(parser, "1 Test note", expectedCommand);
    }

    @Test
    public void parse_missingDetails_failure() {
        // No arguments
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));

        // Missing note details
        assertParseFailure(parser, "1", AddNoteCommandParser.MESSAGE_EMPTY_NOTE);

        // Missing index
        assertParseFailure(parser, "Test note", ParserUtil.MESSAGE_NOT_A_INDEX);
    }
}
