package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static connectify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;

import org.junit.jupiter.api.Test;

import connectify.logic.commands.DeleteCompanyCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCompanyCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCompanyCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCompanyCommandParserTest {

    private DeleteCompanyCommandParser parser = new DeleteCompanyCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCompanyCommand() {
        assertParseSuccess(parser, "1", new DeleteCompanyCommand(INDEX_FIRST_COMPANY));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCompanyCommand.MESSAGE_USAGE));
    }
}
