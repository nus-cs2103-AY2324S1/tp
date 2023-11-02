package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindAllCommand;
import seedu.address.model.event.EventNameOrGroupContainsKeywordsPredicate;
import seedu.address.model.person.PersonNameOrGroupContainsKeywordsPredicate;

public class FindAllCommandParserTest {

    private FindAllCommandParser parser = new FindAllCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindAllCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindAllCommand expectedFindAllCommand =
                new FindAllCommand(new PersonNameOrGroupContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new EventNameOrGroupContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindAllCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindAllCommand);
    }

}
