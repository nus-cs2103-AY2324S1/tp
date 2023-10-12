package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LookupCommand;
import seedu.address.model.person.PersonContainsKeywordsPredicate;

public class LookupCommandParserTest {

    private LookupCommandParser parser = new LookupCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LookupCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LookupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsLookupCommand() {
        LookupCommand expectedLookupCommand =
                new LookupCommand(new PersonContainsKeywordsPredicate(null,
                        null, "alice", "12345", null, null));

        // different ordering
        assertParseSuccess(parser, " n/alice p/12345", expectedLookupCommand);
        assertParseSuccess(parser, " p/12345 n/alice ", expectedLookupCommand);

        // prefix present but no keywords
        assertParseSuccess(parser, " c/ n/alice p/12345", expectedLookupCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/ \n alice \n p/\t \n12345  \t", expectedLookupCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // no keywords
        assertParseFailure(parser, " Alice Bob", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LookupCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " /n Alice", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LookupCommand.MESSAGE_USAGE));

        // multiple keywords in one prefix
        assertParseFailure(parser, " n/Alice Bob", LookupCommand.MESSAGE_ADDITIONAL_KEYWORDS);
        assertParseFailure(parser, " c/t11 t12 t13", LookupCommand.MESSAGE_ADDITIONAL_KEYWORDS);

        // no leading whitespaces
        assertParseFailure(parser, "n/Alice", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LookupCommand.MESSAGE_USAGE));
    }

}
