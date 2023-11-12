package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkedInCommand;

public class LinkedInCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkedInCommand.MESSAGE_USAGE);
    private LinkedInCommandParser parser = new LinkedInCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + "";
        LinkedInCommand expectedCommand = new LinkedInCommand(INDEX_FIRST_PERSON);
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
        userInput = "";
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }
}
