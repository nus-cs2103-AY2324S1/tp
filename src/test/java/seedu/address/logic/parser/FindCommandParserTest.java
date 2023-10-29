package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.predicates.CompositePredicate;
import seedu.address.model.person.predicates.IdContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void nameparse_validArgs_returnsFindCommand() {
        CompositePredicate findCommandPredicate = new CompositePredicate();
        findCommandPredicate.add(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(findCommandPredicate);
        assertParseSuccess(parser, " n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void nricparse_validArgs_returnsFindCommand() {
        CompositePredicate findCommandPredicate = new CompositePredicate();
        findCommandPredicate.add(new IdContainsKeywordsPredicate(Arrays.asList("T0100606Z", "T0206006Z")));
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand = new FindCommand(findCommandPredicate);
        assertParseSuccess(parser, " id/T0100606Z T0206006Z", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " id/T0100606Z \n \t T0206006Z  \t", expectedFindCommand);
    }
}
