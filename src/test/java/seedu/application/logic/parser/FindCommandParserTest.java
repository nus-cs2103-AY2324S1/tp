package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.FindCommand;
import seedu.application.model.job.CombinedPredicate;
import seedu.application.model.job.FieldContainsKeywordsPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyKeywords_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_ROLE.toString(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FieldContainsKeywordsPredicate expectedFirstPredicate = new FieldContainsKeywordsPredicate(
                PREFIX_ROLE, Arrays.asList("Software", "Grass"));
        CombinedPredicate expectedCombinedPredicate = new CombinedPredicate(Arrays.asList(expectedFirstPredicate));
        FindCommand expectedFindCommand =
                new FindCommand(expectedCombinedPredicate);
        assertParseSuccess(parser, " " + PREFIX_ROLE + " Software Grass", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " "
                + PREFIX_ROLE + " \n Software \n \t Grass  \t", expectedFindCommand);

        // multiple prefixes
        FieldContainsKeywordsPredicate expectedSecondPredicate = new FieldContainsKeywordsPredicate(
                PREFIX_COMPANY, Arrays.asList("Google"));
        expectedCombinedPredicate = new CombinedPredicate(
                Arrays.asList(expectedFirstPredicate, expectedSecondPredicate));
        expectedFindCommand = new FindCommand(expectedCombinedPredicate);
        assertParseSuccess(parser, " " + PREFIX_ROLE + "Software Grass " + PREFIX_COMPANY + "Google",
                expectedFindCommand);

    }

}
