package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_A_NOTE_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_A_PERSON_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_B_NOTE_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NOTE_B_PERSON_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_A_NOTE_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_A_PERSON_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteNoteCommand;

public class DeleteNoteCommandParserTest {
    private DeleteNoteCommandParser parser = new DeleteNoteCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NOTE_A_PERSON_ID_DESC + NOTE_A_NOTE_ID_DESC,
                new DeleteNoteCommand(Integer.parseInt(VALID_NOTE_A_PERSON_ID),
                        Integer.parseInt(VALID_NOTE_A_NOTE_ID)));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedNoteString = NOTE_B_PERSON_ID_DESC + NOTE_B_NOTE_ID_DESC;

        // multiple person ids
        assertParseFailure(parser, NOTE_A_PERSON_ID_DESC + validExpectedNoteString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PERSON_ID));

        // multiple note ids
        assertParseFailure(parser, NOTE_A_NOTE_ID_DESC + validExpectedNoteString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NOTE_ID));

        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedNoteString + NOTE_A_PERSON_ID_DESC + NOTE_A_NOTE_ID_DESC
                        + validExpectedNoteString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PERSON_ID, PREFIX_NOTE_ID));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteNoteCommand.MESSAGE_USAGE);

        // missing person id prefix
        assertParseFailure(parser, VALID_NOTE_A_PERSON_ID + NOTE_A_NOTE_ID_DESC,
                expectedMessage);

        // missing note id prefix
        assertParseFailure(parser, NOTE_A_PERSON_ID_DESC + VALID_NOTE_A_NOTE_ID,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NOTE_A_PERSON_ID + VALID_NOTE_A_NOTE_ID,
                expectedMessage);
    }
}
