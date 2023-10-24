package swe.context.logic.parser;

import static swe.context.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swe.context.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static swe.context.testutil.TestData.IndexContact.FIRST_CONTACT;

import java.util.List;

import org.junit.jupiter.api.Test;

import swe.context.logic.Messages;
import swe.context.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(List.of(FIRST_CONTACT)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
            parser,
            "a",
            Messages.commandInvalidFormat(DeleteCommand.MESSAGE_USAGE)
        );
    }
}
