package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindLocationCommand;
import seedu.address.model.util.LocationContainsKeywordsPredicate;

public class FindLocationCommandParserTest {

    private FindLocationCommandParser parser = new FindLocationCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        // EP: No keywords
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindLocationCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindLocationCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindLocationCommand() {
        // EP: One keyword
        FindLocationCommand expectedFindLocationCommand =
                new FindLocationCommand(new LocationContainsKeywordsPredicate(Arrays.asList("deck")));
        assertParseSuccess(parser, "deck", expectedFindLocationCommand);

        // EP: Multiple keywords
        // no leading and trailing whitespaces
        expectedFindLocationCommand =
                new FindLocationCommand(new LocationContainsKeywordsPredicate(Arrays.asList("deck", "techno")));
        assertParseSuccess(parser, "deck techno", expectedFindLocationCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n deck \n \t techno  \t", expectedFindLocationCommand);
    }

}
