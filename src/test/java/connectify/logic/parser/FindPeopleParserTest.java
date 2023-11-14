package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static connectify.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import connectify.logic.commands.FindPeopleCommand;
import connectify.model.person.NameContainsKeywordsPredicate;

public class FindPeopleParserTest {

    private FindPeopleCommandParser parser = new FindPeopleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPeopleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindPeopleCommand() {
        // no leading and trailing whitespaces
        FindPeopleCommand expectedFindPeopleCommand =
                new FindPeopleCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindPeopleCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindPeopleCommand);
    }

}
