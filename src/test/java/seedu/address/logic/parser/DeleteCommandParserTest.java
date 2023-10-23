package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseBasicFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseBasicSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;



/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validSingleIndexArg_returnsDeleteCommand() {
        assertParseBasicSuccess(parser, "1", new DeleteCommand(List.of(INDEX_FIRST_PERSON)));
    }

    @Test
    public void parse_validMultiIndexArg_returnsDeleteCommand() {
        List<Index> indexList = Arrays.asList(Index.fromOneBased(1),
                Index.fromOneBased(2), Index.fromOneBased(3));
        assertParseBasicSuccess(parser, "1 2 3", new DeleteCommand(indexList));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseBasicFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multiArgSingleInvalid_throwsParseException() {
        assertParseBasicFailure(parser, "1 2 a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE));
    }
}
