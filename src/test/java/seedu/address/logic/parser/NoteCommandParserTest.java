package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.model.person.Note;

public class NoteCommandParserTest {
    private NoteCommandParser parser = new NoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        NoteCommand expectedCommand = new NoteCommand(Index.fromZeroBased(0), new Note("Test note"));
        assertParseSuccess(parser, "1 Test note", expectedCommand);
    }

    @Test
    public void parse_missingDetails_failure() {
        // Missing note details
        assertParseFailure(parser, "1",
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));

        // Missing index
        assertParseFailure(parser, "Test note",
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
    }
}
