package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseComplexFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseComplexSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.PersonType;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseComplexFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE), PersonType.PATIENT);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")), PersonType.PATIENT);
        assertParseComplexSuccess(parser, "Alice Bob", expectedFindCommand, PersonType.PATIENT);

        // multiple whitespaces between keywords
        assertParseComplexSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand, PersonType.PATIENT);
    }

}
