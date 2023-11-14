package networkbook.logic.parser;

import static networkbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static networkbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.CommandTestUtil;
import networkbook.logic.commands.OpenEmailCommand;
import networkbook.testutil.TypicalIndexes;

public class OpenEmailCommandParserTest {
    private static final OpenEmailCommandParser PARSER = new OpenEmailCommandParser();
    private static final String MESSAGE_INVALID_FORMAT = String.format(
            Messages.MESSAGE_INVALID_COMMAND_FORMAT, OpenEmailCommand.MESSAGE_USAGE);

    @Test
    public void parse_missingPreamble_failure() {
        String expectedMessage = String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT, OpenEmailCommand.MESSAGE_USAGE);
        assertParseFailure(PARSER, "", expectedMessage);
        assertParseFailure(PARSER, CommandTestUtil.VALID_INDEX_DESC, expectedMessage);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(
                Messages.MESSAGE_INVALID_COMMAND_FORMAT, OpenEmailCommand.MESSAGE_USAGE);
        assertParseFailure(PARSER, "-1", expectedMessage);
        assertParseFailure(PARSER, "0", expectedMessage);
        assertParseFailure(PARSER, "1 what", expectedMessage);
        assertParseFailure(PARSER, "lol", expectedMessage);
    }

    @Test
    public void parse_invalidIndexOfContact_failure() {
        // negative index
        assertParseFailure(PARSER, CommandTestUtil.INVALID_INDEX_DESC_NEGATIVE
                        + CommandTestUtil.VALID_INDEX_DESC,
                MESSAGE_INVALID_FORMAT);

        // zero
        assertParseFailure(PARSER, CommandTestUtil.INVALID_INDEX_ZERO
                        + CommandTestUtil.VALID_INDEX_DESC,
                MESSAGE_INVALID_FORMAT);

        // integer overflow
        assertParseFailure(PARSER, CommandTestUtil.INVALID_INDEX_OVERFLOW,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndexOfEmail_failure() {
        // negative index
        assertParseFailure(PARSER, "1" + CommandTestUtil.INVALID_INDEX_DESC_NEGATIVE,
                MESSAGE_INVALID_FORMAT);

        // zero
        assertParseFailure(PARSER, "1" + CommandTestUtil.INVALID_INDEX_DESC_ZERO,
                MESSAGE_INVALID_FORMAT);

        // integer overflow
        assertParseFailure(PARSER, "1" + CommandTestUtil.INVALID_INDEX_DESC_OVERFLOW,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_duplicateIndexPrefix_failure() {
        String expectedMessage = Messages.getErrorMessageForDuplicatePrefixes(CliSyntax.PREFIX_INDEX);
        String userInput = String.format("1 %s %s",
                CommandTestUtil.VALID_INDEX_DESC, CommandTestUtil.VALID_INDEX_DESC);
        assertParseFailure(PARSER, userInput, expectedMessage);
    }

    @Test
    public void parse_validPreamble_success() {
        OpenEmailCommand expectedCommand = new OpenEmailCommand(
                TypicalIndexes.INDEX_FIRST_PERSON, Index.fromOneBased(1));
        assertParseSuccess(PARSER, "1", expectedCommand);
    }

    @Test
    public void parse_validPreambleAndIndex_success() {
        OpenEmailCommand expectedCommand = new OpenEmailCommand(
                TypicalIndexes.INDEX_FIRST_PERSON, Index.fromOneBased(1));
        String userInput = TypicalIndexes.INDEX_FIRST_PERSON.getOneBased() + CommandTestUtil.VALID_INDEX_DESC;
        assertParseSuccess(PARSER, userInput, expectedCommand);
    }
}
