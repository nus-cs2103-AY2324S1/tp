package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CliSyntax.PREFIX_NOTE;
import static connectify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static connectify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.commands.PersonNoteCommand;
import connectify.model.person.PersonNote;

public class PersonNoteCommandParserTest {
    private PersonNoteCommandParser parser = new PersonNoteCommandParser();
    private final String nonEmptyNote = "Some note.";

    @Test
    public void parse_indexSpecified_success() {
        // have note
        Index targetPersonIndex = INDEX_FIRST_PERSON;
        Index targetCompanyIndex = INDEX_FIRST_COMPANY;
        String userInput = targetCompanyIndex.getOneBased() + " "
                + targetPersonIndex.getOneBased() + " "
                + PREFIX_NOTE + nonEmptyNote;
        PersonNoteCommand expectedCommand = new PersonNoteCommand(INDEX_FIRST_COMPANY,
                INDEX_FIRST_PERSON, new PersonNote(nonEmptyNote));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no note
        userInput = targetCompanyIndex.getOneBased() + " "
                + targetPersonIndex.getOneBased() + " "
                + PREFIX_NOTE;
        expectedCommand = new PersonNoteCommand(INDEX_FIRST_COMPANY,
                INDEX_FIRST_PERSON, new PersonNote(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PersonNoteCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, PersonNoteCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, PersonNoteCommand.COMMAND_WORD + " " + nonEmptyNote, expectedMessage);
    }
}
