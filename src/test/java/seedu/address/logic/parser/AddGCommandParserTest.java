package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddGCommand;
import seedu.address.model.person.Github;

public class AddGCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGCommand.MESSAGE_USAGE);
    private AddGCommandParser parser = new AddGCommandParser();
    private final String nonEmptyUsername = "Someusername";

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_USERNAME + nonEmptyUsername;
        AddGCommand expectedCommand = new AddGCommand(INDEX_FIRST_PERSON, new Github(nonEmptyUsername));
        assertParseSuccess(parser, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + " " + PREFIX_USERNAME;
        expectedCommand = new AddGCommand(INDEX_FIRST_PERSON, new Github(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_indexSpecified_failure() {
        // Negative numbers
        String userInput = " -5";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // Out of bounds check
        String userInput2 = " 100000000000";
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_FORMAT);

        // Blank field
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index
        userInput = PREFIX_USERNAME + nonEmptyUsername;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }



    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddGCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AddGCommand.COMMAND_WORD + " " + nonEmptyUsername, expectedMessage);
    }
}
