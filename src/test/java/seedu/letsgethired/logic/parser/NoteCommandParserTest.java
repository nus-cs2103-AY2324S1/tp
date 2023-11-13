package seedu.letsgethired.logic.parser;

import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_NOTE_JANE_STREET;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_DELETE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_NOTE_INSERT;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.letsgethired.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.letsgethired.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.commands.NoteCommand;
import seedu.letsgethired.logic.commands.NoteDeleteCommand;
import seedu.letsgethired.logic.commands.NoteInsertCommand;
import seedu.letsgethired.model.application.Note;

public class NoteCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);
    private NoteCommandParser parser = new NoteCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        // have note
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInputInsert = targetIndex.getOneBased() + " " + PREFIX_NOTE_INSERT + VALID_NOTE_JANE_STREET;
        NoteCommand expectedInsertCommand =
                new NoteInsertCommand(INDEX_FIRST_APPLICATION, new Note(VALID_NOTE_JANE_STREET));
        assertParseSuccess(parser, userInputInsert, expectedInsertCommand);

        String userInputDelete = targetIndex.getOneBased() + " " + PREFIX_NOTE_DELETE + "1";
        NoteCommand expectedDeleteCommand =
                new NoteDeleteCommand(INDEX_FIRST_APPLICATION, Index.fromZeroBased(0));
        assertParseSuccess(parser, userInputDelete, expectedDeleteCommand);
    }

    @Test
    public void parse_missingParameter_failure() {
        // no field specified
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, Note.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, NoteCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, NoteCommand.COMMAND_WORD + " " + VALID_NOTE_JANE_STREET, expectedMessage);
    }
}
