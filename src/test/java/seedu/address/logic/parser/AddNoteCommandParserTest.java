package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_A_CONTENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_A_PERSON_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_A_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_B_CONTENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_B_PERSON_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_B_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_A_CONTENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_A_PERSON_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_A_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalNotes.NOTE_A;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddNoteCommand;
import seedu.address.model.note.Note;

public class AddNoteCommandParserTest {
    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Note expectedNote = NOTE_A;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NOTE_A_PERSON_ID_DESC + NOTE_A_TITLE_DESC
                + NOTE_A_CONTENT_DESC,
                new AddNoteCommand(Integer.parseInt(VALID_NOTE_A_PERSON_ID), expectedNote));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedNoteString = NOTE_B_PERSON_ID_DESC + NOTE_B_TITLE_DESC + NOTE_B_CONTENT_DESC;

        // multiple person ids
        assertParseFailure(parser, NOTE_A_PERSON_ID_DESC + validExpectedNoteString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PERSON_ID));

        // multiple titles
        assertParseFailure(parser, NOTE_A_TITLE_DESC + validExpectedNoteString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NOTE_TITLE));

        // multiple contents
        assertParseFailure(parser, NOTE_A_CONTENT_DESC + validExpectedNoteString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NOTE_CONTENT));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedNoteString + NOTE_A_PERSON_ID_DESC + NOTE_A_TITLE_DESC + NOTE_A_CONTENT_DESC
                        + validExpectedNoteString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PERSON_ID, PREFIX_NOTE_TITLE, PREFIX_NOTE_CONTENT));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE);

        // missing person id prefix
        assertParseFailure(parser, VALID_NOTE_A_PERSON_ID + NOTE_A_TITLE_DESC + NOTE_A_CONTENT_DESC,
                expectedMessage);

        // missing title prefix
        assertParseFailure(parser, NOTE_A_PERSON_ID_DESC + VALID_NOTE_A_TITLE + NOTE_A_CONTENT_DESC,
                expectedMessage);

        // missing content prefix
        assertParseFailure(parser, NOTE_A_PERSON_ID_DESC + NOTE_A_TITLE_DESC + VALID_NOTE_A_CONTENT,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NOTE_A_PERSON_ID + VALID_NOTE_A_TITLE + VALID_NOTE_A_CONTENT,
                expectedMessage);
    }
}
