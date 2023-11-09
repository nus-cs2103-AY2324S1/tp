package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;


public class ViewCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        String userInput = " 1";
        ViewCommand expectedCommand = new ViewCommand(INDEX_FIRST_PERSON);
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

    }

}
