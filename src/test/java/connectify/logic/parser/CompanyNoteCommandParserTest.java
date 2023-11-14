package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CliSyntax.PREFIX_NOTE;
import static connectify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static connectify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.commands.CompanyNoteCommand;
import connectify.model.company.CompanyNote;

public class CompanyNoteCommandParserTest {
    private CompanyNoteCommandParser parser = new CompanyNoteCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        // have note
        Index targetIndex = INDEX_FIRST_COMPANY;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE + "Some note.";
        CompanyNoteCommand expectedCommand = new CompanyNoteCommand(INDEX_FIRST_COMPANY, new CompanyNote("Some note."));
        assertParseSuccess(parser, userInput, expectedCommand);

        // no note
        userInput = targetIndex.getOneBased() + " " + PREFIX_NOTE;
        expectedCommand = new CompanyNoteCommand(INDEX_FIRST_COMPANY, new CompanyNote(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompanyNoteCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, CompanyNoteCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, CompanyNoteCommand.COMMAND_WORD + " Some note.", expectedMessage);
    }
}
