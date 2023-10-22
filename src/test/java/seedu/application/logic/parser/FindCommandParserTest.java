package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.Messages.MESSAGE_INVALID_SPECIFIER;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.model.job.FieldContainsKeywordsPredicateTest.INVALID_SPECIFIER;
import static seedu.application.model.job.Role.ROLE_SPECIFIER;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.FindCommand;
import seedu.application.model.job.FieldContainsKeywordsPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSpecifier_throwsParseException() {
        assertParseFailure(parser, INVALID_SPECIFIER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_SPECIFIER));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(
                        new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Arrays.asList("Software", "Grass")));
        assertParseSuccess(parser, ROLE_SPECIFIER + " Software Grass", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, ROLE_SPECIFIER + " \n Software \n \t Grass  \t", expectedFindCommand);
    }

}
